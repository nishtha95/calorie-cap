package com.caloriecap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caloriecap.CalorieCapApplication;
import com.caloriecap.dtos.MealDTO;
import com.caloriecap.exception.CalorieCapException;
import com.caloriecap.models.MealDAO;
import com.caloriecap.repositories.MealRepository;

@Service
public class UserService {

	@Autowired
	MealRepository mealRepository;
	
	public MealDAO addMeal(MealDTO mealDTO) {
		MealDAO mealDAO=new MealDAO();
		mealDAO.setDate(mealDTO.getDate());
		mealDAO.setDescription(mealDTO.getDescription());
		mealDAO.setCalorieCount(mealDTO.getCalorieCount());
		return mealRepository.save(mealDAO);
	}
	
	public MealDAO updateMeal(MealDTO mealDTO) {
		MealDAO mealDAO=mealRepository.findById(mealDTO.get).orElseThrow();
		mealDAO.setDate(mealDTO.getDate());
		mealDAO.setDescription(mealDTO.getDescription());
		mealDAO.setCalorieCount(mealDTO.getCalorieCount());
		return mealRepository.f
	}
	
}
