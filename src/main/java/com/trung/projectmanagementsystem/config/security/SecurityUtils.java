package com.trung.projectmanagementsystem.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.trung.projectmanagementsystem.model.entity.Account;
import com.trung.projectmanagementsystem.reponository.IAccountRepository;


@Configuration
public class SecurityUtils {

	@Autowired
	private IAccountRepository accountRepository;

	public Account getCurrentAccountLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) return null;
		return accountRepository.findByUsername(authentication.getName());
	}

}
