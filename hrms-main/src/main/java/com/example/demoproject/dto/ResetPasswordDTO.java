package com.example.demoproject.dto;

public class ResetPasswordDTO {
	
	private String mailId;
	private String newPassword;
	private String reEnteredPassword;
	private int status;
	
	public ResetPasswordDTO() {
		
	}
	
	public ResetPasswordDTO(String mailId,String newPassword,
			String reEnteredPassword,int status) {
		this.mailId = mailId;
		this.newPassword = newPassword;
		this.reEnteredPassword = reEnteredPassword;
		this.status=status;
	}
	
	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReEnteredPassword() {
		return reEnteredPassword;
	}

	public void setReEnteredPassword(String reEnteredPassword) {
		this.reEnteredPassword = reEnteredPassword;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResetPasswordDTO [mailId=" + mailId + ", newPassword=" + newPassword + ", reEnteredPassword="
				+ reEnteredPassword + ", status=" + status + "]";
	}
	
	
	

	
	
	
	
	

}
