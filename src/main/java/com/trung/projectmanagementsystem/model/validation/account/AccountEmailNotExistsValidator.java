package com.trung.projectmanagementsystem.model.validation.account;

import org.apache.catalina.util.StringUtil;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountEmailNotExistsValidator implements ConstraintValidator<Annotation, Object> {

    @Autowired
    private AccountSevice service;
    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
       if (StringUtil.isEmty(email)){
        return true;
       }
       return !service.isAccountExistsByEmail(email);
    }

}
