package com.adcc.validator.view.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adcc.validator.domain.UserProfile;
import com.adcc.validator.dto.PasswordValidationRequest;
import com.adcc.validator.dto.PasswordValidationResponse;
import com.adcc.validator.service.PasswordValidationService;


@Controller
public class PasswordValidationController {
	
	public final static String KEY_USER = "_current_user";

	
	@Autowired
	private PasswordValidationService validationService;
	
	@RequestMapping(method=RequestMethod.POST, value="/validate-password")
	public @ResponseBody PasswordValidationResponse validatePassword(HttpServletRequest httpRequest, @RequestBody PasswordValidationRequest request) throws Exception {
		
		UserProfile user = (UserProfile)httpRequest.getSession().getAttribute(KEY_USER);
		
		return validationService.validate(user, request);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/validate-password/${password}")
	public @ResponseBody PasswordValidationResponse validatePassword(HttpServletRequest httpRequest, @PathVariable String password) throws Exception {
		
		
		PasswordValidationRequest request = new PasswordValidationRequest();
		request.setAttemptedPassword(password);
		
		return validatePassword(httpRequest, request);
		
	}
	
	
}
