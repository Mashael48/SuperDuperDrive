package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileMapper fileMapper;

}
