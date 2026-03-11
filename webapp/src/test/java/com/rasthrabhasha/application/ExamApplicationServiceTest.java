package com.rasthrabhasha.application;

import com.rasthrabhasha.application.dto.ExamApplicationDTO;
import com.rasthrabhasha.exam.Exam;
import com.rasthrabhasha.exam.ExamRepository;
import com.rasthrabhasha.exception.EntityNotFoundException;
import com.rasthrabhasha.result.ExamResultRepository;
import com.rasthrabhasha.student.Student;
import com.rasthrabhasha.student.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamApplicationServiceTest {

    @Mock
    private StudentRepository sr;

    @Mock
    private ExamRepository er;

    @Mock
    private ExamApplicationRepository exam_app_repo;

    @Mock
    private ExamResultRepository resultRepo;

    @InjectMocks
    private ExamApplicationService examApplicationService;

    private Student student;
    private Exam exam;
    private ExamApplication application;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Ram");
        student.setLastName("Sharma");

        exam = new Exam();
        exam.setExamNo(10L);
        exam.setExam_name("Prathmik");

        application = new ExamApplication();
        application.setApplicationId(100L);
        application.setStudent(student);
        application.setExam(exam);
        application.setStatus("SUBMITTED");
    }

    @Test
    void fillForm_newApplication_savesAndReturnsDTO() {
        ExamApplication request = new ExamApplication();
        request.setStudent(student);
        request.setExam(exam);

        when(er.findByExamNo(exam.getExamNo())).thenReturn(Optional.of(exam));
        when(sr.findByStudentId(student.getStudentId())).thenReturn(Optional.of(student));
        when(exam_app_repo.findByStudentAndExam(student, exam)).thenReturn(Optional.empty());
        when(exam_app_repo.save(any(ExamApplication.class))).thenReturn(application);

        ResponseEntity<ExamApplicationDTO> response = examApplicationService.fillForm(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("SUBMITTED", response.getBody().getStatus());
        verify(exam_app_repo).save(any(ExamApplication.class));
    }

    @Test
    void fillForm_whenExamNull_throwsIllegalArgumentException() {
        ExamApplication badRequest = new ExamApplication();
        badRequest.setStudent(student);
        // exam = null

        assertThrows(IllegalArgumentException.class,
                () -> examApplicationService.fillForm(badRequest));
    }

    @Test
    void fillForm_whenExamNotFound_throwsEntityNotFoundException() {
        ExamApplication request = new ExamApplication();
        request.setStudent(student);
        request.setExam(exam);

        when(er.findByExamNo(exam.getExamNo())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> examApplicationService.fillForm(request));
    }

    @Test
    void fillForm_existingApplication_updatesAndSaves() {
        ExamApplication request = new ExamApplication();
        request.setStudent(student);
        request.setExam(exam);
        request.setStatus("APPROVED");

        ExamApplication existing = new ExamApplication();
        existing.setApplicationId(200L);
        existing.setStudent(student);
        existing.setExam(exam);
        existing.setStatus("SUBMITTED");

        when(er.findByExamNo(exam.getExamNo())).thenReturn(Optional.of(exam));
        when(sr.findByStudentId(student.getStudentId())).thenReturn(Optional.of(student));
        when(exam_app_repo.findByStudentAndExam(student, exam)).thenReturn(Optional.of(existing));
        when(exam_app_repo.save(any(ExamApplication.class))).thenReturn(existing);

        ResponseEntity<ExamApplicationDTO> response = examApplicationService.fillForm(request);

        assertEquals(200, response.getStatusCode().value());
        verify(exam_app_repo).save(existing);
    }

    @Test
    void getAllApplicationsDTOs_returnsList() {
        when(exam_app_repo.findAll()).thenReturn(List.of(application));

        List<ExamApplicationDTO> dtos = examApplicationService.getAllApplicationsDTOs();

        assertEquals(1, dtos.size());
        assertEquals("SUBMITTED", dtos.get(0).getStatus());
    }

    @Test
    void deleteApplication_callsBothRepositories() {
        doNothing().when(resultRepo).deleteByApplication_ApplicationId(100L);
        doNothing().when(exam_app_repo).deleteById(100L);

        examApplicationService.deleteApplication(100L);

        verify(resultRepo).deleteByApplication_ApplicationId(100L);
        verify(exam_app_repo).deleteById(100L);
    }

    @Test
    void getFormByApplicationIdAndExamNo_whenNotFound_throwsEntityNotFoundException() {
        when(exam_app_repo.findByApplicationIdAndExam_ExamNo(999L, 10L)).thenReturn(null);
        assertThrows(EntityNotFoundException.class,
                () -> examApplicationService.getFormByApplicationIdAndExamNo(999L, 10L));
    }
}
