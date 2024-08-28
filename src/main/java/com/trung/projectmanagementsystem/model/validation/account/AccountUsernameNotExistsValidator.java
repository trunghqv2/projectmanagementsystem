package com.trung.projectmanagementsystem.model.validation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.trung.projectmanagementsystem.service.AccountService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountUsernameNotExistsValidator implements ConstraintValidator<AccountUsernameNotExists, String> {

	@Autowired
	private AccountService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(username)) {
			return true;
		}

		return !service.isAccountExistsByUsername(username);
	}
}