package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CredentialService {

	private final CredentialMapper credentialMapper;
	private final EncryptionService encryptionService;

	public int createCredential(Credential credential) {
		log.info("createCredential: {}", credential);
		encodePasswordWithKey(credential);
		return credentialMapper.insert(credential);
	}

	public int updateCredential(Credential credential) {
		log.info("updateCredential: {}", credential);
		encodePasswordWithKey(credential);
		return credentialMapper.update(credential);
	}

	public String getDecodedPassword(String password, String key) {
		log.info("getDecodedPassword: {} {}", password, key);
		return encryptionService.decryptValue(password, key);
	}

	public int deleteCredential(Integer credentialId) {
		log.info("deleteCredential: {}", credentialId);
		return credentialMapper.delete(credentialId);
	}

	public List<Credential> getCredentialsList(Integer userId) {
		List<Credential> credentials = credentialMapper.getUserCredentialsList(userId);
		log.info("getCredentialsList: {}", credentials);
		return credentials;
	}

	private void encodePasswordWithKey(Credential credential) {

		String encodedKey = generateEncodeKey();
		String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

		credential.setKey(encodedKey);
		credential.setPassword(encryptedPassword);
	}

	private static String generateEncodeKey() {

		SecureRandom random = new SecureRandom();
		byte[] key = new byte[16];
		random.nextBytes(key);
		String encodedKey = Base64.getEncoder().encodeToString(key);

		return encodedKey;
	}
}
