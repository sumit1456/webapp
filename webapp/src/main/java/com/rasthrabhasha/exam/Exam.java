package com.rasthrabhasha.exam;

import java.util.List;

import com.rasthrabhasha.application.ExamApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exam_n0;

    private String exam_name;

    private int no_of_papers;

    private double exam_fees;

    @OneToMany(mappedBy = "exam")
    private List<ExamApplication> applications;

    // getters & setters
}
