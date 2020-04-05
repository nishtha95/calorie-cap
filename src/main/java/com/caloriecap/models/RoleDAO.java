package com.caloriecap.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles")
public class RoleDAO extends AuditableDAO{

	@NotBlank
	@Column(unique=true)
	@Getter
	@Setter
	private String name;
	
	@NotBlank
	@Getter
	@Setter
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="role")
	@Getter
	@Setter
	@JsonIgnore
	private List<UserDAO> users;
}
