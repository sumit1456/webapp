package com.rasthrabhasha.application;

import com.rasthrabhasha.exam.Exam;
import com.rasthrabhasha.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ExamApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;


    @ManyToOne
    @JoinColumn(name = "exam_no")
    private Exam exam;
    
    
    @ManyToOne
    private Student student;

    @Column(columnDefinition = "json")
    private String formData;   // exam-specific fields

    private String status;     // SUBMITTED / APPROVED / REJECTED
}

