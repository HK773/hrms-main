package com.example.demoproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoproject.dto.EmployeeLoginDetailsDTO;
import com.example.demoproject.dto.ResetPasswordDTO;
import com.example.demoproject.entitymodel.Employee;
import com.example.demoproject.repository.EmployeeRepository;
import com.example.demoproject.utilities.PasswordUtil;

@Service
public class EmployeeAuthDataServiceImpl implements EmployeeAuthDataService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Autowired
	private EmployeeLoginDetailsDTO employeeLoginDto;
	
	@Override
	public Boolean employeeLoginService(EmployeeLoginDetailsDTO employeeLoginDto) {
		
		String mailId = employeeLoginDto.getMailId();
		
		
		Employee employee = employeeRepository.findByMailId(mailId).get();
		
		if (employeeLoginDto.getStatus()==0) {
			if (employee.getMailId().equalsIgnoreCase(mailId) 
					&& employee.getPassword().equals(employeeLoginDto.getPassword())) {
				
				return true;
				
			} else {
				return false;
			}
		} else {
			
		boolean isPassword = passwordUtil.matches(
					employee.getPassword(), employeeLoginDto.getPassword());
			
			if (employee.getMailId().equalsIgnoreCase(mailId) 
					&& isPassword) {
				
				return true;
				
			} else {
				return false;
			}
			
		}
		
	}

	@Override
	public boolean employeePasswordReset(ResetPasswordDTO resetPasswordDTO) {
		
		String mailId = resetPasswordDTO.getMailId();
		Employee employee = employeeRepository.findByMailId(mailId).get();
		
		if (resetPasswordDTO.getNewPassword()
				.equals(resetPasswordDTO.getReEnteredPassword())) {
			
			employee.setPassword(passwordUtil.passwordEncoder().encode(
					resetPasswordDTO.getNewPassword()));
			employeeLoginDto.setStatus(1);
			resetPasswordDTO.setStatus(1);
			
			
			employeeRepository.save(employee);
			
			return true;
			
		} else {
			return false;
		}
		
	}

	
}
