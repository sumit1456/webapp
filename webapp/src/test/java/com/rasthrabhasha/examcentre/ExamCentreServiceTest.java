package com.rasthrabhasha.examcentre;

import com.rasthrabhasha.examcentre.dto.ExamCentreDTO;
import com.rasthrabhasha.region.Region;
import com.rasthrabhasha.region.RegionRepository;
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
class ExamCentreServiceTest {

    @Mock
    private ExamCentreRepository examCentreRepository;

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private ExamCentreService examCentreService;

    private Region region;
    private ExamCentre examCentre;

    @BeforeEach
    void setUp() {
        region = new Region("North");
        region.setRegionId(1L);

        examCentre = new ExamCentre();
        examCentre.setCentreId(10L);
        examCentre.setCentreCode("DC001");
        examCentre.setCentreName("Delhi Centre");
        examCentre.setRegion(region);
    }

    @Test
    void addExamCentre_setsRegionAndSaves() {
        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));
        when(examCentreRepository.save(any(ExamCentre.class))).thenReturn(examCentre);

        ExamCentreDTO dto = examCentreService.addExamCentre(1L, examCentre);

        assertNotNull(dto);
        assertEquals("Delhi Centre", dto.getCentreName());
        assertEquals("DC001", dto.getCentreCode());
        assertEquals("North", dto.getRegionName());
        verify(examCentreRepository).save(examCentre);
    }

    @Test
    void addExamCentre_whenRegionNotFound_throwsException() {
        when(regionRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> examCentreService.addExamCentre(99L, examCentre));
        verify(examCentreRepository, never()).save(any());
    }

    @Test
    void getAllExamCentresDTOs_returnsListOfDTOs() {
        when(examCentreRepository.findAll()).thenReturn(List.of(examCentre));

        List<ExamCentreDTO> dtos = examCentreService.getAllExamCentresDTOs();

        assertEquals(1, dtos.size());
        assertEquals("DC001", dtos.get(0).getCentreCode());
        assertEquals(1L, dtos.get(0).getRegionId());
    }

    @Test
    void updateExamCentre_updatesFieldsAndSaves() {
        ExamCentre updated = new ExamCentre();
        updated.setCentreCode("DC002");
        updated.setCentreName("Mumbai Centre");

        when(examCentreRepository.findById(10L)).thenReturn(Optional.of(examCentre));
        when(examCentreRepository.save(any(ExamCentre.class))).thenAnswer(inv -> inv.getArgument(0));

        ExamCentreDTO dto = examCentreService.updateExamCentre(10L, updated);

        assertEquals("DC002", dto.getCentreCode());
        assertEquals("Mumbai Centre", dto.getCentreName());
    }

    @Test
    void updateExamCentre_whenNotFound_throwsException() {
        when(examCentreRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () ->
                examCentreService.updateExamCentre(99L, examCentre));
    }

    @Test
    void deleteExamCentre_callsDeleteById() {
        doNothing().when(examCentreRepository).deleteById(10L);

        examCentreService.deleteExamCentre(10L);

        verify(examCentreRepository).deleteById(10L);
    }
}
