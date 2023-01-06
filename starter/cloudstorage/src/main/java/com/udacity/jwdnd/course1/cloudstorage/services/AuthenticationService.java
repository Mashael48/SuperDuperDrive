package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO: Add embedded database checking
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		log.info("usename: {}, password: {}", username, password);
		if (username != null)
			return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());

		return null;
		// TODO: Add embedded database checking
		/*
		 * User user = userMapper.getUser(username); if (user != null) { String
		 * encodedSalt = user.getSalt(); String hashedPassword =
		 * hashService.getHashedValue(password, encodedSalt); if
		 * (user.getPassword().equals(hashedPassword)) { return new
		 * UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()); }
		 * }
		 */

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
