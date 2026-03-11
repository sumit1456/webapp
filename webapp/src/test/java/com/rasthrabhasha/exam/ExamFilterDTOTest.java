package com.rasthrabhasha.exam;

import com.rasthrabhasha.exam.dto.ExamFilterDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExamFilterDTOTest {

    private ExamFilterDTO create(String name, String code, String status) {
        ExamFilterDTO f = new ExamFilterDTO();
        f.setExamName(name);
        f.setExamCode(code);
        f.setStatus(status);
        return f;
    }

    @Test
    void sameValues_shouldBeEqualWithSameHashCode() {
        ExamFilterDTO f1 = create("Prathmik", "PRA001", "ACTIVE");
        ExamFilterDTO f2 = create("Prathmik", "PRA001", "ACTIVE");
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentExamCode_shouldNotBeEqual() {
        ExamFilterDTO f1 = create("Prathmik", "PRA001", "ACTIVE");
        ExamFilterDTO f2 = create("Prathmik", "PRA002", "ACTIVE");
        assertNotEquals(f1, f2);
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentStatus_shouldNotBeEqual() {
        ExamFilterDTO f1 = create("Prathmik", "PRA001", "ACTIVE");
        ExamFilterDTO f2 = create("Prathmik", "PRA001", "INACTIVE");
        assertNotEquals(f1, f2);
    }

    @Test
    void allNulls_shouldBeEqual() {
        ExamFilterDTO f1 = create(null, null, null);
        ExamFilterDTO f2 = create(null, null, null);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }
}
