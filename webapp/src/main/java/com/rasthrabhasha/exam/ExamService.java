package com.rasthrabhasha.exam;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.application.ExamApplicationRepository;
import com.rasthrabhasha.application.ExamApplicationService;
import com.rasthrabhasha.result.ExamResultRepository;

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
	
	
	@Transactional
	public String deleteExam(long id) {
		exam_res_repo.deleteByApplication_Exam_ExamNo(id);
		exam_app_repo.deleteByExam_ExamNo(id);
		exam_repo.deleteById(id);
		return "Exam has been deleted";
		
	}


    @Transactional
    public String updateExam(long examNo, Exam updatedExam) {

    
        Exam exam = exam_repo.findById(examNo)
                .orElseThrow(() ->
                        new RuntimeException("Exam not found with id: " + examNo)
                );

       
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

    

        return "Exam has been updated successfully";
    }
}
