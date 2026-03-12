package com.rasthrabhasha.examcentre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamCentreRepository extends JpaRepository<ExamCentre, Long>, JpaSpecificationExecutor<ExamCentre> {

	@Query("SELECT COUNT(e) FROM ExamCentre e WHERE e.region.regionId = :regionId")
	long countByRegionId(@Param("regionId") long regionId);
}
