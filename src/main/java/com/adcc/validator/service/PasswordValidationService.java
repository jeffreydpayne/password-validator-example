package com.adcc.validator.service;

import com.adcc.validator.domain.UserProfile;
import com.adcc.validator.dto.PasswordValidationRequest;
import com.adcc.validator.dto.PasswordValidationResponse;

public interface PasswordValidationService {
	
	public PasswordValidationResponse validate(UserProfile user, PasswordValidationRequest request);


}
