package com.caloriecap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caloriecap.dtos.CalorieReportRequestDTO;
import com.caloriecap.dtos.UserDTO;
import com.caloriecap.services.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "/fetchAllUsers", method = RequestMethod.GET)
	public ResponseEntity<?> fetchAllUsers(){
		return ResponseEntity.ok(adminService.getAllUsers());
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ResponseEntity<?> deleteUser(@RequestBody String user){
		
		return ResponseEntity.ok(new ResponseEntity<>(adminService.deleteUserByUserName(user),HttpStatus.OK));
	}
	
	@RequestMapping(value = "/deleteMealsByUsername", method = RequestMethod.POST)
	public ResponseEntity<?> deleteMeals(@RequestBody CalorieReportRequestDTO requestDTO){
		
		return ResponseEntity.ok(new ResponseEntity<>(adminService.deleteMealsByUserName(requestDTO.getFrom(),requestDTO.getTo(),requestDTO.getUsername()),HttpStatus.OK));
	}
}
