package com.rasthrabhasha.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	StudentRepository student_repo;

	public Student getStudent(long id) {
		return student_repo.findById(id).get();
	}

	public List<Student> getAllStudents() {
		return student_repo.findAll();
	}

	public String addStudent(Student st) {
		student_repo.save(st);
		return "student has been added";
	}

}
