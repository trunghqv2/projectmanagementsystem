package com.trung.projectmanagementsystem.model.validation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.trung.projectmanagementsystem.service.AccountService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountEmailNotExistsValidator implements ConstraintValidator<AccountEmailNotExists, String> {

	@Autowired
	private AccountService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(email)) {
			return true;
		}

		return !service.isAccountExistsByEmail(email);
	}
}