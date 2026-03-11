package com.rasthrabhasha.region;

import com.rasthrabhasha.region.dto.RegionFilterDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegionFilterDTOTest {

    @Test
    void sameRegionName_shouldBeEqualWithSameHashCode() {
        RegionFilterDTO f1 = new RegionFilterDTO();
        f1.setRegionName("North");

        RegionFilterDTO f2 = new RegionFilterDTO();
        f2.setRegionName("North");

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentRegionName_shouldNotBeEqual() {
        RegionFilterDTO f1 = new RegionFilterDTO();
        f1.setRegionName("North");

        RegionFilterDTO f2 = new RegionFilterDTO();
        f2.setRegionName("South");

        assertNotEquals(f1, f2);
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void nullName_shouldBeEqualToOtherNullName() {
        RegionFilterDTO f1 = new RegionFilterDTO();
        RegionFilterDTO f2 = new RegionFilterDTO();
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }
}
