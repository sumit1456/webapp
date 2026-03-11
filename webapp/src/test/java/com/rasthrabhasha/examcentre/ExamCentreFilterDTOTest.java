package com.rasthrabhasha.examcentre;

import com.rasthrabhasha.examcentre.dto.ExamCentreFilterDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExamCentreFilterDTOTest {

    private ExamCentreFilterDTO create(String name, Long regionId, String code) {
        ExamCentreFilterDTO f = new ExamCentreFilterDTO();
        f.setCentreName(name);
        f.setRegionId(regionId);
        f.setCentreCode(code);
        return f;
    }

    @Test
    void sameValues_shouldBeEqualWithSameHashCode() {
        ExamCentreFilterDTO f1 = create("Delhi Centre", 1L, "DC001");
        ExamCentreFilterDTO f2 = create("Delhi Centre", 1L, "DC001");
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentRegionId_shouldNotBeEqual() {
        ExamCentreFilterDTO f1 = create("Delhi Centre", 1L, "DC001");
        ExamCentreFilterDTO f2 = create("Delhi Centre", 2L, "DC001");
        assertNotEquals(f1, f2);
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentCode_shouldNotBeEqual() {
        ExamCentreFilterDTO f1 = create("Delhi Centre", 1L, "DC001");
        ExamCentreFilterDTO f2 = create("Delhi Centre", 1L, "DC002");
        assertNotEquals(f1, f2);
    }

    @Test
    void allNulls_shouldBeEqual() {
        ExamCentreFilterDTO f1 = create(null, null, null);
        ExamCentreFilterDTO f2 = create(null, null, null);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }
}
