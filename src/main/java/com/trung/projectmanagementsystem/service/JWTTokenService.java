package com.trung.projectmanagementsystem.service;

import org.springframework.security.core.Authentication;

import com.trung.projectmanagementsystem.model.dto.auth.TokenDTO;
import com.trung.projectmanagementsystem.model.entity.Account;
import com.trung.projectmanagementsystem.model.entity.Token;

import jakarta.servlet.http.HttpServletRequest;

public interface JWTTokenService {
    String generateJWTToken(String username);

	Authentication parseTokenToUserInformation(HttpServletRequest request);

	Token generateRefreshToken(Account account);

	boolean isRefreshTokenValid(String refreshToken);

	TokenDTO getNewToken(String refreshToken);
	
	void deleteRefreshToken(Account account);
}