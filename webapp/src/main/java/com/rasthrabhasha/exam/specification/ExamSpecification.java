package com.rasthrabhasha.exam.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rasthrabhasha.exam.Exam;
import com.rasthrabhasha.exam.dto.ExamFilterDTO;

import jakarta.persistence.criteria.Predicate;

public class ExamSpecification {
    public static Specification<Exam> build(ExamFilterDTO filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getExamName() != null && !filter.getExamName().isBlank()) {
                predicates
                        .add(cb.like(cb.lower(root.get("exam_name")), "%" + filter.getExamName().toLowerCase() + "%"));
            }

            if (filter.getExamCode() != null && !filter.getExamCode().isBlank()) {
                predicates.add(cb.equal(root.get("exam_code"), filter.getExamCode()));
            }

            if (filter.getStatus() != null && !filter.getStatus().isBlank()) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
