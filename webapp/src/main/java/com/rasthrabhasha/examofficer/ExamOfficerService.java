package com.rasthrabhasha.examofficer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.examofficer.dto.ExamOfficerDTO;

@Service
public class ExamOfficerService {

	@Autowired
	private ExamOfficerRepository examOfficerRepository;

	public ExamOfficerDTO createExamOfficer(ExamOfficerDTO dto) {
		ExamOfficer officer = new ExamOfficer();
		officer.setUsername(dto.getUsername());
		officer.setPassword(dto.getPassword());
		officer.setName(dto.getName());
		ExamOfficer saved = examOfficerRepository.save(officer);
		return toDTO(saved);
	}

	public ExamOfficerDTO getExamOfficerById(long id) {
		Optional<ExamOfficer> officer = examOfficerRepository.findById(id);
		return officer.map(this::toDTO).orElse(null);
	}

	public List<ExamOfficerDTO> getAllExamOfficers() {
		return examOfficerRepository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	public ExamOfficerDTO updateExamOfficer(long id, ExamOfficerDTO dto) {
		Optional<ExamOfficer> existing = examOfficerRepository.findById(id);
		if (existing.isEmpty()) {
			return null;
		}
		ExamOfficer officer = existing.get();
		if (dto.getUsername() != null) {
			officer.setUsername(dto.getUsername());
		}
		if (dto.getPassword() != null) {
			officer.setPassword(dto.getPassword());
		}
		if (dto.getName() != null) {
			officer.setName(dto.getName());
		}
		ExamOfficer saved = examOfficerRepository.save(officer);
		return toDTO(saved);
	}

	public boolean deleteExamOfficer(long id) {
		if (!examOfficerRepository.existsById(id)) {
			return false;
		}
		examOfficerRepository.deleteById(id);
		return true;
	}

	private ExamOfficerDTO toDTO(ExamOfficer officer) {
		return new ExamOfficerDTO(officer.getExamOfficerId(), officer.getUsername(), officer.getName());
	}
}
