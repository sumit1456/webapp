package com.rasthrabhasha.result;

import com.rasthrabhasha.result.dto.ExamResultFilterDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExamResultFilterDTOTest {

    private ExamResultFilterDTO create(Long studentId, Long examId, Long schoolId,
                                       Long regionId, Double min, Double max) {
        ExamResultFilterDTO f = new ExamResultFilterDTO();
        f.setStudentId(studentId);
        f.setExamId(examId);
        f.setSchoolId(schoolId);
        f.setRegionId(regionId);
        f.setMinPercentage(min);
        f.setMaxPercentage(max);
        return f;
    }

    @Test
    void sameValues_shouldBeEqualWithSameHashCode() {
        ExamResultFilterDTO f1 = create(1L, 2L, 3L, 4L, 40.0, 100.0);
        ExamResultFilterDTO f2 = create(1L, 2L, 3L, 4L, 40.0, 100.0);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentMinPercentage_shouldNotBeEqual() {
        ExamResultFilterDTO f1 = create(1L, 2L, 3L, 4L, 40.0, 100.0);
        ExamResultFilterDTO f2 = create(1L, 2L, 3L, 4L, 50.0, 100.0);
        assertNotEquals(f1, f2);
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentStudentId_shouldNotBeEqual() {
        ExamResultFilterDTO f1 = create(1L, 2L, 3L, 4L, null, null);
        ExamResultFilterDTO f2 = create(99L, 2L, 3L, 4L, null, null);
        assertNotEquals(f1, f2);
    }

    @Test
    void allNulls_shouldBeEqual() {
        ExamResultFilterDTO f1 = create(null, null, null, null, null, null);
        ExamResultFilterDTO f2 = create(null, null, null, null, null, null);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }
}
