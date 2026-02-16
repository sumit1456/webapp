package com.rasthrabhasha.application.specification;

import java.util.ArrayList;
import com.rasthrabhasha.application.ExamApplication;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rasthrabhasha.application.dto.ExamApplicationFilterDTO;

import jakarta.persistence.criteria.Predicate;

public class ExamApplicationSpecification {

	// This method builds a dynamic WHERE clause
	// based on filters sent from frontend
	public static Specification<ExamApplication> build(
			ExamApplicationFilterDTO filter) {

		return (root, query, cb) -> {

			// Stores all dynamic conditions
			List<Predicate> predicates = new ArrayList<>();

			// ----------------------------------------------------
			// Filter by Region
			// Path:
			// ExamApplication -> student -> school
			// -> examCentre -> region -> id
			// ----------------------------------------------------
			if (filter.getRegionId() != null) {
				predicates.add(
						cb.equal(
								root.get("student")
										.get("school")
										.get("examCentre")
										.get("region")
										.get("id"),
								filter.getRegionId()));
			}

			// ----------------------------------------------------
			// Filter by Exam Centre
			// Path:
			// ExamApplication -> student -> school
			// -> examCentre -> id
			// ----------------------------------------------------
			if (filter.getExamCentre() != null) {
				predicates.add(
						cb.equal(
								root.get("student")
										.get("school")
										.get("examCentre")
										.get("id"),
								filter.getExamCentre()));
			}

			// ----------------------------------------------------
			// Filter by School
			// Path:
			// ExamApplication -> student -> school -> id
			// ----------------------------------------------------
			if (filter.getSchoolId() != null) {
				predicates.add(
						cb.equal(
								root.get("student")
										.get("school")
										.get("id"),
								filter.getSchoolId()));
			}

			// ----------------------------------------------------
			// Filter by Exam
			// SQL: WHERE exam_id = ?
			// ----------------------------------------------------
			if (filter.getExamId() != null) {
				predicates.add(
						cb.equal(
								root.get("exam").get("id"),
								filter.getExamId()));
			}

			// ----------------------------------------------------
			// Filter by Student
			// SQL: AND student_id = ?
			// ----------------------------------------------------
			if (filter.getStudentId() != null) {
				predicates.add(
						cb.equal(
								root.get("student").get("id"),
								filter.getStudentId()));
			}

			// ----------------------------------------------------
			// Filter by Status
			// SQL: AND status = ?
			// ----------------------------------------------------
			if (filter.getStatus() != null
					&& !filter.getStatus().isBlank()) {

				predicates.add(
						cb.equal(
								root.get("status"),
								filter.getStatus()));
			}

			// ----------------------------------------------------
			// Filter by Application ID (optional)
			// SQL: AND application_id = ?
			// ----------------------------------------------------
			if (filter.getApplicationId() != null) {
				predicates.add(
						cb.equal(
								root.get("applicationId"),
								filter.getApplicationId()));
			}

			// ----------------------------------------------------
			// Combine all conditions using AND
			// If no filters → returns ALL records
			// ----------------------------------------------------
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
