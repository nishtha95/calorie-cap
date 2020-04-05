package com.caloriecap.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caloriecap.dtos.CalorieReportDTO;
import com.caloriecap.dtos.MealDTO;
import com.caloriecap.dtos.UserDTO;
import com.caloriecap.exception.CalorieCapException;
import com.caloriecap.models.MealDAO;
import com.caloriecap.models.UserDAO;
import com.caloriecap.repositories.MealRepository;
import com.caloriecap.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	MealRepository mealRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public MealDAO addMeal(MealDTO mealDTO) throws CalorieCapException{
		
		UserDAO user=userRepository.findByUsername(mealDTO.getUsername());
		MealDAO mealDAO=new MealDAO();
		if(mealDTO.getId()!=null){
			mealDAO=mealRepository.findById(mealDTO.getId()).orElse(new MealDAO());
		}	
		mealDAO.setDate(mealDTO.getDate());
		mealDAO.setDescription(mealDTO.getDescription());
		mealDAO.setCalorieCount(mealDTO.getCalorieCount());
		mealDAO.setUser(user);
		List<MealDAO> meals= user.getMeals();
		if(meals==null) {
			meals=new ArrayList<>();
		}
		meals.add(mealDAO);
		user.setMeals(meals);
		try {
			userRepository.save(user);
			return mealDAO;
		} catch (Exception e) {
			throw new CalorieCapException(e.getMessage(),e.getCause());
		}
		
	}

	public String deleteMeal(MealDTO mealDTO) throws CalorieCapException {
		MealDAO mealDAO=mealRepository.findById(mealDTO.getId()).orElse(null);
		if(mealDAO==null) {
			log.info("Meal cannot be deleted as ID doesn't exist");
			throw new CalorieCapException("Invalid meal id provided for deletion");
		}
		mealRepository.delete(mealDAO);
		return "Meal deleted successfully";
	}
	
	public List<CalorieReportDTO> getCalorieRecordsBetweenDates(Date from, Date to,String username){
		System.out.println(username);
		Map<Date,CalorieReportDTO> map=new HashMap<>();
		UserDAO user=userRepository.findByUsername(username);
		Integer calorieCount=user.getExpectedCalorieCount();
		List<MealDAO> meals=mealRepository.findMealsBetweenDates(from, to, user);
		
		List<CalorieReportDTO> calorieReportDTOs=new ArrayList<>();
		for(MealDAO meal: meals) {
			System.out.println(meal.getDescription());
			CalorieReportDTO calorieReportDTO;
			MealDTO mealDto=new MealDTO();
			mealDto.setCalorieCount(meal.getCalorieCount());
			mealDto.setDescription(meal.getDescription());
			mealDto.setId(meal.getId());
			if(map.containsKey(meal.getDate())) {
				calorieReportDTO=map.get(meal.getDate());
				calorieReportDTO.setTotalCalorieCount(calorieReportDTO.getTotalCalorieCount()+meal.getCalorieCount());
				calorieReportDTO.getMeals().add(mealDto);
			}
			else {
				calorieReportDTO=new CalorieReportDTO();
				calorieReportDTO.setDate(meal.getDate());
				calorieReportDTO.setTotalCalorieCount(meal.getCalorieCount());
				List<MealDTO> mealDTOs=new ArrayList<>();
				mealDTOs.add(mealDto);
				calorieReportDTO.setMeals(mealDTOs);
				calorieReportDTO.setExpectedCalorieCount(calorieCount);
				
			}
			
			map.put(meal.getDate(), calorieReportDTO);
		}
		for(Map.Entry<Date,CalorieReportDTO> entry: map.entrySet()) {
			calorieReportDTOs.add(entry.getValue());
		}
		return calorieReportDTOs;
	}
	
	public String updateSuggestedCalorieCount(UserDTO userDto) {
		UserDAO user=userRepository.findByUsername(userDto.getUsername());
		user.setExpectedCalorieCount(userDto.getExpectedCalorieCount());
		userRepository.save(user);
		return "Suggested Calorie Updated Successfully";
	}
}
