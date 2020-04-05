package com.caloriecap.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.caloriecap.dtos.UserDTO;
import com.caloriecap.exception.CalorieCapException;
import com.caloriecap.models.AccountDAO;
import com.caloriecap.models.AdminDAO;
import com.caloriecap.models.RoleDAO;
import com.caloriecap.models.UserDAO;
import com.caloriecap.repositories.AccountRepository;
import com.caloriecap.repositories.AdminRepository;
import com.caloriecap.repositories.RoleRepository;
import com.caloriecap.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountDAO account = accountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("Account doesn't exist for this username");
		}
		return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getSaltedHashedPassword(),
				new ArrayList<>());
	}
	
	public UserDAO save(UserDTO user) throws CalorieCapException {
		UserDAO newUser = null;
		AdminDAO admin=null;
		newUser= userRepository.findByUsername(user.getUsername());
		admin=adminRepository.findByUsername(user.getUsername());
		if(newUser!=null || admin!=null){
			throw new CalorieCapException("Account already exists with this username");
		}
		newUser= userRepository.findByEmail(user.getEmail());
		admin=adminRepository.findByEmail(user.getEmail());
		if(newUser!=null || admin!=null){
			throw new CalorieCapException("Account already exists with this email id");
		}
		newUser=new UserDAO();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setUsername(user.getUsername());
		newUser.setSaltedHashedPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setExpectedCalorieCount(user.getExpectedCalorieCount());
		newUser.setRole(roleRepository.findById(2L).orElseThrow());
		return userRepository.save(newUser);
	}
	
	public AdminDAO saveAdmin(UserDTO user) throws CalorieCapException {
		UserDAO newUser = null;
		AdminDAO admin=null;
		newUser= userRepository.findByUsername(user.getUsername());
		admin=adminRepository.findByUsername(user.getUsername());
		if(newUser!=null || admin!=null){
			throw new CalorieCapException("Account already exists with this username");
		}
		newUser= userRepository.findByEmail(user.getEmail());
		admin=adminRepository.findByEmail(user.getEmail());
		if(newUser!=null || admin!=null){
			throw new CalorieCapException("Account already exists with this email id");
		}
		admin=new AdminDAO();
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setUsername(user.getUsername());
		admin.setSaltedHashedPassword(bcryptEncoder.encode(user.getPassword()));
		admin.setEmail(user.getEmail());
		admin.setRole(roleRepository.findById(1L).orElseThrow());
		return adminRepository.save(admin);
	}
	public String getRoleByUserName(String username) {
		System.out.println(accountRepository.findByUsername(username).getRole().getName());
		return accountRepository.findByUsername(username).getRole().getName();
	}
	
}