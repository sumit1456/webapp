package com.rasthrabhasha.exam;

import com.rasthrabhasha.application.ExamApplicationRepository;
import com.rasthrabhasha.exam.dto.ExamDTO;
import com.rasthrabhasha.result.ExamResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceTest {

    @Mock
    private ExamRepository exam_repo;

    @Mock
    private ExamApplicationRepository exam_app_repo;

    @Mock
    private ExamResultRepository exam_res_repo;

    @InjectMocks
    private ExamService examService;

    private Exam exam;

    @BeforeEach
    void setUp() {
        exam = new Exam();
        exam.setExamNo(1L);
        exam.setExam_name("Prathmik");
        exam.setExam_code("PRA001");
        exam.setNo_of_papers(3);
        exam.setExam_fees(500.0);
        exam.setStatus("ACTIVE");
        exam.setApplication_start_date(LocalDate.of(2025, 1, 1));
        exam.setApplication_end_date(LocalDate.of(2025, 3, 31));
    }

    @Test
    void addExam_savesAndReturnsDTO() {
        when(exam_repo.save(any(Exam.class))).thenReturn(exam);

        ExamDTO dto = examService.addExam(exam);

        assertNotNull(dto);
        assertEquals("Prathmik", dto.getExam_name());
        assertEquals("PRA001", dto.getExam_code());
        assertEquals("ACTIVE", dto.getStatus());
        verify(exam_repo).save(exam);
    }

    @Test
    void getAllExamsDTOs_returnsMappedList() {
        when(exam_repo.findAll()).thenReturn(List.of(exam));

        List<ExamDTO> dtos = examService.getAllExamsDTOs();

        assertEquals(1, dtos.size());
        assertEquals("PRA001", dtos.get(0).getExam_code());
    }

    @Test
    void getExamDTO_whenFound_returnsDTO() {
        when(exam_repo.findById(1L)).thenReturn(Optional.of(exam));

        ExamDTO dto = examService.getExamDTO(1L);

        assertEquals("Prathmik", dto.getExam_name());
        assertEquals(3, dto.getNo_of_papers());
    }

    @Test
    void getExamDTO_whenNotFound_throwsException() {
        when(exam_repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> examService.getExamDTO(99L));
    }

    @Test
    void updateExam_updatesAllFieldsAndSaves() {
        Exam updated = new Exam();
        updated.setExam_name("Prabodh");
        updated.setExam_code("PRB002");
        updated.setNo_of_papers(2);
        updated.setExam_fees(300.0);
        updated.setStatus("DRAFT");
        updated.setApplication_start_date(LocalDate.of(2025, 6, 1));
        updated.setApplication_end_date(LocalDate.of(2025, 8, 31));

        when(exam_repo.findById(1L)).thenReturn(Optional.of(exam));
        when(exam_repo.save(any(Exam.class))).thenAnswer(inv -> inv.getArgument(0));

        ExamDTO dto = examService.updateExam(1L, updated);

        assertEquals("Prabodh", dto.getExam_name());
        assertEquals("PRB002", dto.getExam_code());
        assertEquals("DRAFT", dto.getStatus());
    }

    @Test
    void updateExam_whenNotFound_throwsException() {
        when(exam_repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> examService.updateExam(99L, exam));
    }

    @Test
    void deleteExam_callsAllThreeDeleteMethods() {
        doNothing().when(exam_res_repo).deleteByApplication_Exam_ExamNo(1L);
        doNothing().when(exam_app_repo).deleteByExam_ExamNo(1L);
        doNothing().when(exam_repo).deleteById(1L);

        String result = examService.deleteExam(1L);

        assertEquals("Exam has been deleted", result);
        verify(exam_res_repo).deleteByApplication_Exam_ExamNo(1L);
        verify(exam_app_repo).deleteByExam_ExamNo(1L);
        verify(exam_repo).deleteById(1L);
    }
}
