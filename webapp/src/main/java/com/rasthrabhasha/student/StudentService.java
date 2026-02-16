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

@Service
public class StudentService {

	@Autowired
	StudentRepository student_repo;

	@Autowired
	SchoolRepository school_repo;

	public Student getStudent(long id) {
		return student_repo.findById(id).get();
	}

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
				s.getSchool() != null ? s.getSchool().getSchoolName() : null);
	}

	public List<Student> getAllStudents() {
		return student_repo.findAll();
	}

	public List<StudentDTO> getAllStudentsDTOs() {
		return student_repo.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

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
				s.getSchool() != null ? s.getSchool().getSchoolName() : null);
	}

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

	public Page<StudentDTO> searchStudents(StudentFilterDTO filter, Pageable pageable) {
		Specification<Student> spec = StudentSpecification.build(filter);
		return student_repo.findAll(spec, pageable)
				.map(s -> new StudentDTO(
						s.getStudentId(),
						s.getFirstName(),
						s.getMiddleName(),
						s.getLastName(),
						s.getContact(),
						s.getEmail(),
						s.getAge(),
						s.getMotherTongue(),
						s.getSchool() != null ? s.getSchool().getSchoolId() : null,
						s.getSchool() != null ? s.getSchool().getSchoolName() : null));
	}

}
