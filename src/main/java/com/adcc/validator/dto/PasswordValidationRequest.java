package com.adcc.validator.dto;

public class PasswordValidationRequest {
	
	private String cxrfToken = null;
	private String attemptedPassword = null;
	public String getCxrfToken() {
		return cxrfToken;
	}
	public void setCxrfToken(String cxrfToken) {
		this.cxrfToken = cxrfToken;
	}
	public String getAttemptedPassword() {
		return attemptedPassword;
	}
	public void setAttemptedPassword(String attemptedPassword) {
		this.attemptedPassword = attemptedPassword;
	}
	
	

}
