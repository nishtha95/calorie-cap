package com.caloriecap.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caloriecap.dtos.UserDTO;
import com.caloriecap.models.MealDAO;
import com.caloriecap.models.UserDAO;
import com.caloriecap.repositories.MealRepository;
import com.caloriecap.repositories.UserRepository;

@Service
public class AdminService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MealRepository mealRepository;
	
	public List<UserDTO> getAllUsers() {
		List<UserDAO> userDAOs=new ArrayList<>();
		List<UserDTO> userDtos=new ArrayList<>();
		userDAOs=userRepository.findAll();
		for(UserDAO userDAO: userDAOs){
			UserDTO userDTO=new UserDTO();
			userDTO.setFirstName(userDAO.getFirstName());
			userDTO.setLastName(userDAO.getLastName());
			userDTO.setUsername(userDAO.getUsername());
			userDTO.setEmail(userDAO.getEmail());
			userDTO.setExpectedCalorieCount(userDAO.getExpectedCalorieCount());
			userDtos.add(userDTO);
		}
		return userDtos;
	}
	
	public String deleteUserByUserName(String username){
		UserDAO userDAO=userRepository.findByUsername(username);
		userRepository.delete(userDAO);
		return "User deleted Successfully";
	}
	
	public String deleteMealsByUserName(Date from ,Date to,String username){
		List<MealDAO> mealDAOs;
		System.out.println(username);
		if(username.equals("All")) {
			mealDAOs=mealRepository.findMealsBetweenDatesForAllUsers(from, to);
		}
		else {
			UserDAO user=userRepository.findByUsername(username);
			mealDAOs=mealRepository.findMealsBetweenDates(from, to,user);
		}
		mealRepository.deleteAll(mealDAOs);
		return "Meals deleted Successfully";
	}
	
}
