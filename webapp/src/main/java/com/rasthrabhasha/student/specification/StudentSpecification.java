package com.rasthrabhasha.student.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rasthrabhasha.student.Student;
import com.rasthrabhasha.student.dto.StudentFilterDTO;

import jakarta.persistence.criteria.Predicate;

public class StudentSpecification {
	
	
	public static Specification<Student> build(StudentFilterDTO filter) {
	    return (root, query, cb) -> {
	        List<Predicate> predicates = new ArrayList<>();

	        // 1. Student ID Filter (Exact Match)
	        if (filter.getStudentId() != null) {
	            predicates.add(cb.equal(root.get("studentId"), filter.getStudentId()));
	        }

	        // 2. First Name Filter (Case-Insensitive Partial Match)
	        if (filter.getFirstName() != null && !filter.getFirstName().isBlank()) {
	            predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + filter.getFirstName().toLowerCase() + "%"));
	        }

	        // 3. Last Name Filter (Case-Insensitive Partial Match)
	        if (filter.getLastName() != null && !filter.getLastName().isBlank()) {
	            predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + filter.getLastName().toLowerCase() + "%"));
	        }

	        // 4. School ID Filter (Join on School entity)
	        if (filter.getSchoolId() != null) {
	            predicates.add(cb.equal(root.get("school").get("schoolId"), filter.getSchoolId()));
	        }

	        // 5. Email Filter (Exact Match)
	        if (filter.getEmail() != null && !filter.getEmail().isBlank()) {
	            predicates.add(cb.equal(root.get("email"), filter.getEmail()));
	        }

	        return cb.and(predicates.toArray(new Predicate[0]));
	    };
	}
}
