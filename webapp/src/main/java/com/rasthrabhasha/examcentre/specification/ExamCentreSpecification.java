package com.rasthrabhasha.examcentre.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rasthrabhasha.examcentre.ExamCentre;
import com.rasthrabhasha.examcentre.dto.ExamCentreFilterDTO;

import jakarta.persistence.criteria.Predicate;

public class ExamCentreSpecification {
    public static Specification<ExamCentre> build(ExamCentreFilterDTO filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getCentreName() != null && !filter.getCentreName().isBlank()) {
                predicates.add(
                        cb.like(cb.lower(root.get("centreName")), "%" + filter.getCentreName().toLowerCase() + "%"));
            }

            if (filter.getCentreCode() != null && !filter.getCentreCode().isBlank()) {
                predicates.add(cb.equal(root.get("centreCode"), filter.getCentreCode()));
            }

            if (filter.getRegionId() != null) {
                predicates.add(cb.equal(root.get("region").get("regionId"), filter.getRegionId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
