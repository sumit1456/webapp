package com.rasthrabhasha.result.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rasthrabhasha.result.ExamResult;
import com.rasthrabhasha.result.dto.ExamResultFilterDTO;

import jakarta.persistence.criteria.Predicate;

public class ExamResultSpecification {
    public static Specification<ExamResult> build(ExamResultFilterDTO filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getStudentId() != null) {
                predicates
                        .add(cb.equal(root.get("application").get("student").get("studentId"), filter.getStudentId()));
            }

            if (filter.getExamId() != null) {
                predicates.add(cb.equal(root.get("application").get("exam").get("examNo"), filter.getExamId()));
            }

            if (filter.getSchoolId() != null) {
                predicates.add(cb.equal(root.get("application").get("student").get("school").get("schoolId"),
                        filter.getSchoolId()));
            }

            if (filter.getRegionId() != null) {
                predicates.add(cb.equal(root.get("application").get("student").get("school").get("examCentre")
                        .get("region").get("regionId"), filter.getRegionId()));
            }

            if (filter.getMinPercentage() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("percentage"), filter.getMinPercentage()));
            }

            if (filter.getMaxPercentage() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("percentage"), filter.getMaxPercentage()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
