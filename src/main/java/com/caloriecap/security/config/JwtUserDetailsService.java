package com.caloriecap.security.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.caloriecap.dtos.UserDTO;
import com.caloriecap.models.RoleDAO;
import com.caloriecap.models.UserDAO;
import com.caloriecap.repositories.RoleRepository;
import com.caloriecap.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDAO user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getSaltedHashedPassword(),
				new ArrayList<>());
	}
	
	public UserDAO save(UserDTO user) {
		UserDAO newUser = new UserDAO();
		newUser.setUsername(user.getUsername());
		newUser.setSaltedHashedPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setExpectedCalorieCount(user.getExpectedCalorieCount());
		newUser.getRoles().add(roleRepository.findById(2L).orElseThrow());
		return userRepository.save(newUser);
	}
	
}