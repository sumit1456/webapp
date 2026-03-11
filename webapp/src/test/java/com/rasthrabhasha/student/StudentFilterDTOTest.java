package com.rasthrabhasha.student;

import com.rasthrabhasha.student.dto.StudentFilterDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentFilterDTOTest {

    private StudentFilterDTO createFilter(String firstName, String lastName, Long schoolId, String email, Long studentId) {
        StudentFilterDTO f = new StudentFilterDTO();
        f.setFirstName(firstName);
        f.setLastName(lastName);
        f.setSchoolId(schoolId);
        f.setEmail(email);
        f.setStudentId(studentId);
        return f;
    }

    @Test
    void sameValues_shouldBeEqual() {
        StudentFilterDTO f1 = createFilter("Ram", "Sharma", 1L, "ram@test.com", 10L);
        StudentFilterDTO f2 = createFilter("Ram", "Sharma", 1L, "ram@test.com", 10L);
        assertEquals(f1, f2);
    }

    @Test
    void sameValues_shouldHaveSameHashCode() {
        StudentFilterDTO f1 = createFilter("Ram", "Sharma", 1L, "ram@test.com", 10L);
        StudentFilterDTO f2 = createFilter("Ram", "Sharma", 1L, "ram@test.com", 10L);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void differentValues_shouldNotBeEqual() {
        StudentFilterDTO f1 = createFilter("Ram", null, null, null, null);
        StudentFilterDTO f2 = createFilter("Shyam", null, null, null, null);
        assertNotEquals(f1, f2);
    }

    @Test
    void differentValues_shouldHaveDifferentHashCodes() {
        StudentFilterDTO f1 = createFilter("Ram", null, 1L, null, null);
        StudentFilterDTO f2 = createFilter("Ram", null, 2L, null, null);
        assertNotEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void allNullFields_shouldBeEqualToEachOther() {
        StudentFilterDTO f1 = createFilter(null, null, null, null, null);
        StudentFilterDTO f2 = createFilter(null, null, null, null, null);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void notEqualToNull_orDifferentClass() {
        StudentFilterDTO f = createFilter("Ram", null, null, null, null);
        assertNotEquals(f, null);
        assertNotEquals(f, "some string");
    }
}
