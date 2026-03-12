package com.rasthrabhasha.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.analytics.dto.AnalyticsResponseDTO;
import com.rasthrabhasha.analytics.dto.AnalyticsSummaryDTO;
import com.rasthrabhasha.analytics.dto.DistributionDTO;
import com.rasthrabhasha.analytics.dto.SchoolStudentCountDTO;
import com.rasthrabhasha.examcentre.ExamCentreRepository;
import com.rasthrabhasha.region.RegionRepository;
import com.rasthrabhasha.school.SchoolRepository;
import com.rasthrabhasha.student.StudentRepository;

@Service
public class AnalyticsService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ExamCentreRepository examCentreRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;

    // --------------- SUMMARY ENDPOINT ---------------

    @Cacheable(value = "analytics_summary")
    public AnalyticsResponseDTO getAnalyticsSummary() {
        AnalyticsSummaryDTO summary = new AnalyticsSummaryDTO(
                regionRepository.count(),
                examCentreRepository.count(),
                schoolRepository.count(),
                studentRepository.count());

        Map<String, Long> studentsByRegion = convertToMap(studentRepository.countStudentsByRegionName());
        Map<String, Long> schoolsByRegion = convertToMap(schoolRepository.countSchoolsByRegionName());
        DistributionDTO distribution = new DistributionDTO(studentsByRegion, schoolsByRegion);

        List<Object[]> raw = studentRepository.findTop5SchoolsByStudentCount();
        List<SchoolStudentCountDTO> topSchools = raw.stream()
                .map(obj -> new SchoolStudentCountDTO((String) obj[0], ((Number) obj[1]).longValue()))
                .collect(Collectors.toList());

        return new AnalyticsResponseDTO(summary, distribution, topSchools);
    }

    // --------------- SPECIFIC COUNT ENDPOINTS ---------------

    @Cacheable(value = "analytics_counts", key = "'school_' + #schoolId + '_students'")
    public long getStudentCountBySchool(long schoolId) {
        return studentRepository.countBySchoolSchoolId(schoolId);
    }

    @Cacheable(value = "analytics_counts", key = "'region_' + #regionId + '_examcentres'")
    public long getExamCentreCountByRegion(long regionId) {
        return examCentreRepository.countByRegionId(regionId);
    }

    @Cacheable(value = "analytics_counts", key = "'examcentre_' + #centreId + '_schools'")
    public long getSchoolCountByExamCentre(long centreId) {
        return schoolRepository.countByExamCentreCentreId(centreId);
    }

    @Cacheable(value = "analytics_counts", key = "'region_' + #regionId + '_students'")
    public long getStudentCountByRegion(long regionId) {
        return studentRepository.countByRegionId(regionId);
    }

    @Cacheable(value = "analytics_counts", key = "'region_' + #regionId + '_schools'")
    public long getSchoolCountByRegion(long regionId) {
        return schoolRepository.countByRegionId(regionId);
    }

    @Cacheable(value = "analytics_counts", key = "'examcentre_' + #centreId + '_students'")
    public long getStudentCountByExamCentre(long centreId) {
        return studentRepository.countByExamCentreId(centreId);
    }

    // --------------- HELPERS ---------------

    private Map<String, Long> convertToMap(List<Object[]> data) {
        Map<String, Long> map = new HashMap<>();
        for (Object[] row : data) {
            map.put((String) row[0], ((Number) row[1]).longValue());
        }
        return map;
    }
}
