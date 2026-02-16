package com.rasthrabhasha.school.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rasthrabhasha.school.School;
import com.rasthrabhasha.school.dto.SchoolFilterDTO;

import jakarta.persistence.criteria.Predicate;

public class SchoolSpecification {
    public static Specification<School> build(SchoolFilterDTO filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getSchoolName() != null && !filter.getSchoolName().isBlank()) {
                predicates.add(
                        cb.like(cb.lower(root.get("schoolName")), "%" + filter.getSchoolName().toLowerCase() + "%"));
            }

            if (filter.getExamCentreId() != null) {
                predicates.add(cb.equal(root.get("examCentre").get("centreId"), filter.getExamCentreId()));
            }

            if (filter.getRegionId() != null) {
                predicates.add(cb.equal(root.get("examCentre").get("region").get("regionId"), filter.getRegionId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
