package com.udacity.jwdnd.course1.cloudstorage.entities;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileForm {
	private MultipartFile file;
}
