package com.rasthrabhasha.region;

import com.rasthrabhasha.region.dto.RegionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionService regionService;

    private Region region;

    @BeforeEach
    void setUp() {
        region = new Region();
        region.setRegionId(1L);
        region.setRegionName("North");
    }

    @Test
    void addRegion_savesAndReturnsDTO() {
        when(regionRepository.save(any(Region.class))).thenReturn(region);

        RegionDTO dto = regionService.addRegion(region);

        assertNotNull(dto);
        assertEquals("North", dto.getRegionName());
        assertEquals(1L, dto.getRegionId());
        verify(regionRepository).save(region);
    }

    @Test
    void getAllRegionsDTOs_returnsList() {
        Region r2 = new Region("South");
        r2.setRegionId(2L);
        when(regionRepository.findAll()).thenReturn(List.of(region, r2));

        List<RegionDTO> dtos = regionService.getAllRegionsDTOs();

        assertEquals(2, dtos.size());
        assertEquals("North", dtos.get(0).getRegionName());
        assertEquals("South", dtos.get(1).getRegionName());
    }

    @Test
    void updateRegion_updatesNameAndSaves() {
        Region updated = new Region("East");
        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));
        when(regionRepository.save(any(Region.class))).thenAnswer(inv -> inv.getArgument(0));

        RegionDTO dto = regionService.updateRegion(1L, updated);

        assertEquals("East", dto.getRegionName());
        verify(regionRepository).save(region);
    }

    @Test
    void updateRegion_whenNotFound_throwsRuntimeException() {
        when(regionRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> regionService.updateRegion(99L, region));
    }

    @Test
    void deleteRegion_callsDeleteById() {
        doNothing().when(regionRepository).deleteById(1L);

        regionService.deleteRegion(1L);

        verify(regionRepository).deleteById(1L);
    }
}
