package com.adcc.validator.dto;

public class PasswordValidationResponse {
	
	private boolean valid = false;
	private String reasonKey = null;
	private String reasonUnlocalized = null;
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getReasonKey() {
		return reasonKey;
	}
	public void setReasonKey(String reasonKey) {
		this.reasonKey = reasonKey;
	}
	public String getReasonUnlocalized() {
		return reasonUnlocalized;
	}
	public void setReasonUnlocalized(String reasonUnlocalized) {
		this.reasonUnlocalized = reasonUnlocalized;
	}
	
	

}
