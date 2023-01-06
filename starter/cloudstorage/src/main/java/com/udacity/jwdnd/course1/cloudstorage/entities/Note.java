package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class Note {
	private Integer noteid;
	private String notetitle;
	private String notedescription;
	private Integer userid;
}
