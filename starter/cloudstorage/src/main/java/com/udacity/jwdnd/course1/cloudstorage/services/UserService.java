package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final HashService hashService;

	public boolean isUsernameAvailable(String username) {
		return userMapper.getUser(username) == null;
	}

	public int createUser(User user) {

		String encodedSalt = generateEncodedSalt();
		String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

		user.setSalt(encodedSalt);
		user.setPassword(hashedPassword);

		log.info("createUser: {}", user);
		return userMapper.insert(user);
	}

	public User getUser(String username) {
		User user = userMapper.getUser(username);
		log.info("getUser: {}" + user);
		return user;
	}

	private static String generateEncodedSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		String encodedSalt = Base64.getEncoder().encodeToString(salt);

		return encodedSalt;
	}
}
