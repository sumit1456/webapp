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
        application.setFormData("{\"paper\":\"Hindi\"}");
    }

    private ExamApplicationDTO buildDTO(long examNo, long studentId, String status, String formData) {
        ExamApplicationDTO dto = new ExamApplicationDTO();
        dto.setExamNo(examNo);
        dto.setStudentId(studentId);
        dto.setStatus(status);
        dto.setFormData(formData);
        return dto;
    }

    @Test
    void fillForm_newApplication_savesAndReturnsDTO() {
        ExamApplicationDTO dto = buildDTO(10L, 1L, null, "{\"paper\":\"Hindi\"}");

        when(er.findByExamNo(10L)).thenReturn(Optional.of(exam));
        when(sr.findByStudentId(1L)).thenReturn(Optional.of(student));
        when(exam_app_repo.findByStudentAndExam(student, exam)).thenReturn(Optional.empty());
        when(exam_app_repo.save(any(ExamApplication.class))).thenReturn(application);

        ResponseEntity<ExamApplicationDTO> response = examApplicationService.fillForm(dto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("SUBMITTED", response.getBody().getStatus());
        assertEquals("{\"paper\":\"Hindi\"}", response.getBody().getFormData());
        verify(exam_app_repo).save(any(ExamApplication.class));
    }

    @Test
    void fillForm_newApplication_formDataIsNull_savesWithNullFormData() {
        ExamApplicationDTO dto = buildDTO(10L, 1L, null, null);

        ExamApplication savedApp = new ExamApplication();
        savedApp.setApplicationId(101L);
        savedApp.setStudent(student);
        savedApp.setExam(exam);
        savedApp.setStatus("SUBMITTED");
        savedApp.setFormData(null);

        when(er.findByExamNo(10L)).thenReturn(Optional.of(exam));
        when(sr.findByStudentId(1L)).thenReturn(Optional.of(student));
        when(exam_app_repo.findByStudentAndExam(student, exam)).thenReturn(Optional.empty());
        when(exam_app_repo.save(any(ExamApplication.class))).thenReturn(savedApp);

        ResponseEntity<ExamApplicationDTO> response = examApplicationService.fillForm(dto);

        assertEquals(200, response.getStatusCode().value());
        assertNull(response.getBody().getFormData());
    }

    @Test
    void fillForm_whenExamNoZero_throwsIllegalArgumentException() {
        ExamApplicationDTO dto = buildDTO(0L, 1L, null, null);
        assertThrows(IllegalArgumentException.class,
                () -> examApplicationService.fillForm(dto));
    }

    @Test
    void fillForm_whenStudentIdZero_throwsIllegalArgumentException() {
        ExamApplicationDTO dto = buildDTO(10L, 0L, null, null);
        assertThrows(IllegalArgumentException.class,
                () -> examApplicationService.fillForm(dto));
    }

    @Test
    void fillForm_whenExamNotFound_throwsEntityNotFoundException() {
        ExamApplicationDTO dto = buildDTO(10L, 1L, null, null);
        when(er.findByExamNo(10L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> examApplicationService.fillForm(dto));
    }

    @Test
    void fillForm_existingApplication_updatesFormDataAndStatus() {
        ExamApplicationDTO dto = buildDTO(10L, 1L, "APPROVED", "{\"paper\":\"Hindi Updated\"}");

        ExamApplication existing = new ExamApplication();
        existing.setApplicationId(200L);
        existing.setStudent(student);
        existing.setExam(exam);
        existing.setStatus("SUBMITTED");
        existing.setFormData("{\"paper\":\"Hindi\"}");

        when(er.findByExamNo(10L)).thenReturn(Optional.of(exam));
        when(sr.findByStudentId(1L)).thenReturn(Optional.of(student));
        when(exam_app_repo.findByStudentAndExam(student, exam)).thenReturn(Optional.of(existing));
        when(exam_app_repo.save(any(ExamApplication.class))).thenReturn(existing);

        ResponseEntity<ExamApplicationDTO> response = examApplicationService.fillForm(dto);

        assertEquals(200, response.getStatusCode().value());
        verify(exam_app_repo).save(existing);
    }

    @Test
    void getAllApplicationsDTOs_returnsList() {
        when(exam_app_repo.findAll()).thenReturn(List.of(application));

        List<ExamApplicationDTO> dtos = examApplicationService.getAllApplicationsDTOs();

        assertEquals(1, dtos.size());
        assertEquals("SUBMITTED", dtos.get(0).getStatus());
        assertEquals("{\"paper\":\"Hindi\"}", dtos.get(0).getFormData());
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

    @Test
    void updateApplication_updatesFormDataAndStatus() {
        ExamApplicationDTO dto = buildDTO(0, 0, "APPROVED", "{\"updated\":true}");

        ExamApplication existing = new ExamApplication();
        existing.setApplicationId(100L);
        existing.setStudent(student);
        existing.setExam(exam);
        existing.setStatus("SUBMITTED");
        existing.setFormData("{\"paper\":\"Hindi\"}");

        when(exam_app_repo.findById(100L)).thenReturn(Optional.of(existing));
        when(exam_app_repo.save(any(ExamApplication.class))).thenReturn(existing);

        ExamApplicationDTO result = examApplicationService.updateApplication(100L, dto);

        assertEquals("APPROVED", existing.getStatus());
        assertEquals("{\"updated\":true}", existing.getFormData());
        verify(exam_app_repo).save(existing);
    }

    @Test
    void updateApplication_whenNotFound_throwsEntityNotFoundException() {
        when(exam_app_repo.findById(999L)).thenReturn(Optional.empty());
        ExamApplicationDTO dto = buildDTO(0, 0, "APPROVED", null);

        assertThrows(EntityNotFoundException.class,
                () -> examApplicationService.updateApplication(999L, dto));
    }
}
