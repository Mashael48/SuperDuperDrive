package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class Credential {
	private Integer credentialid;
	private String url;
	private String username;
	private String key;
	private String password;
	private Integer userid;
}
