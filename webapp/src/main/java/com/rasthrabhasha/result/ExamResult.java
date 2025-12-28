package com.rasthrabhasha.result;

import java.time.LocalDateTime;

import com.rasthrabhasha.application.ExamApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "exam_result")
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(
        name = "application_id",
        referencedColumnName = "application_id",
        nullable = false,
        unique = true
    )
    private ExamApplication application;

    @Column(columnDefinition = "json")
    private String resultData;

    private LocalDateTime publishedAt;
}

