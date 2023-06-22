package com.example.demoproject.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demoproject.entitymodel.Employee;
import com.example.demoproject.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class EmployeeUserDetailsService implements UserDetailsService {
	
	@Autowired(required=true)
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		System.out.println("Fun: loadUserByUsername ");
		
	Optional<Employee> employeeInfo = employeeRepository.findByMailId(username);
	
	return employeeInfo.map(com.example.demoproject.config.EmployeeInfoUserDetails::new)
				.orElseThrow(()->new NoSuchElementException("Username Not found"));
	

	}	 

}

