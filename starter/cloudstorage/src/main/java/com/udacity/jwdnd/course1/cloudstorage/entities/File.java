package com.udacity.jwdnd.course1.cloudstorage.entities;

import java.sql.Blob;

import lombok.Data;

@Data
public class File {
	private Integer fileId;
	private String filename;
	private String contenttype;
	private Integer filesize;
	private Blob filedata;
}
