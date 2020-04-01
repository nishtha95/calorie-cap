package com.caloriecap.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="users")
public class UserDAO extends AccountDAO{
	
	@Getter
	@Setter
	@NotNull
	private Integer expectedCalorieCount;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	@Getter
	@Setter
	private Set<MealDAO> meals;
}
