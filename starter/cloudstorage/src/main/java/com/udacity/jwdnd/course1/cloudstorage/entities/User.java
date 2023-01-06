package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class User {
	private Integer userId;
	private String username;
	private String salt;
	private String password;
	private String firstName;
	private String lastName;
}
