package com.rasthrabhasha.application;

import com.rasthrabhasha.application.dto.ExamApplicationFilterDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExamApplicationFilterDTOTest {

    private ExamApplicationFilterDTO create(Long examId, Long studentId, String status,
                                             Long regionId, Long schoolId, Long centreId, Long appId) {
        ExamApplicationFilterDTO f = new ExamApplicationFilterDTO();
        f.setExamId(examId);
        f.setStudentId(studentId);
        f.setStatus(status);
        f.setRegionId(regionId);
        f.setSchoolId(schoolId);
        f.setExamCentre(centreId);
        f.setApplicationId(appId);
        return f;
    }

    @Test
    void sameValues_shouldBeEqualWithSameHashCode() {
        ExamApplicationFilterDTO f1 = create(1L, 2L, "SUBMITTED", 3L, 4L, 5L, 6L);
        ExamApplicationFilterDTO f2 = create(1L, 2L, "SUBMITTED", 3L, 4L, 5L, 6L);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentStatus_shouldNotBeEqual() {
        ExamApplicationFilterDTO f1 = create(1L, 2L, "SUBMITTED", 3L, 4L, 5L, 6L);
        ExamApplicationFilterDTO f2 = create(1L, 2L, "APPROVED", 3L, 4L, 5L, 6L);
        assertNotEquals(f1, f2);
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentExamId_shouldNotBeEqual() {
        ExamApplicationFilterDTO f1 = create(1L, 2L, "SUBMITTED", null, null, null, null);
        ExamApplicationFilterDTO f2 = create(99L, 2L, "SUBMITTED", null, null, null, null);
        assertNotEquals(f1, f2);
    }

    @Test
    void allNulls_shouldBeEqual() {
        ExamApplicationFilterDTO f1 = create(null, null, null, null, null, null, null);
        ExamApplicationFilterDTO f2 = create(null, null, null, null, null, null, null);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }
}
