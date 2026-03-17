package com.rasthrabhasha.school;

import com.rasthrabhasha.examcentre.ExamCentre;
import com.rasthrabhasha.examcentre.ExamCentreRepository;
import com.rasthrabhasha.school.dto.SchoolDTO;
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
class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private ExamCentreRepository examCentreRepository;

    @InjectMocks
    private SchoolService schoolService;

    private ExamCentre examCentre;
    private School school;

    @BeforeEach
    void setUp() {
        examCentre = new ExamCentre();
        examCentre.setCentreId(1L);
        examCentre.setCentreName("Delhi Centre");

        school = new School();
        school.setSchoolId(10L);
        school.setSchoolName("DPS");
        school.setSchoolCode("SCH001");
        school.setPrincipalName("John Doe");
        school.setExamCentre(examCentre);
    }

    @Test
    void addSchool_setsCentreAndSaves() {
        when(examCentreRepository.findById(1L)).thenReturn(Optional.of(examCentre));
        when(schoolRepository.save(any(School.class))).thenReturn(school);

        SchoolDTO dto = schoolService.addSchool(1L, school);

        assertNotNull(dto);
        assertEquals("DPS", dto.getSchoolName());
        assertEquals("SCH001", dto.getSchoolCode());
        assertEquals("John Doe", dto.getPrincipalName());
        assertEquals(1L, dto.getCentreId());
        assertEquals("Delhi Centre", dto.getCentreName());
        verify(schoolRepository).save(school);
    }

    @Test
    void addSchool_whenCentreNotFound_throwsException() {
        when(examCentreRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> schoolService.addSchool(99L, school));
        verify(schoolRepository, never()).save(any());
    }

    @Test
    void getAllSchoolsDTOs_returnsMappedList() {
        when(schoolRepository.findAll()).thenReturn(List.of(school));

        List<SchoolDTO> dtos = schoolService.getAllSchoolsDTOs();

        assertEquals(1, dtos.size());
        assertEquals("DPS", dtos.get(0).getSchoolName());
        assertEquals("SCH001", dtos.get(0).getSchoolCode());
    }

    @Test
    void getAllSchools_returnsRawEntities() {
        when(schoolRepository.findAll()).thenReturn(List.of(school));

        List<School> schools = schoolService.getAllSchools();

        assertEquals(1, schools.size());
        assertEquals("DPS", schools.get(0).getSchoolName());
    }

    @Test
    void updateSchool_updatesFieldsAndSaves() {
        SchoolDTO updateDto = new SchoolDTO();
        updateDto.setSchoolId(10L);
        updateDto.setSchoolName("Updated DPS");
        updateDto.setSchoolCode("SCH999");
        updateDto.setPrincipalName("Jane Smith");
        updateDto.setCentreId(1L);

        when(schoolRepository.findById(10L)).thenReturn(Optional.of(school));
        when(schoolRepository.save(any(School.class))).thenReturn(school);

        SchoolDTO resultDto = schoolService.updateSchool(updateDto);

        assertNotNull(resultDto);
        assertEquals("Updated DPS", school.getSchoolName());
        assertEquals("SCH999", school.getSchoolCode());
        assertEquals("Jane Smith", school.getPrincipalName());
        verify(schoolRepository).save(school);
    }
}
