package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationProvider {

	private final UserMapper userMapper;
	private final HashService hashService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = userMapper.getUser(username);
		if (user != null) {
			log.info("User exist!");

			String encodedSalt = user.getSalt();
			String hashedPassword = hashService.getHashedValue(password, encodedSalt);

			if (user.getPassword().equals(hashedPassword)) {
				return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
			}
		}

		log.warn("User does not exist!");
		return null;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
