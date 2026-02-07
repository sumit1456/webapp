package com.rasthrabhasha.exam;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rasthrabhasha.application.ExamApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examNo;

    // ===== EXISTING FIELDS (UNCHANGED) =====

    private String exam_name;

    private int no_of_papers;

    private double exam_fees;

    @Column(columnDefinition = "json")
    private String papers; 
    // [{"code":"P1","name":"Paper I","maxMarks":100}, ...]

    @Column(columnDefinition = "json")
    private String exam_details; 
    // exam-specific descriptive details

    // ===== NEW FIELDS (ADDED) =====

    @Column(unique = true, nullable = false)
    private String exam_code; 
    // PRAVIN_HINDI, PRABODH_HINDI

    private LocalDate application_start_date;
    private LocalDate application_end_date;

    private LocalDate exam_start_date;
    private LocalDate exam_end_date;

    // ✅ STATUS AS STRING
    private String status;
    // DRAFT / PUBLISHED / CLOSED / RESULT_PUBLISHED

    // ===== RELATIONSHIPS =====

    @OneToMany(mappedBy = "exam")
    @JsonIgnore
    private List<ExamApplication> applications;

    // ===== GETTERS & SETTERS =====

    public long getExamNo() {
        return examNo;
    }

    public void setExamNo(long examNo) {
        this.examNo = examNo;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public int getNo_of_papers() {
        return no_of_papers;
    }

    public void setNo_of_papers(int no_of_papers) {
        this.no_of_papers = no_of_papers;
    }

    public double getExam_fees() {
        return exam_fees;
    }

    public void setExam_fees(double exam_fees) {
        this.exam_fees = exam_fees;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }

    public String getExam_details() {
        return exam_details;
    }

    public void setExam_details(String exam_details) {
        this.exam_details = exam_details;
    }

    public String getExam_code() {
        return exam_code;
    }

    public void setExam_code(String exam_code) {
        this.exam_code = exam_code;
    }

    public LocalDate getApplication_start_date() {
        return application_start_date;
    }

    public void setApplication_start_date(LocalDate application_start_date) {
        this.application_start_date = application_start_date;
    }

    public LocalDate getApplication_end_date() {
        return application_end_date;
    }

    public void setApplication_end_date(LocalDate application_end_date) {
        this.application_end_date = application_end_date;
    }

    public LocalDate getExam_start_date() {
        return exam_start_date;
    }

    public void setExam_start_date(LocalDate exam_start_date) {
        this.exam_start_date = exam_start_date;
    }

    public LocalDate getExam_end_date() {
        return exam_end_date;
    }

    public void setExam_end_date(LocalDate exam_end_date) {
        this.exam_end_date = exam_end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ExamApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<ExamApplication> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Exam [exam_name=" + exam_name + ", no_of_papers=" + no_of_papers +
                ", exam_fees=" + exam_fees + ", papers=" + papers + "]";
    }
}
