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
import com.caloriecap.dtos.MealDTO;
import com.caloriecap.dtos.UserDTO;
import com.caloriecap.exception.CalorieCapException;
import com.caloriecap.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;	
	
	@RequestMapping(value = "/addMeal", method = RequestMethod.POST)
	public ResponseEntity<?> addMeal(@RequestBody MealDTO meal){
		try {
			return ResponseEntity.ok(userService.addMeal(meal));
		} catch (CalorieCapException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getCalorieRecord", method = RequestMethod.POST)
	public ResponseEntity<?> getCalorieRecord(@RequestBody CalorieReportRequestDTO requestDTO){
		
		return ResponseEntity.ok(userService.getCalorieRecordsBetweenDates(requestDTO.getFrom(),requestDTO.getTo(),requestDTO.getUsername()));
	}
	
	@RequestMapping(value = "/deleteMeal", method = RequestMethod.POST)
	public ResponseEntity<?> deleteMeal(@RequestBody MealDTO meal){
		try {
			return ResponseEntity.ok(new ResponseEntity<>(userService.deleteMeal(meal),HttpStatus.OK));
		} catch (CalorieCapException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/editSuggestedCalories", method = RequestMethod.POST)
	public ResponseEntity<?> editSuggestedCalories(@RequestBody UserDTO user){
		return ResponseEntity.ok(new ResponseEntity<>(userService.updateSuggestedCalorieCount(user),HttpStatus.OK));
	}
}
