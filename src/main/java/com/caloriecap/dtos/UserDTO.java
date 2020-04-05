package com.caloriecap.dtos;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {

	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String token;
	
	@Getter
	@Setter
	private Integer expectedCalorieCount;
	
	@Getter
	@Setter
	private String role;
	
}
