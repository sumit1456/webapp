package com.rasthrabhasha.result;

import com.rasthrabhasha.application.ExamApplication;
import com.rasthrabhasha.application.ExamApplicationRepository;
import com.rasthrabhasha.exception.EntityNotFoundException;
import com.rasthrabhasha.exam.Exam;
import com.rasthrabhasha.result.dto.ExamResultDTO;
import com.rasthrabhasha.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamResultServiceTest {

    @Mock
    private ExamApplicationRepository ear;

    @Mock
    private ExamResultRepository err;

    @InjectMocks
    private ExamResultService examResultService;

    private ExamApplication application;
    private ExamResult examResult;
    private Student student;
    private Exam exam;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Ram");
        student.setMiddleName("Kumar");
        student.setLastName("Sharma");

        exam = new Exam();
        exam.setExamNo(5L);
        exam.setExam_name("Prathmik");

        application = new ExamApplication();
        application.setApplicationId(100L);
        application.setStudent(student);
        application.setExam(exam);
        application.setStatus("SUBMITTED");

        examResult = new ExamResult();
        examResult.setId(200L);
        examResult.setApplication(application);
        examResult.setTotalMarks(350.0);
        examResult.setPercentage(87.5);
        examResult.setResultData("{\"score\":87}");
        examResult.setPublishedAt(LocalDateTime.of(2025, 6, 1, 10, 0));
    }

    @Test
    void createResult_validApp_savesAndReturnsDTO() {
        ExamResult request = new ExamResult();
        request.setApplication(application);
        request.setTotalMarks(350.0);
        request.setPercentage(87.5);

        when(ear.findByApplicationId(100L)).thenReturn(application);
        when(err.save(any(ExamResult.class))).thenReturn(examResult);

        ExamResultDTO dto = examResultService.createResult(request);

        assertNotNull(dto);
        assertEquals(87.5, dto.getPercentage());
        assertEquals(350.0, dto.getTotalMarks());
        assertEquals("Ram Kumar Sharma", dto.getStudentName());
        verify(err).save(request);
    }

//    @Test
//    void createResult_invalidAppId_throwsEntityNotFoundException() {
//        ExamResult request = new ExamResult();
//        request.setApplication(application);
//
//        when(ear.findByApplicationId(100L)).thenReturn(null);
//
//        assertThrows(EntityNotFoundException.class,
//                () -> examResultService.createResult(request));
//        verify(err, never()).save(any());
//    }

    @Test
    void getExamResult_whenFound_returnsResult() {
        when(err.findByApplicationId(100L)).thenReturn(examResult);

        ExamResult result = examResultService.getExamResult(100L);

        assertNotNull(result);
        assertEquals(87.5, result.getPercentage());
    }

    @Test
    void getExamResult_whenNotFound_throwsEntityNotFoundException() {
        when(err.findByApplicationId(999L)).thenReturn(null);
        assertThrows(EntityNotFoundException.class,
                () -> examResultService.getExamResult(999L));
    }

    @Test
    void getResultDTO_returnsMappedDTO() {
        when(err.findByApplicationId(100L)).thenReturn(examResult);

        ExamResultDTO dto = examResultService.getResultDTO(100L);

        assertNotNull(dto);
        assertEquals(200L, dto.getId());
        assertEquals(87.5, dto.getPercentage());
        assertEquals("Prathmik", dto.getExamName());
    }

    @Test
    void getAllResultsDTOs_returnsMappedList() {
        when(err.findAll()).thenReturn(List.of(examResult));

        List<ExamResultDTO> dtos = examResultService.getAllResultsDTOs();

        assertEquals(1, dtos.size());
        assertEquals(87.5, dtos.get(0).getPercentage());
    }

    @Test
    void getStudentResultsDTOs_returnsFilteredList() {
        when(err.findByStudentId(1L)).thenReturn(List.of(examResult));

        List<ExamResultDTO> dtos = examResultService.getStudentResultsDTOs(1L);

        assertEquals(1, dtos.size());
        assertEquals(350.0, dtos.get(0).getTotalMarks());
    }
}
