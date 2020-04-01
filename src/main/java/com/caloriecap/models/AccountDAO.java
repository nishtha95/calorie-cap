package com.caloriecap.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AccountDAO extends AuditableDAO{

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
	
	@Getter
	@Setter
	@ManyToMany
	Set<RoleDAO> roles=new HashSet<>();
	
}
