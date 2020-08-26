package com.lawencon.booting.security;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AutorizationFilter extends BasicAuthenticationFilter {

	public AutorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header == null || header.isEmpty() || !header.startsWith("Bearer")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		
		String secretKey = "$2y$12$pYlKn7Tybq/emyVvZcuE2eN9xTRC5kEUrnDLwbIfZQgh4gprma8r2";
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
		String nikOrUsername = null;
		
		try {
			
			String body = header.replaceFirst("Bearer ", "");
			nikOrUsername = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(body)
					.getBody()
					.getSubject();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		
		Authentication auth = new UsernamePasswordAuthenticationToken(nikOrUsername, null, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		chain.doFilter(request, response);		
	}
	
}
