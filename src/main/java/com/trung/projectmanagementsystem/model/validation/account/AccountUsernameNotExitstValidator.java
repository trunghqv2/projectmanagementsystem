package com.trung.projectmanagementsystem.model.validation.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountUsernameNotExitstValidator implements ConstraintValidator<AccountUsernameNotExists, String> {

    @Autowired
    private AccountService service;

    @SuppressWarnings("Deprecation")
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(username)) {
            return true;
        }
        return !service.isAccountExistsByUserName(username);
    }

}
