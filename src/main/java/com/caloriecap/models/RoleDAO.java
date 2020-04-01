package com.caloriecap.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
}
