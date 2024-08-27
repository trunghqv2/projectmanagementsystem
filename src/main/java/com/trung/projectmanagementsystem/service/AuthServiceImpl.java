package com.trung.projectmanagementsystem.service;

import java.text.Normalizer;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.trung.projectmanagementsystem.model.dto.auth.LoginInfoDTO;
import com.trung.projectmanagementsystem.model.entity.Account;
import com.trung.projectmanagementsystem.model.entity.Account.Status;
import com.trung.projectmanagementsystem.model.form.account.CreatingAccountForm;
import com.trung.projectmanagementsystem.model.form.auth.ChangePasswordForm;
import com.trung.projectmanagementsystem.model.form.auth.ResetPasswordForm;
import com.trung.projectmanagementsystem.reponository.IAccountRepository;

public class AuthServiceImpl extends BaseService implements AuthService {

    @Autowired
    private JWTTokenService jwtTokenService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;
    
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired
	private SecurityUtils securityUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public LoginInfoDTO login(String username) throws AccountBlockException {

        Account entity = accountService.getAccountByUsername(username);

       if(entity.getStatus() == Status.BLOCK){
        throw new AccountBlockException("Your account is blocked!");
       }

       LoginInfoDTO dto = converObjectToObject(entity, LoginInfoDTO.class);

       dto.setToken(jwtTokenService.generateRefreshToken(entity.getUsername()));
       Token token = jwtTokenService.generateRefreshToken(entity);
       dto.setRefreshToken(token.getKey());
       return dto;
    }

    @Override
    public void createAccountRegistrationTokenViaEmail(String usename) {
       

    }

    @Override
    public void createAccount(CreatingAccountForm form) {
        Account account = converObjectToObject(form,Account.class);
        Token newRegistrationToken=tokenService.generateAccountRegistrationToken(account);
        emailService.sendActiveAccountRegistrationEmail(account,newRegistrationToken.getKey());
    }

    @Override
    public void acticeAccount(String registrationToken) {
        Token token = tokenService.getRegistrationTokenByKey(registrationToken);
        Account account = token.getAccount();
        account.setStatus(Status.ACTIVE);
        account.setUpdateDateTime(new Date());
        accountRepository.save(account);

        tokenService.deleteAccountRegistrationToken(account);
    }

    @Override
    public void sendAccountForgotPasswordTokenViaEmail(String usernameOrEmail) {
        Account account =accountRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        Token newForgotPasswordToken = tokenService.generateForgotPasswordToken(account);

        emailService.sendAccountForgotPasswordEmail(account, newForgotPasswordToken.getKey());
    }

    @Override
    public void resetPassword(ResetPasswordForm form) {
        Token token = TokenService.getForgotPasswordTokenByKey(form.getForgotPasswordToken());

        Account account = token.getAccount();
        account.setPassword(passwordEncoder.encode(form.getNewPassword()));
        account.setUpdateDateTime(new Date());
        account.setLastChangePasswordDateTime(new Date());

        tokenService.deleteForgotPasswordToken(account);

        jwtTokenService.deleteRefreshToken(account);

    }

    @Override
    public void changePassword(ChangePasswordForm form) {
       Account account = securityUtils.getCurrentAccountLogin();
       account.setPassword(passwordEncoder.encode(form.getNewPassword()));
       account.setUpdateDateTime(new Date());
       account.getLastChangePasswordDateTime(new Date());
       accountRepository.save(account);

       jwtTokenService.deleteRefreshToken(account);

       emailService.sendChangePasswordEmail(account);
    }
    
}
