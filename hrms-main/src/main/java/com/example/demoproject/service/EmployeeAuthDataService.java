package com.example.demoproject.service;

import com.example.demoproject.dto.EmployeeLoginDetailsDTO;
import com.example.demoproject.dto.ResetPasswordDTO;

public interface EmployeeAuthDataService {
	
	Boolean employeeLoginService(EmployeeLoginDetailsDTO employeeLoginDto);
	
	boolean employeePasswordReset(ResetPasswordDTO resetPasswordDTO);
	


}
