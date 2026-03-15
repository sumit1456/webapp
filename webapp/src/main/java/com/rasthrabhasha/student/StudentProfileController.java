package com.rasthrabhasha.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.rasthrabhasha.student.dto.StudentProfileDTO;
import org.springframework.data.domain.Pageable;
import com.rasthrabhasha.common.dto.PageResponse;

@RestController
public class StudentProfileController {

    @Autowired
    private StudentProfileService sr;

    @GetMapping("/getStudentProfile")
    public StudentProfileDTO getStudentProfile(@RequestParam long id) {
        return sr.getProfileDTO(id);
    }

    @GetMapping("/getAllStudentProfiles")
    public List<StudentProfileDTO> getAllStudentProfiles() {
        return sr.getAllProfileDTOs();
    }

    @PostMapping("/addStudentProfile")
    public ResponseEntity<StudentProfileDTO> addStudentProfile(
            @RequestParam Long studentId,
            @RequestBody StudentProfile profile) {
        return createStudentProfile(studentId, profile);
    }

    @PostMapping("/studentProfiles")
    public ResponseEntity<StudentProfileDTO> createStudentProfile(
            @RequestParam Long studentId,
            @RequestBody StudentProfile profile) {
        StudentProfileDTO dto = sr.addProfile(studentId, profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/getStudentProfileById")
    public ResponseEntity<StudentProfileDTO> getStudentProfileById(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(sr.getProfileDTO(id));
    }

    @GetMapping("/studentProfiles")
    public PageResponse<StudentProfileDTO> searchStudentProfiles(Pageable pageable) {
        return sr.searchProfiles(pageable);
    }

    @PutMapping("/studentProfiles/{id}")
    public ResponseEntity<StudentProfileDTO> updateStudentProfile(@PathVariable long id,
            @RequestBody StudentProfile profile) {
        return ResponseEntity.ok(sr.updateProfile(id, profile));
    }

    @DeleteMapping("/studentProfiles/{id}")
    public ResponseEntity<Void> deleteStudentProfile(@PathVariable long id) {
        sr.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
