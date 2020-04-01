package com.caloriecap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caloriecap.dtos.RoleDTO;
import com.caloriecap.models.RoleDAO;
import com.caloriecap.repositories.RoleRepository;

@Service
public class AdminService {

	@Autowired
	RoleRepository roleRepository;
	
	public RoleDAO addRole(RoleDTO roleDto) {
		RoleDAO role = new RoleDAO();
		role.setName(roleDto.getName());
		role.setDescription(roleDto.getDescription());
		return roleRepository.save(role);
	}
}
