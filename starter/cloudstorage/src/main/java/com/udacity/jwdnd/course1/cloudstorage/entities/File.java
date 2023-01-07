package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
	private Integer fileId;
	private String fileName;
	private String contentType;
	private String fileSize;
	private byte[] fileData;
	private Integer userId;
}
