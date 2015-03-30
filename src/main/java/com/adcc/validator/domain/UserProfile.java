package com.adcc.validator.domain;

import java.util.Date;
import java.util.Set;

public class UserProfile {
	
	private String userName;
	private String emailAddress;
	private String passwordHash;
	private Date registrationDate;
	private Set<String> previousPasswordHashes;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Set<String> getPreviousPasswordHashes() {
		return previousPasswordHashes;
	}
	public void setPreviousPasswordHashes(Set<String> previousPasswordHashes) {
		this.previousPasswordHashes = previousPasswordHashes;
	}
	
	

}
