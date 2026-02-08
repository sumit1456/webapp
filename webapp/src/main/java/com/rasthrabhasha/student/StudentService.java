package com.rasthrabhasha.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.school.School;
import com.rasthrabhasha.school.SchoolRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository student_repo;
	
	
	@Autowired
	SchoolRepository school_repo;

	public Student getStudent(long id) {
		return student_repo.findById(id).get();
	}

	public List<Student> getAllStudents() {
		return student_repo.findAll();
	}

	public Student addStudent(long school_id, Student st) {
		School school = school_repo.findById(school_id).orElseThrow(()->new RuntimeException("School was not found"));
	    st.setSchool(school);
		student_repo.save(st);
		return st;
	}

}
