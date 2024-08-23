package com.trung.projectmanagementsystem.model.validation.account;

import org.springframework.beans.factory.annotation.Autowired;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountUserNameExistsValidator implements ConstraintValidator<Annotation, Object> {
    @Autowired
    Private AccountService service;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Object username, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(username)){
            return true;
        }
        return service.isAccountExistsByUserName(username);
    }

}