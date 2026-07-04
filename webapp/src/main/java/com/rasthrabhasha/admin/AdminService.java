package com.rasthrabhasha.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.admin.dto.AdminDTO;
import com.rasthrabhasha.admin.dto.DashboardStatsDTO;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public AdminDTO createAdmin(AdminDTO dto) {
		Admin admin = new Admin();
		admin.setUsername(dto.getUsername());
		admin.setPassword(dto.getPassword());
		Admin saved = adminRepository.save(admin);
		return toDTO(saved);
	}

	public AdminDTO getAdminById(long id) {
		Optional<Admin> admin = adminRepository.findById(id);
		return admin.map(this::toDTO).orElse(null);
	}

	public List<AdminDTO> getAllAdmins() {
		return adminRepository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	public AdminDTO updateAdmin(long id, AdminDTO dto) {
		Optional<Admin> existing = adminRepository.findById(id);
		if (existing.isEmpty()) {
			return null;
		}
		Admin admin = existing.get();
		if (dto.getUsername() != null) {
			admin.setUsername(dto.getUsername());
		}
		if (dto.getPassword() != null) {
			admin.setPassword(dto.getPassword());
		}
		Admin saved = adminRepository.save(admin);
		return toDTO(saved);
	}

	public boolean deleteAdmin(long id) {
		if (!adminRepository.existsById(id)) {
			return false;
		}
		adminRepository.deleteById(id);
		return true;
	}

	public DashboardStatsDTO getStats() {
		return null;
	}

	private AdminDTO toDTO(Admin admin) {
		return new AdminDTO(admin.getAdmin_id(), admin.getUsername());
	}
}
