package com.rasthrabhasha.school;

import com.rasthrabhasha.school.dto.SchoolFilterDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SchoolFilterDTOTest {

    private SchoolFilterDTO create(String name, Long centreId, Long regionId) {
        SchoolFilterDTO f = new SchoolFilterDTO();
        f.setSchoolName(name);
        f.setExamCentreId(centreId);
        f.setRegionId(regionId);
        return f;
    }

    @Test
    void sameValues_shouldBeEqualWithSameHashCode() {
        SchoolFilterDTO f1 = create("DPS", 1L, 2L);
        SchoolFilterDTO f2 = create("DPS", 1L, 2L);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentSchoolName_shouldNotBeEqual() {
        SchoolFilterDTO f1 = create("DPS", 1L, 2L);
        SchoolFilterDTO f2 = create("KV", 1L, 2L);
        assertNotEquals(f1, f2);
    }

    @Test
    void differentRegionId_shouldNotBeEqual() {
        SchoolFilterDTO f1 = create("DPS", 1L, 1L);
        SchoolFilterDTO f2 = create("DPS", 1L, 99L);
        assertNotEquals(f1, f2);
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void allNulls_shouldBeEqual() {
        SchoolFilterDTO f1 = create(null, null, null);
        SchoolFilterDTO f2 = create(null, null, null);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }
}
