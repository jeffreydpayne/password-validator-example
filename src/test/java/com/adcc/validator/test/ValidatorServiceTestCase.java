package com.adcc.validator.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.adcc.validator.domain.UserProfile;
import com.adcc.validator.dto.PasswordValidationRequest;
import com.adcc.validator.view.api.PasswordValidationController;

@Test
@ContextConfiguration(locations = { "classpath:spring-env-test.xml" })
public class ValidatorServiceTestCase extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private PasswordValidationController validationController;

	public void testValidator() throws Exception {
		
		String[] invalidPasswords = new String[] {
			"bambambam1",
			"afoofoods1",
			"srt1",
			"toolongtoolongtoolong2",
			"CapitalLetters",
			"12323343",
			"##$@#$$",
			
		};
		
		String[] validPasswords = new String[] {
			"heythere42",
			"bamabam25",
			"12345a",
			"a12345"
				
			};
		
		
		UserProfile user = new UserProfile();
		user.setRegistrationDate(new Date());
		
		PasswordValidationRequest request = new PasswordValidationRequest();

		MockHttpServletRequest httpRequest = new MockHttpServletRequest();
		httpRequest.getSession(true).setAttribute("_current_user", user);
		
		for (String invalidPassword : invalidPasswords) {
			request.setAttemptedPassword(invalidPassword);
			Assert.assertFalse(validationController.validatePassword(httpRequest, request).isValid(), invalidPassword + " was validated incorrectly.");
		}
		
		
		for (String validPassword : validPasswords) {
			request.setAttemptedPassword(validPassword);
			Assert.assertTrue(validationController.validatePassword(httpRequest, request).isValid(), validPassword + " was invalidated incorrectly.");
		}
		
		
	}
	
}
