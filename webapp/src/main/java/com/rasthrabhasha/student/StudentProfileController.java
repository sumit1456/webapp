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

import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;
import com.rasthrabhasha.student.dto.StudentProfileDTO;
import org.springframework.data.domain.Pageable;
import com.rasthrabhasha.common.dto.PageResponse;

@RestController
public class StudentProfileController {

    @Autowired
    private StudentProfileService sr;

    @GetMapping("/getStudentProfile")
    public ResponseEntity<?> getStudentProfile(@RequestParam long id) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
        if (err != null) return err;
        return ResponseEntity.ok(sr.getProfileDTO(id));
    }

    @GetMapping("/studentprofile/studentId/{id}")
    public ResponseEntity<?> getStudentProfilebyStudentId(@PathVariable long id) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
        if (err != null) return err;
        return ResponseEntity.ok(sr.getProfileByStudentId(id));
    }

    @GetMapping("/getAllStudentProfiles")
    public ResponseEntity<?> getAllStudentProfiles() {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
        if (err != null) return err;
        return ResponseEntity.ok(sr.getAllProfileDTOs());
    }

    @PostMapping("/addStudentProfile")
    public ResponseEntity<?> addStudentProfile(
            @RequestParam Long studentId,
            @RequestBody StudentProfile profile) {
        return createStudentProfile(studentId, profile);
    }

    @PostMapping("/studentProfiles")
    public ResponseEntity<?> createStudentProfile(
            @RequestParam Long studentId,
            @RequestBody StudentProfile profile) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_STUDENTS);
        if (err != null) return err;
        StudentProfileDTO dto = sr.addProfile(studentId, profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/getStudentProfileById")
    public ResponseEntity<?> getStudentProfileById(@RequestParam long id) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
        if (err != null) return err;
        return ResponseEntity.status(HttpStatus.OK).body(sr.getProfileDTO(id));
    }

    @GetMapping("/studentProfiles")
    public ResponseEntity<?> searchStudentProfiles(Pageable pageable) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
        if (err != null) return err;
        return ResponseEntity.ok(sr.searchProfiles(pageable));
    }

    @PutMapping("/studentProfiles/{id}")
    public ResponseEntity<?> updateStudentProfile(@PathVariable long id,
            @RequestBody StudentProfile profile) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_STUDENTS);
        if (err != null) return err;
        return ResponseEntity.ok(sr.updateProfile(id, profile));
    }

    @DeleteMapping("/studentProfiles/{id}")
    public ResponseEntity<?> deleteStudentProfile(@PathVariable long id) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_STUDENTS);
        if (err != null) return err;
        sr.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
