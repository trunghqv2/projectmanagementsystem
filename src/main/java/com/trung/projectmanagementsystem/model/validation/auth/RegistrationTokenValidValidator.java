package com.trung.projectmanagementsystem.model.validation.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.trung.projectmanagementsystem.service.TokenService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegistrationTokenValidValidator implements ConstraintValidator<RegistrationTokenValid, String> {

	@Autowired
	private TokenService tokenService;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String registrationToken, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(registrationToken)) {
			return false;
		}

		return tokenService.isRegistrationTokenValid(registrationToken);
	}
}
