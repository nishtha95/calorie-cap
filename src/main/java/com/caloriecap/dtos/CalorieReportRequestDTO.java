package com.caloriecap.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class CalorieReportRequestDTO {

	@Getter
	@Setter
	Date from;
	
	@Getter
	@Setter
	Date to;
	
	@Getter
	@Setter
	String username;
}
