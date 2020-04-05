package com.caloriecap.dtos;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CalorieReportDTO {
	@Getter
	@Setter
	private Integer expectedCalorieCount;
	
	@Getter
	@Setter
	private Integer totalCalorieCount;
	
	@Getter
	@Setter
	private Date date;
	
	@Getter
	@Setter
	private List<MealDTO> meals;
}
