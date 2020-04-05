package com.caloriecap.dtos;

import java.io.Serializable;

import lombok.Getter;

public class JwtResponseDTO implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	@Getter
	private final String jwttoken;

	public JwtResponseDTO(String jwttoken) {
		this.jwttoken = jwttoken;
	}

}