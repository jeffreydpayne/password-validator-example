package com.adcc.validator.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import sun.swing.StringUIClientPropertyKey;

import com.adcc.validator.domain.UserProfile;
import com.adcc.validator.dto.PasswordValidationRequest;
import com.adcc.validator.dto.PasswordValidationResponse;
import com.adcc.validator.service.PasswordValidationService;

@Service
public class PasswordValidationServiceImpl implements PasswordValidationService {
	
	
	private int repeatThreshold = 2;
	
	

	@Override
	public PasswordValidationResponse validate(UserProfile user, PasswordValidationRequest request) {
		
		PasswordValidationResponse response = new PasswordValidationResponse();
		response.setValid(false);
		
		if (StringUtils.isEmpty(request.getAttemptedPassword())) {
			
			response.setReasonUnlocalized("Password cannot be empty or null.");
			return response;
		}
		
		String newHash = computeHash(user, request.getAttemptedPassword());
		
		
		
		if (user.getPreviousPasswordHashes() != null) {
			if (user.getPreviousPasswordHashes().contains(newHash)) {
				response.setReasonUnlocalized("Previously used password");
				return response;
			}
		}
		
		
		
		if (request.getAttemptedPassword().length() < 5) {
			response.setReasonUnlocalized("Password is too short");
			return response;
		}
		else if (request.getAttemptedPassword().length() > 12) {
			response.setReasonUnlocalized("Password is too long");
			return response;
		}
		
		for (char c : request.getAttemptedPassword().toCharArray()) {
			if (!Character.isLowerCase(c) && !Character.isDigit(c) ) {
				response.setReasonUnlocalized("Passwords must contain only lower case letters and numbers.");
				return response;
			}
			
		}
		
		/*
		 * I'm not sure if you're looking for a RegEx approach here or to see if I can cook up
		 * my own algorithm.  I tend to discourage the use of RegEx due to maintainability, so I
		 * went the for the second approach.
		 * 
		 * This is n squared, which is not ideal, but hopefully close enough for a quick first draft.
		 * 
		 */
		
		for (int index = repeatThreshold ; index < request.getAttemptedPassword().length() - repeatThreshold; index++) {
			String leftSide = request.getAttemptedPassword().substring(0, index);
			String rightSide = request.getAttemptedPassword().substring(index, request.getAttemptedPassword().length());
			
			for (int subIndex = leftSide.length() - repeatThreshold ; subIndex >= 0; subIndex--) {
				String matchFragment = leftSide.substring(subIndex, leftSide.length());
				if (rightSide.startsWith(matchFragment)) {
					response.setReasonUnlocalized("Cannot repeat character sequences in passwords.");
					return response;
				}
			}
			
					
					
		}
		
		
		
		response.setValid(true);
		return response;
	}
	
	public String computeHash(UserProfile user, String rawPassword) {
		
		/*
		 * Adding a salt to the hash.
		 * Would probably use an ISO formatted version of the timestamp
		 * in a real project instead of millis - and of course the hash computing code would 
		 * have its own service or live in a central user or password service.
		 * 
		 */
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(String.valueOf(user.getRegistrationDate().getTime()));
		sb.append("}");
		sb.append(rawPassword);
		
		return DigestUtils.sha256Hex(sb.toString());
		
	}

	
	
}
