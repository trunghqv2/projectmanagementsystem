package com.trung.projectmanagementsystem.service;

import com.trung.projectmanagementsystem.config.security.AccountBlockException;
import com.trung.projectmanagementsystem.model.dto.auth.LoginInfoDTO;
import com.trung.projectmanagementsystem.model.form.account.CreatingAccountForm;
import com.trung.projectmanagementsystem.model.form.auth.ChangePasswordForm;
import com.trung.projectmanagementsystem.model.form.auth.ResetPasswordForm;

public interface AuthService {
    LoginInfoDTO login(String username) throws AccountBlockException;

	void createAccount(CreatingAccountForm form);

	void sendAccountRegistrationTokenViaEmail(String username);

	void activeAccount(String registrationToken);

	void sendAccountForgotPasswordTokenViaEmail(String usernameOrEmail);

	void resetPassword(ResetPasswordForm form);

	void changePassword(ChangePasswordForm form);
}