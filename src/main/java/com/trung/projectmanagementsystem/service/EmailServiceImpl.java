package com.trung.projectmanagementsystem.service;

import org.springframework.beans.factory.annotation.Value;

import com.trung.projectmanagementsystem.model.entity.Account;

public class EmailServiceImpl extends BaseService implements EmailService {

    @Value("$(server.protocol)")
    private String SERVER_PROTOCOL;

    @Value("$(server.hostname)")
    private String SERVER_HOSTNAME;

    @Value("$(server.port)")
    private String SERVER_PORT;

    @Value("$(frontend.protocol)")
    private String FRONTEND_HOSTNAME;
    
    @Value("$(frontend.port)")
    private String FRONTEND_PORT;
    
    
    
    @Override
    public void sendActiveAccountRegistrationEmail(Account account, String registrationToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendActiveAccountRegistrationEmail'");
    }

    @Override
    public void sendForgotPasswordEmail(Account account, String key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendForgotPasswordEmail'");
    }

    @Override
    public void sendChangePasswordEmail(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendChangePasswordEmail'");
    }
    
}
