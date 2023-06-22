package com.example.demoproject.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoproject.dto.EmployeeLoginDetailsDTO;
import com.example.demoproject.dto.ResetPasswordDTO;
import com.example.demoproject.jwtsecurity.JWTService;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.EmployeeAuthDataService;
import com.example.demoproject.service.EmployeeDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class EmployeeAuthController {
	
	private EmployeeAuthDataService employeeAuthDataService;
	
	public EmployeeAuthController(EmployeeAuthDataService employeeAuthDataService) {
		this.employeeAuthDataService = employeeAuthDataService;
	}
	
	@Autowired
	private EmployeeDataService employeeDataService;
	

	@Autowired
	private JWTService jwtService;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<Object> loginEmployeeController(
		@RequestBody @Valid	EmployeeLoginDetailsDTO emploDetailsDTO) {
		
	
		if (emploDetailsDTO.getMailId().isEmpty() && emploDetailsDTO.getPassword().isEmpty()) {
			throw new UsernameNotFoundException("Invalid Username or Password");
		} 
		
		if (emploDetailsDTO.getStatus()==0) {
			
			 Boolean loginStatus = employeeAuthDataService
						.employeeLoginService(emploDetailsDTO);
				 
				 if (loginStatus) {
					 
					return ResponseHandler.generateSuccessResponse(
							"Successfully Logged In", HttpStatus.OK);		 
				} else {
					
					return ResponseHandler.generateErrorResponse(
							"Pls Check Credentials. Error in Login", HttpStatus.BAD_REQUEST);		
				}
			
		} else {
			
			//navigate to normal login
			return ResponseHandler.generateSuccessResponse(
					"Successfully Logged In", HttpStatus.OK);
			
		}
			
			
					
		
	}
	
	
	@PostMapping("/reset-password")
	public ResponseEntity<Object> resetEmployeePasswordController(
			@RequestBody ResetPasswordDTO resetPasswordDTO) {
		
		if (resetPasswordDTO.getNewPassword().isEmpty() 
				&& resetPasswordDTO.getReEnteredPassword().isEmpty()) {
			throw new NullPointerException(
					"NewPassword or ReEnteredPassword cannot be Empty");
		} 
		
		if (employeeDataService.existsByMailId(resetPasswordDTO.getMailId())) {
						
			boolean resetStatus = employeeAuthDataService
					.employeePasswordReset(resetPasswordDTO);
			
			authenticateAndGetToken(resetPasswordDTO);
			
			if (resetStatus) {
				
				return ResponseHandler.generateSuccessResponse(
						"Successful Password Reset", HttpStatus.OK);
				
			} else {
				
				return ResponseHandler.generateErrorResponse(
						"Failed to Reset Password", HttpStatus.BAD_REQUEST);
				
			}
				
		} else {
			 throw new NoSuchElementException("Invalid Mail Id");
		}
		
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<Object> authenticateAndGetToken(
		@RequestBody ResetPasswordDTO resetPasswordDTO) 
	{
		
		if (resetPasswordDTO.getMailId().length() == 0) {
			System.out.println("Check for empty details ");
			throw new NoSuchElementException("Invalid User details");
		}
		
		if (employeeDataService.existsByMailId(resetPasswordDTO.getMailId())) {
			
			System.out.println("Fun: authenticateAndGetToken ");
			
			String data = jwtService.generateJWTToken(
					resetPasswordDTO.getMailId());
			
			return ResponseHandler.generateResponse(
					"Token generated Successfully", HttpStatus.OK, data);
			
		} else {
			return ResponseHandler.generateErrorResponse("Invalid Username", HttpStatus.BAD_REQUEST);
		}
		

	}
	

	

}
