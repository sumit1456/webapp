package com.rasthrabhasha.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.application.ExamApplicationRepository;
import com.rasthrabhasha.result.ExamResultRepository;
import com.rasthrabhasha.exam.dto.ExamDTO;
import com.rasthrabhasha.exam.dto.ExamFilterDTO;
import com.rasthrabhasha.exam.specification.ExamSpecification;

import java.util.stream.Collectors;
import com.rasthrabhasha.common.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import jakarta.transaction.Transactional;

@Service
public class ExamService {

    @Autowired
    ExamRepository exam_repo;

    @Autowired
    ExamApplicationRepository exam_app_repo;

    @Autowired
    ExamResultRepository exam_res_repo;

    public List<Exam> getAllExams() {
        return exam_repo.findAll();
    }

    @CacheEvict(value = "exams", allEntries = true)
    public ExamDTO addExam(Exam exam) {
        Exam savedExam = exam_repo.save(exam);
        return mapToDTO(savedExam);
    }

    @Cacheable(value = "exams", key = "'allDTOs'")
    public List<ExamDTO> getAllExamsDTOs() {
        return exam_repo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "exams", key = "#id")
    public ExamDTO getExamDTO(long id) {
        Exam e = exam_repo.findById(id).orElseThrow(() -> new RuntimeException("Exam not found"));
        return mapToDTO(e);
    }

    @CacheEvict(value = "exams", allEntries = true)
    @Transactional
    public String deleteExam(long id) {
        exam_res_repo.deleteByApplication_Exam_ExamNo(id);
        exam_app_repo.deleteByExam_ExamNo(id);
        exam_repo.deleteById(id);
        return "Exam has been deleted";

    }

    @CacheEvict(value = "exams", allEntries = true)
    @Transactional
    public ExamDTO updateExam(long examNo, Exam updatedExam) {

        Exam exam = exam_repo.findById(examNo)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examNo));

        exam.setExam_name(updatedExam.getExam_name());
        exam.setNo_of_papers(updatedExam.getNo_of_papers());
        exam.setExam_fees(updatedExam.getExam_fees());
        exam.setPapers(updatedExam.getPapers());
        exam.setExam_details(updatedExam.getExam_details());
        exam.setExam_code(updatedExam.getExam_code());

        exam.setApplication_start_date(updatedExam.getApplication_start_date());
        exam.setApplication_end_date(updatedExam.getApplication_end_date());
        exam.setExam_start_date(updatedExam.getExam_start_date());
        exam.setExam_end_date(updatedExam.getExam_end_date());

        exam.setStatus(updatedExam.getStatus());

        Exam savedExam = exam_repo.save(exam);
        return mapToDTO(savedExam);
    }

    @Cacheable(value = "exams", key = "'search:' + #filter.hashCode() + ':' + #pageable.pageNumber + ':' + #pageable.pageSize")
    public PageResponse<ExamDTO> searchExams(ExamFilterDTO filter, Pageable pageable) {
        Specification<Exam> spec = ExamSpecification.build(filter);
        Page<ExamDTO> page = exam_repo.findAll(spec, pageable).map(this::mapToDTO);
        return new PageResponse<>(page);
    }

    private ExamDTO mapToDTO(Exam e) {
        ExamDTO dto = new ExamDTO();
        dto.setExamNo(e.getExamNo());
        dto.setExam_name(e.getExam_name());
        dto.setNo_of_papers(e.getNo_of_papers());
        dto.setExam_fees(e.getExam_fees());
        dto.setPapers(e.getPapers());
        dto.setExam_details(e.getExam_details());
        dto.setExam_code(e.getExam_code());
        dto.setApplication_start_date(e.getApplication_start_date());
        dto.setApplication_end_date(e.getApplication_end_date());
        dto.setExam_start_date(e.getExam_start_date());
        dto.setExam_end_date(e.getExam_end_date());
        dto.setStatus(e.getStatus());
        return dto;
    }
}
