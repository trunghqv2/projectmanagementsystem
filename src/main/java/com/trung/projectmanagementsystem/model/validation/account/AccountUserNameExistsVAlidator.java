package com.trung.projectmanagementsystem.model.validation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.trung.projectmanagementsystem.service.AccountServiceImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountUsernameExistsValidator implements ConstraintValidator<AccountUsernameExists, String> {

	@Autowired
	private AccountServiceImpl service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(username)) {
			return true;
		}

		return service.isAccountExistsByUsername(username);
	}
}