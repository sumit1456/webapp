package com.rasthrabhasha.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.rasthrabhasha.student.dto.StudentProfileDTO;
import com.rasthrabhasha.common.dto.PageResponse;
import com.rasthrabhasha.exception.EntityNotFoundException;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository profileRepository;

    @Autowired
    private StudentRepository studentRepository;

    public StudentProfileDTO getProfileDTO(long id) {
        StudentProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StudentProfile not found"));
        return mapToDTO(profile);
    }

    public List<StudentProfileDTO> getAllProfileDTOs() {
        return profileRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentProfileDTO addProfile(long studentId, StudentProfile profile) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        profile.setStudent(student);
        
        student.setHasProfile(true);
        studentRepository.save(student);
        
        StudentProfile savedProfile = profileRepository.save(profile);
        return mapToDTO(savedProfile);
    }

    public PageResponse<StudentProfileDTO> searchProfiles(Pageable pageable) {
        Page<StudentProfileDTO> page = profileRepository.findAll(pageable).map(this::mapToDTO);
        return new PageResponse<>(page);
    }

    @Transactional
    public StudentProfileDTO updateProfile(long id, StudentProfile updatedProfile) {
        StudentProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StudentProfile not found with id: " + id));

        profile.setDateOfBirth(updatedProfile.getDateOfBirth());
        profile.setGender(updatedProfile.getGender());
        profile.setCategory(updatedProfile.getCategory());
        profile.setPreviousExamName(updatedProfile.getPreviousExamName());
        profile.setPreviousExamMarks(updatedProfile.getPreviousExamMarks());
        profile.setFatherName(updatedProfile.getFatherName());
        profile.setMotherName(updatedProfile.getMotherName());
        profile.setGuardianContact(updatedProfile.getGuardianContact());
        profile.setQualification(updatedProfile.getQualification());
        profile.setProfilePhotoUrl(updatedProfile.getProfilePhotoUrl());
        profile.setSignatureUrl(updatedProfile.getSignatureUrl());
        profile.setIdProofNumber(updatedProfile.getIdProofNumber());
        profile.setIdProofDocumentUrl(updatedProfile.getIdProofDocumentUrl());
        profile.setProfileCompletionStatus(updatedProfile.getProfileCompletionStatus());
        profile.setAddress(updatedProfile.getAddress());
        profile.setPreviousExamYear(updatedProfile.getPreviousExamYear());
        profile.setPreviousExamRollNO(updatedProfile.getPreviousExamRollNO());

        return mapToDTO(profileRepository.save(profile));
    }

    @Transactional
    public void deleteProfile(long id) {
        StudentProfile profile = profileRepository.findById(id).orElse(null);
        if (profile != null && profile.getStudent() != null) {
            Student student = profile.getStudent();
            student.setHasProfile(false);
            studentRepository.save(student);
        }
        profileRepository.deleteById(id);
    }

    private StudentProfileDTO mapToDTO(StudentProfile profile) {
        return new StudentProfileDTO(
                profile.getProfileId(),
                profile.getDateOfBirth(),
                profile.getGender(),
                profile.getCategory(),
                profile.getPreviousExamName(),
                profile.getPreviousExamMarks(),
                profile.getPreviousExamRollNO(),
                profile.getPreviousExamYear(),
                profile.getFatherName(),
                profile.getMotherName(),
                profile.getGuardianContact(),
                profile.getQualification(),
                profile.getProfilePhotoUrl(),
                profile.getSignatureUrl(),
                profile.getIdProofNumber(),
                profile.getIdProofDocumentUrl(),
                profile.getProfileCompletionStatus(),
                profile.getAddress(),
                profile.getStudent() != null ? profile.getStudent().getStudentId() : null
        );
    }
}
