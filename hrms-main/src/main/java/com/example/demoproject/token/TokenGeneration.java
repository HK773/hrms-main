package com.example.demoproject.token;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoproject.dto.EmployeeLoginDetailsDTO;
import com.example.demoproject.jwtsecurity.JWTService;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.EmployeeDataService;

import jakarta.transaction.Transactional;

@RestController
@Transactional
@RequestMapping("/authenticate")
public class TokenGeneration {
	
	private EmployeeDataService employeeDataService;
	
	public TokenGeneration(EmployeeDataService employeeDataService) {
		this.employeeDataService = employeeDataService;
	}
	
	@Autowired
	private JWTService jwtService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	ResponseEntity<Object>
	
	@PostMapping
	public ResponseEntity<Object> authenticateAndGetToken(
		@RequestBody EmployeeLoginDetailsDTO employeeLoginDetails) 
	{		
		String data=null;
		
		if (employeeLoginDetails.getMailId().length() == 0) {
			throw new NoSuchElementException("Invalid User details");
		}
		
		System.out.println("Login details: " + employeeLoginDetails.getMailId() + " " 
				+ employeeLoginDetails.getPassword());
		
		 if (employeeDataService.existsByMailId(employeeLoginDetails.getMailId())) {
				
			 Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								employeeLoginDetails.getMailId(),
								employeeLoginDetails.getPassword()));
				
				
				if (authentication.isAuthenticated()) {
					
					data = jwtService.generateJWTToken(
							employeeLoginDetails.getMailId());
				
				}
				
			} else {
				return ResponseHandler.generateErrorResponse("Invalid Username", HttpStatus.BAD_REQUEST);
			}
		 return ResponseHandler.generateResponse(
					"Token generated Successfully", HttpStatus.OK, data);
	}
	
	
	
}
