package com.rasthrabhasha.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

	Optional<Student> findByStudentId(long studentId);

	Optional<Student> findByEmailAndPassword(String email, String password);

	@Query("""
			    SELECT DISTINCT s
			    FROM Student s
			    LEFT JOIN FETCH s.applications
			    WHERE s.studentId = :studentId
			""")
	Optional<Student> findStudentWithApplications(@Param("studentId") long studentId);

	long countBySchoolSchoolId(long schoolId);

	@Query("SELECT COUNT(s) FROM Student s WHERE s.school.examCentre.centreId = :centreId")
	long countByExamCentreId(@Param("centreId") long centreId);

	@Query("SELECT COUNT(s) FROM Student s WHERE s.school.examCentre.region.regionId = :regionId")
	long countByRegionId(@Param("regionId") long regionId);

	@Query("SELECT s.school.examCentre.region.regionName, COUNT(s) FROM Student s GROUP BY s.school.examCentre.region.regionName")
	List<Object[]> countStudentsByRegionName();

	@Query(value = "SELECT school_name as schoolName, studentCount FROM (SELECT sc.school_name, COUNT(st.student_id) as studentCount FROM school sc LEFT JOIN student st ON sc.school_id = st.school_id GROUP BY sc.school_id, sc.school_name ORDER BY studentCount DESC LIMIT 5) as top_schools", nativeQuery = true)
	List<Object[]> findTop5SchoolsByStudentCount();

}
