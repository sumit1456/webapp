package com.rasthrabhasha.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.auth.dto.LoginRequestDTO;
import com.rasthrabhasha.student.Student;
import com.rasthrabhasha.student.StudentRepository;
import com.rasthrabhasha.student.dto.StudentDTO;

@Service
public class AuthService {

	@Autowired
	StudentRepository studentRepository;

	public StudentDTO verifyStudent(LoginRequestDTO dto) {
		Optional<Student> studentOpt = studentRepository.findByEmailAndPassword(dto.getUsername(), dto.getPassword());
		if (studentOpt.isEmpty()) {
			return null;
		}
		Student s = studentOpt.get();
		StudentDTO result = new StudentDTO(
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
		return result;
	}

}
