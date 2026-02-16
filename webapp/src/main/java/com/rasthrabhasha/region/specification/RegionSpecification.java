package com.rasthrabhasha.region.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rasthrabhasha.region.Region;
import com.rasthrabhasha.region.dto.RegionFilterDTO;

import jakarta.persistence.criteria.Predicate;

public class RegionSpecification {
    public static Specification<Region> build(RegionFilterDTO filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getRegionName() != null && !filter.getRegionName().isBlank()) {
                predicates.add(
                        cb.like(cb.lower(root.get("regionName")), "%" + filter.getRegionName().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
