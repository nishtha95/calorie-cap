package com.caloriecap.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AccountDAO extends AuditableDAO{

	@NotBlank
	@Getter
	@Setter
	protected String firstName;
	
	@NotBlank
	@Getter
	@Setter
	protected String lastName;
	
	@Email @NotBlank
	@Column(unique = true)
	@Getter
	@Setter
	protected String email;

	@NotBlank
	@Getter
	@Setter
	protected String username;
	
	@NotBlank
	@Getter
	@Setter
	protected String saltedHashedPassword;
	
	@ManyToOne
	@JoinColumn(name = "role_id",nullable = false)
	@Getter
	@Setter
	private RoleDAO role;

}
