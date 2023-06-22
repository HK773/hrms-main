package com.example.demoproject.dto;

import org.springframework.stereotype.Component;

@Component
public class EmployeeLoginDetailsDTO {
	
	private String mailId;
	
	private String password;
	
	private int status;
	
	public EmployeeLoginDetailsDTO() {
		
	}
	
	public EmployeeLoginDetailsDTO(String mailId, String password) {
		super();
		this.mailId = mailId;
		this.password = password;
	}
	

	public EmployeeLoginDetailsDTO(String mailId, String password, int status) {
		super();
		this.mailId = mailId;
		this.password = password;
		this.status = status;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmployeeLoginDetailsDTO [mailId=" + mailId + ", password=" + password + ", status=" + status + "]";
	}
	
	

	
	
}

