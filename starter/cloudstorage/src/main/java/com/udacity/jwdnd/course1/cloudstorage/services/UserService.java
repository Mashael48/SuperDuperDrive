package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;

}
