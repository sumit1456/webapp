package com.rasthrabhasha.student;

import com.rasthrabhasha.exception.EntityNotFoundException;
import com.rasthrabhasha.school.School;
import com.rasthrabhasha.school.SchoolRepository;
import com.rasthrabhasha.student.dto.StudentDTO;
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
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepo;

    @Mock
    private SchoolRepository schoolRepo;

    @Mock
    private com.rasthrabhasha.application.ExamApplicationRepository examAppRepo;

    @Mock
    private com.rasthrabhasha.result.ExamResultRepository examResultRepo;

    @InjectMocks
    private StudentService studentService;

    private School school;
    private Student student;

    @BeforeEach
    void setUp() {
        school = new School();
        school.setSchoolId(1L);
        school.setSchoolName("DPS");

        student = new Student();
        student.setStudentId(10L);
        student.setFirstName("Ram");
        student.setMiddleName("Kumar");
        student.setLastName("Sharma");
        student.setContact("9876543210");
        student.setEmail("ram@test.com");
        student.setAge(18);
        student.setMotherTongue("Hindi");
        student.setSchool(school);
    }

    // ─── getStudentDTO ───────────────────────────────────────────────

    @Test
    void getStudentDTO_whenFound_returnsCorrectDTO() {
        when(studentRepo.findById(10L)).thenReturn(Optional.of(student));

        StudentDTO dto = studentService.getStudentDTO(10L);

        assertEquals("Ram", dto.getFirstName());
        assertEquals("Sharma", dto.getLastName());
        assertEquals(1L, dto.getSchoolId());
        assertEquals("DPS", dto.getSchoolName());
    }

    @Test
    void getStudentDTO_whenNotFound_throwsException() {
        when(studentRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> studentService.getStudentDTO(99L));
    }

    // ─── addStudent ──────────────────────────────────────────────────

    @Test
    void addStudent_setsSchoolAndSaves() {
        when(schoolRepo.findById(1L)).thenReturn(Optional.of(school));
        when(studentRepo.save(any(Student.class))).thenReturn(student);

        StudentDTO result = studentService.addStudent(1L, student);

        assertNotNull(result);
        assertEquals("Ram", result.getFirstName());
        verify(studentRepo, times(1)).save(student);
    }

    @Test
    void addStudent_whenSchoolNotFound_throwsException() {
        when(schoolRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> studentService.addStudent(99L, student));
        verify(studentRepo, never()).save(any());
    }

    // ─── updateStudent ───────────────────────────────────────────────

    @Test
    void updateStudent_updatesFieldsAndSaves() {
        Student updatedData = new Student();
        updatedData.setFirstName("Shyam");
        updatedData.setLastName("Verma");
        updatedData.setAge(20);

        when(studentRepo.findById(10L)).thenReturn(Optional.of(student));
        when(studentRepo.save(any(Student.class))).thenAnswer(inv -> inv.getArgument(0));

        StudentDTO result = studentService.updateStudent(10L, updatedData);

        assertEquals("Shyam", result.getFirstName());
        assertEquals("Verma", result.getLastName());
        assertEquals(20, result.getAge());
    }

    @Test
    void updateStudent_whenNotFound_throwsEntityNotFoundException() {
        when(studentRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> studentService.updateStudent(99L, student));
    }

    // ─── deleteStudent ───────────────────────────────────────────────

    @Test
    void deleteStudent_callsAllDeletionMethods() {
        doNothing().when(examResultRepo).deleteByApplication_Student_StudentId(10L);
        doNothing().when(examAppRepo).deleteByStudent_StudentId(10L);
        doNothing().when(studentRepo).deleteById(10L);

        studentService.deleteStudent(10L);

        verify(examResultRepo).deleteByApplication_Student_StudentId(10L);
        verify(examAppRepo).deleteByStudent_StudentId(10L);
        verify(studentRepo).deleteById(10L);
    }

    // ─── getAllStudentsDTOs ───────────────────────────────────────────

    @Test
    void getAllStudentsDTOs_returnsListOfDTOs() {
        when(studentRepo.findAll()).thenReturn(List.of(student));

        List<StudentDTO> dtos = studentService.getAllStudentsDTOs();

        assertEquals(1, dtos.size());
        assertEquals("Ram", dtos.get(0).getFirstName());
    }
}
