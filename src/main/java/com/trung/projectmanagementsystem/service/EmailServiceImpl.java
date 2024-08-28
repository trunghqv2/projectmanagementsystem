package com.trung.projectmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.trung.projectmanagementsystem.model.entity.Account;
@Service
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

    @Autowired
    private JavaMailSender mailSender;

    private void SendEmail(String recipientEmail, String subject, String content) {
        SimpleMailMessage messsage = new SimpleMailMessage();
        messsage.setTo(recipientEmail);
        messsage.setSubject(subject);
        messsage.setText(content);

        mailSender.send(messsage);
    }

    @Override
    public void sendActiveAccountRegistrationEmail(Account account, String registrationToken) {
        String activeAccountUrl = String.format("%s://%s:%d/api/v1/auth/registration/active?registrationToken=%s",
                SERVER_PROTOCOL,
                SERVER_HOSTNAME,
                SERVER_PORT,
                registrationToken);
        String subject = "Active Account";
        String content = "You have successfully registered an account\n"
                + "Click on the link below to active account\n" + activeAccountUrl;
        SendEmail(account.getEmail(), subject, content);
    }

    @Override
    public void sendForgotPasswordEmail(Account account, String forgotPasswordToken) {
        String forgotPasswordUrl = String.format("%s://%s:%d/html/new-password?forgotPasswordToken=%s",
                SERVER_PROTOCOL,
                SERVER_HOSTNAME,
                SERVER_PORT,
                forgotPasswordToken);

        String subject = "Forgot Password";
        String content = " You have just sent forgot passsword request\n"
                + "click on the link below to the new password\n" + forgotPasswordUrl;

        SendEmail(account.getEmail(), subject, content);
    }

    @Override
    public void sendChangePasswordEmail(Account account) {
        String subject = "Change Password";
        String content = "You have just change your password";

        SendEmail(account.getEmail(), subject, content);
    }
}
