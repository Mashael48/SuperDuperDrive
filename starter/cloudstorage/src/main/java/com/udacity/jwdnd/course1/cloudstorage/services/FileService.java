package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileMapper fileMapper;

	public boolean isFileNameAvailable(String fileName) {
		return fileMapper.getFile(fileName) == null;
	}

	public File getFile(String fileName) {
		return fileMapper.getFile(fileName);
	}

	public List<File> getFilesList(Integer userId) {
		return fileMapper.getUserFilesList(userId);
	}

	@SneakyThrows
	public void addFile(MultipartFile multipartFile, Integer userId) {

		InputStream fis = multipartFile.getInputStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];

		while ((nRead = fis.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();

		byte[] fileData = buffer.toByteArray();
		String fileName = multipartFile.getOriginalFilename();
		String contentType = multipartFile.getContentType();
		String fileSize = String.valueOf(multipartFile.getSize());

		File file = new File(null, fileName, contentType, fileSize, fileData, userId);
		fileMapper.insert(file);
	}
}
