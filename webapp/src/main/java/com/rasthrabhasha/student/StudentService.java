package com.rasthrabhasha.student;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.school.School;
import com.rasthrabhasha.school.SchoolRepository;

import com.rasthrabhasha.dto.StudentDTO;
import java.util.stream.Collectors;

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
						s.getSchool() != null ? s.getSchool().getSchoolName() : null))
				.collect(Collectors.toList());
	}

	public Student addStudent(long school_id, Student st) {
		School school = school_repo.findById(school_id).orElseThrow(() -> new RuntimeException("School was not found"));
		st.setSchool(school);
		student_repo.save(st);
		return st;
	}

	public StudentDTO findStudentById(long student_id) {
		StudentDTO dto = new StudentDTO();
		
		
		Student student = student_repo.findById(student_id).orElseThrow(() -> new RuntimeException("Student with the id "+student_id+" was not found"));
		
		
		dto.setFirstName(student.getFirstName());
		dto.setContact(student.getContact());
		dto.setAge(student.getAge());
		dto.setEmail(student.getEmail());
		dto.setStudentId(dto.getStudentId());
		dto.setMiddleName(student.getMiddleName());
		
		dto.setSchoolId(student.getStudentId());
		
	    
	    dto.setExamApplications(student.getApplications());
		
		return dto;
	}

}
