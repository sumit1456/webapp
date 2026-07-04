package com.rasthrabhasha.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.admin.Admin;
import com.rasthrabhasha.admin.AdminRepository;
import com.rasthrabhasha.auth.dto.LoginRequestDTO;
import com.rasthrabhasha.auth.dto.LoginResponseDTO;
import com.rasthrabhasha.auth.jwt.JwtUtil;
import com.rasthrabhasha.examofficer.ExamOfficer;
import com.rasthrabhasha.examofficer.ExamOfficerRepository;
import com.rasthrabhasha.student.Student;
import com.rasthrabhasha.student.StudentRepository;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ExamOfficerRepository examOfficerRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO dto, String role) {
        return switch (role.toUpperCase()) {
            case "ADMIN" -> loginAdmin(dto);
            case "EXAM_OFFICER" -> loginExamOfficer(dto);
            case "STUDENT" -> loginStudent(dto);
            default -> null;
        };
    }

    private LoginResponseDTO loginAdmin(LoginRequestDTO dto) {
        Optional<Admin> admin = adminRepository.findByUsername(dto.getUsername());
        if (admin.isEmpty() || !admin.get().getPassword().equals(dto.getPassword())) {
            return null;
        }
        Admin a = admin.get();
        String token = jwtUtil.generateToken(a.getUsername(), "ADMIN");
        return new LoginResponseDTO(token, "ADMIN", a.getUsername(), a.getAdmin_id());
    }

    private LoginResponseDTO loginExamOfficer(LoginRequestDTO dto) {
        Optional<ExamOfficer> officer = examOfficerRepository.findByUsername(dto.getUsername());
        if (officer.isEmpty() || !officer.get().getPassword().equals(dto.getPassword())) {
            return null;
        }
        ExamOfficer o = officer.get();
        String token = jwtUtil.generateToken(o.getUsername(), "EXAM_OFFICER");
        return new LoginResponseDTO(token, "EXAM_OFFICER", o.getUsername(), o.getExamOfficerId());
    }

    private LoginResponseDTO loginStudent(LoginRequestDTO dto) {
        Optional<Student> student = studentRepository.findByEmailAndPassword(dto.getUsername(), dto.getPassword());
        if (student.isEmpty()) {
            return null;
        }
        Student s = student.get();
        String token = jwtUtil.generateToken(s.getEmail(), "STUDENT");
        return new LoginResponseDTO(token, "STUDENT", s.getEmail(), s.getStudentId());
    }
}
