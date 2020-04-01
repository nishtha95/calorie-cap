package com.caloriecap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caloriecap.dtos.RoleDTO;
import com.caloriecap.services.AdminService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public ResponseEntity<?> addRoles(@RequestBody RoleDTO roleDTO) throws Exception {
		adminService.addRole(roleDTO);
		return ResponseEntity.ok("Role Added");
	}
}
