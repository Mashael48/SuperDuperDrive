package com.udacity.jwdnd.course1.cloudstorage.utils;

import java.net.InetAddress;

public interface Constants {

	// ** Server ** //
	public static final String IP = InetAddress.getLoopbackAddress().getHostAddress();

	// ** Pages ** //
	public static final String LOGIN_PAGE = "login";
	public static final String SIGNUP_PAGE = "signup";
	public static final String LOGOUT_PAGE = "logout";
	public static final String HOME_PAGE = "home";
	public static final String FILE_PAGE = "file";
	public static final String NOTE_PAGE = "note";
	public static final String CREDENTIAL_PAGE = "credential";
	public static final String RESULT_PAGE = "result";

	// ** Lists ** //
	public static final String FILES_LIST = "filesList";
	public static final String NOTES_LIST = "notesList";
	public static final String CREDENTIALS_LIST = "credentialsList";

	// ** FE variable ** //
	public static final String SIGNUP_SUCCESS = "signupSuccess";
	public static final String SIGNUP_ERROR = "signupError";
	public static final String FILE_FORM = "fileForm";
	public static final String CREDENTIAL_SERVICE = "credentialService";

	// ** Messages Status ** //
	public static final String SUCCESS = "success";
	public static final String NOT_SAVED = "notSaved";
	public static final String ERROR = "error";

	// ** Error messages ** //
	public static final String MESSAGE = "message";
	public static final String GENERAL_ERROR_MESSAGE = "There was an error signing you up. Please try again.";
	public static final String DUBLICATE_USERNAME = "The username already exists.";
	public static final String DUBLICATE_FILE_NAME = "File name is already in-use!";

}
