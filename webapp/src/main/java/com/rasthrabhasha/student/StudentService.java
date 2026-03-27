package com.rasthrabhasha.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.school.School;
import com.rasthrabhasha.school.SchoolRepository;

import com.rasthrabhasha.student.dto.StudentDTO;
import com.rasthrabhasha.student.dto.StudentFilterDTO;
import com.rasthrabhasha.student.specification.StudentSpecification;
import com.rasthrabhasha.application.ExamApplication;
import com.rasthrabhasha.application.dto.ExamApplicationDTO;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.rasthrabhasha.common.dto.PageResponse;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import com.rasthrabhasha.exception.EntityNotFoundException;
import com.rasthrabhasha.application.ExamApplicationRepository;
import com.rasthrabhasha.result.ExamResultRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository student_repo;

	@Autowired
	SchoolRepository school_repo;

	public Student getStudent(long id) {
		return student_repo.findById(id).get();
	}

	@Cacheable(value = "students", key = "#id")
	public StudentDTO getStudentDTO(long id) {
		Student s = student_repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
		return new StudentDTO(
				s.getStudentId(),
				s.getFirstName(),
				s.getMiddleName(),
				s.getLastName(),
				s.getContact(),
				s.getEmail(),
				s.getAge(),
				s.getMotherTongue(),
				s.getSchool() != null ? s.getSchool().getSchoolId() : null,
				s.getSchool() != null ? s.getSchool().getSchoolName() : null,
				s.getHasProfile());
	}

	public List<Student> getAllStudents() {
		return student_repo.findAll();
	}

	@Cacheable(value = "students", key = "'allDTOs'")
	public List<StudentDTO> getAllStudentsDTOs() {
		return student_repo.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@CacheEvict(value = { "students", "analytics_summary", "analytics_counts" }, allEntries = true)
	public StudentDTO addStudent(long school_id, Student st) {
		School school = school_repo.findById(school_id)
				.orElseThrow(() -> new RuntimeException("School was not found"));
		st.setSchool(school);
		Student savedStudent = student_repo.save(st);
		return mapToDTO(savedStudent);
	}

	private StudentDTO mapToDTO(Student s) {
		return new StudentDTO(
				s.getStudentId(),
				s.getFirstName(),
				s.getMiddleName(),
				s.getLastName(),
				s.getContact(),
				s.getEmail(),
				s.getAge(),
				s.getMotherTongue(),
				s.getSchool() != null ? s.getSchool().getSchoolId() : null,
				s.getSchool() != null ? s.getSchool().getSchoolName() : null,
				s.getHasProfile());
	}

	@Cacheable(value = "students", key = "'details:' + #student_id")
	public StudentDTO findStudentById(long student_id) {
		Student student = student_repo.findById(student_id)
				.orElseThrow(() -> new RuntimeException("Student with the id " + student_id + " was not found"));

		StudentDTO dto = mapToDTO(student);

		if (student.getApplications() != null) {
			dto.setExamApplications(student.getApplications().stream()
					.map(this::mapApplicationToDTO)
					.collect(Collectors.toList()));
		}

		return dto;
	}

	private ExamApplicationDTO mapApplicationToDTO(ExamApplication ea) {
		ExamApplicationDTO dto = new ExamApplicationDTO();
		dto.setApplicationId(ea.getApplicationId());
		dto.setExamNo(ea.getExam() != null ? ea.getExam().getExamNo() : 0);
		dto.setExamName(ea.getExam() != null ? ea.getExam().getExam_name() : null);
		dto.setStudentId(ea.getStudent() != null ? ea.getStudent().getStudentId() : 0);
		dto.setStudentName(
				ea.getStudent() != null ? ea.getStudent().getFirstName() + " " + ea.getStudent().getLastName() : null);
		dto.setStatus(ea.getStatus());
		return dto;
	}

	@Cacheable(value = "students", key = "'search:' + #filter.hashCode() + ':' + #pageable.pageNumber + ':' + #pageable.pageSize")
	public PageResponse<StudentDTO> searchStudents(StudentFilterDTO filter, Pageable pageable) {
        System.out.println("Request sent to database");
		Specification<Student> spec = StudentSpecification.build(filter);
		Page<StudentDTO> page = student_repo.findAll(spec, pageable)
				.map(this::mapToDTO);
		return new PageResponse<>(page);
	}

	@CacheEvict(value = { "students", "analytics_summary", "analytics_counts" }, allEntries = true)
	@Transactional
	public StudentDTO updateStudent(long id, Student updatedStudent) {
		Student student = student_repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

		student.setFirstName(updatedStudent.getFirstName());
		student.setMiddleName(updatedStudent.getMiddleName());
		student.setLastName(updatedStudent.getLastName());
		student.setContact(updatedStudent.getContact());
		student.setEmail(updatedStudent.getEmail());
		student.setAge(updatedStudent.getAge());
		student.setMotherTongue(updatedStudent.getMotherTongue());
		if (updatedStudent.getHasProfile() != null) {
			student.setHasProfile(updatedStudent.getHasProfile());
		}

		return mapToDTO(student_repo.save(student));
	}

	@Autowired
	ExamApplicationRepository examAppRepo;

	@Autowired
	ExamResultRepository examResultRepo;

	@CacheEvict(value = { "students", "analytics_summary", "analytics_counts" }, allEntries = true)
	@Transactional
	public void deleteStudent(long id) {
		examResultRepo.deleteByApplication_Student_StudentId(id);
		examAppRepo.deleteByStudent_StudentId(id);
		student_repo.deleteById(id);
	}

}
