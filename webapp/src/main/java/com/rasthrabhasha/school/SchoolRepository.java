package com.rasthrabhasha.school;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>, JpaSpecificationExecutor<School> {

	long countByExamCentreCentreId(long centreId);

	@Query("SELECT COUNT(s) FROM School s WHERE s.examCentre.region.regionId = :regionId")
	long countByRegionId(@Param("regionId") long regionId);

	@Query("SELECT s.examCentre.region.regionName, COUNT(s) FROM School s GROUP BY s.examCentre.region.regionName")
	List<Object[]> countSchoolsByRegionName();
}
