package com.lawencon.booting.security;

import java.io.IOException;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.LoginHelper;
import com.lawencon.booting.service.AccountsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager am;
	
	private AccountsService accountsService;
		
	public AuthenticationFilter(AuthenticationManager am, AccountsService accountsService) {
		this.am = am;
		this.accountsService = accountsService;
		super.setFilterProcessesUrl("/api/login");
	}	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Accounts acc = new Accounts();
		try {
			acc = new ObjectMapper().readValue(request.getInputStream(), Accounts.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return am.authenticate(new UsernamePasswordAuthenticationToken(acc.getEmail(), acc.getPass()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String secretKey = "$2y$12$pYlKn7Tybq/emyVvZcuE2eN9xTRC5kEUrnDLwbIfZQgh4gprma8r2";
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
		long expiredDate = 9999999;
		
		String tokenStr = Jwts.builder()
				.signWith(key)
				.setSubject(authResult.getName())
				.setExpiration(new Date(new Date().getTime()+expiredDate))
				.compact();
		
		Accounts account = new Accounts();
		account.setEmail(authResult.getName());
		LoginHelper logHelp = new LoginHelper();
		
		try {
			Accounts acc = accountsService.findByEmail(account);
			acc.setPass(null);
			logHelp.setAccount(acc);
			logHelp.setToken(tokenStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(logHelp.getToken() + " -- " + logHelp.getAccount().getIdUser().getIdCompany().getName());
		
		response.setContentType("application/json");
		response.getWriter().append(new ObjectMapper().writeValueAsString(logHelp));
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}
}
