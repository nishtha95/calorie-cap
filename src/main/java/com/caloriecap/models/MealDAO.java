package com.caloriecap.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="meals")
public class MealDAO extends AuditableDAO{

	private static final long serialVersionUID = -1002632094495829067L;

	@NotBlank
	@Getter
	@Setter
	private Date date;
	
	@NotBlank
	@Getter
	@Setter
	private String description;
	
	@NotBlank
	@Getter
	@Setter
	private int calorieCount;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	@Getter
	@Setter
	private UserDAO user;
	
	
}
