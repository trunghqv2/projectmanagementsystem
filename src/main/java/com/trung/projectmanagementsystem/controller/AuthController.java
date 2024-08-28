package com.trung.projectmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trung.projectmanagementsystem.config.security.AccountBlockException;
import com.trung.projectmanagementsystem.model.dto.auth.LoginInfoDTO;
import com.trung.projectmanagementsystem.model.dto.auth.TokenDTO;
import com.trung.projectmanagementsystem.model.form.account.CreatingAccountForm;
import com.trung.projectmanagementsystem.model.form.auth.ChangePasswordForm;
import com.trung.projectmanagementsystem.model.form.auth.LoginForm;
import com.trung.projectmanagementsystem.model.form.auth.ResetPasswordForm;
import com.trung.projectmanagementsystem.model.validation.account.AccountUsernameExists;
import com.trung.projectmanagementsystem.model.validation.account.AccountUsernameOrEmailExists;
import com.trung.projectmanagementsystem.model.validation.auth.RefreshTokenValid;
import com.trung.projectmanagementsystem.model.validation.auth.RegistrationTokenValid;
import com.trung.projectmanagementsystem.service.AuthService;
import com.trung.projectmanagementsystem.service.JWTTokenService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "api/v1/auth")
@Validated
public class AuthController {

	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JWTTokenService jwtTokenService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public LoginInfoDTO login(@RequestBody @Valid LoginForm loginForm) throws AccountBlockException{

		org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginForm.getUsername(), 
						loginForm.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return authService.login(loginForm.getUsername());
	}
	
	@GetMapping("/refreshToken")
	public TokenDTO refreshtoken(@RefreshTokenValid String refreshToken) {
		return jwtTokenService.getNewToken(refreshToken);
	}

	@PostMapping("/registration")
	public String createAccount(@Valid @RequestBody CreatingAccountForm form) {
		authService.createAccount(form);
		return "We have sent a email. Please check email to active account!";
	}

	@GetMapping("/registration/active-mail")
	public String resendAccountRegistrationTokenViaEmail(@RequestParam @AccountUsernameExists String username) {
		authService.sendAccountRegistrationTokenViaEmail(username);
		return "We have sent a email. Please check email to active account!";
	}

	@GetMapping("/registration/active")
	public String activeAccountViaEmail(@RequestParam @RegistrationTokenValid String registrationToken) {
		authService.activeAccount(registrationToken);
		return "Active success!";
	}
	
	@GetMapping("/password/forgot-mail")
	public String sendAccountForgotPasswordTokenViaEmail(@RequestParam @AccountUsernameOrEmailExists String usernameOrEmail) {
		authService.sendAccountForgotPasswordTokenViaEmail(usernameOrEmail);
		return "Email sent to your email! Please check it!";
	}
	
	@PutMapping("/password/new-password")
	public String resetPasswordViaEmail(@Valid @RequestBody ResetPasswordForm form) {
		authService.resetPassword(form);
		return "Change password successfully!";
	}
	
	@PutMapping("/password/change")
	public String changePassword(@Valid @RequestBody ChangePasswordForm form) {
		authService.changePassword(form);
		return "Change password successfully!";
	}
}