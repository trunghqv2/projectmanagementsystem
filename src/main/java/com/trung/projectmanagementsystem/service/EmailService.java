package com.trung.projectmanagementsystem.service;

import com.trung.projectmanagementsystem.model.entity.Account;

public interface EmailService {

    
    void sendActiveAccountRegistrationEmail(Account account , String registrationToken);

    void sendForgotPasswordEmail(Account account ,String key);

    void sendChangePasswordEmail(Account account);

}
