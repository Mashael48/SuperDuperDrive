package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialService {

	private final CredentialMapper credentialMapper;

}
