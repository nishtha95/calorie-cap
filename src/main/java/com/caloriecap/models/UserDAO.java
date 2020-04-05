package com.caloriecap.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	private List<MealDAO> meals;

}
