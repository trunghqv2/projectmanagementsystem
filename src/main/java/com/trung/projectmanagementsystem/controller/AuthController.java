package com.trung.projectmanagementsystem.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.trung.projectmanagementsystem.model.dto.auth.LoginInfoDTO;
import com.trung.projectmanagementsystem.model.dto.auth.TokenDTO;
import com.trung.projectmanagementsystem.model.form.account.CreatingAccountForm;
import com.trung.projectmanagementsystem.model.form.auth.LoginForm;
import com.trung.projectmanagementsystem.model.validation.account.AccountUserNameExists;
import com.trung.projectmanagementsystem.model.validation.auth.RefreshTokenValid;
import com.trung.projectmanagementsystem.model.validation.auth.RegistrationTokenValid;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trung.projectmanagementsystem.model.form.auth.ResetPasswordForm;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trung.projectmanagementsystem.model.form.auth.ChangePasswordForm;


@RestController
@RequestMapping(value = "api/v1/auth")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTTokenService jWTTokenService;

    @PostMapping("/login")
    public LoginInfoDTO login(@RequestBody @Valid LoginForm loginForm) throws AccountBlockException {
        Authentication authentication = AuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authService.login(loginForm.getUsername());
    }

    @GetMapping("/refreshtoken")
    public TokenDTO refreshtoken(@RefreshTokenValid String refeshToken) {
        return jWTTokenService.getNewToken(refeshToken);
    }

    @GetMapping("/registration")
    public String createAccount(@Valid @RequestBody CreatingAccountForm creatingAccountForm) {
        return new String();
    }

    @GetMapping("/registration/active-mail")
    public String resendAccountRegistrationTokenViaEmail(@RequestParam @AccountUserNameExists String username) {
        authService.sendAccountRegistrationTokenViaEmail(username);
        return "We have sent a email. Please check email to active account!";
    }

    @GetMapping("/registration/acitve")
    public String activeAccountViaEmail(@RequestParam @RegistrationTokenValid String registrationToken) {
        authService.acitveAccount(registrationToken);
        return "Active success!";
    }
    @GetMapping("/password/new-password")
    public String resetPasswordViaEmail(@Valid @RequestBody ResetPasswordForm form) {
        authService.ResetPassword(form);
        return "Change password successfully";
    }
   @GetMapping("/password/forgot-mail")
   public String sendpAccountForgotPasswordTokenViaEmail (@RequestParam @AccountUserNameOrEmailExists String usernameOrEmai) {
      authService.sendpAccountForgotPasswordTokenViaEmail(usernameOrEmai);
      return "Please check your email!";
   }
   @PutMapping("/password/change")
   public String changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm) {
       authService.changePassword(form);
       return "Change password successfully!";
   }

}
