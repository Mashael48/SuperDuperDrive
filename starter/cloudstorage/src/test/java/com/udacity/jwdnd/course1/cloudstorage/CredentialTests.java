package com.udacity.jwdnd.course1.cloudstorage;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pages.*;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {

	@LocalServerPort
	private int port;
	private String URL;
	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		URL = "http://" + IP + ":" + port;
		newLogin();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testCreate() {
		// Test creation
		String url1 = "https://www.google.com/";
		String username1 = "username1";
		String pass1 = "password1";

		String url2 = "https://translate.google.com/";
		String username2 = "username2";
		String pass2 = "password2";

		HomePage homePage = new HomePage(driver);
		homePage.navToCredentialsTab();
		createCredential(homePage, url1, username1, pass1);
		createCredential(homePage, url2, username2, pass2);

		// Test viewing
		Assertions.assertFalse(homePage.noCredentials(driver));
		verifyViewFirstCredential(homePage, url1, username1, pass1);

		// Delete two credentials
		deleteCredential(homePage);
		deleteCredential(homePage);
		homePage.logout();
	}

	@Test
	public void testEdit() {
		// Create
		String url1 = "https://www.google.com/";
		String username1 = "username1";
		String pass1 = "password1";

		HomePage homePage = new HomePage(driver);
		homePage.navToCredentialsTab();
		createCredential(homePage, url1, username1, pass1);
		verifyViewFirstCredential(homePage, url1, username1, pass1);

		// save first view pass
		Credential originalCredential = homePage.getFirstCredential();
		String firstEncryptedPassword = originalCredential.getPassword();

		// Edit
		String url2 = "https://translate.google.com/";
		String username2 = "username2";
		String pass2 = "password2";

		homePage.editCredential();
		setCredentialFields(homePage, url2, username2, pass2);
		homePage.saveCredentialChanges();
		successToCredentialsTab(homePage);

		Credential modifiedCredential = homePage.getFirstCredential();
		Assertions.assertEquals(url2, modifiedCredential.getUrl());
		Assertions.assertEquals(username2, modifiedCredential.getUsername());

		String modifiedCredentialPassword = modifiedCredential.getPassword();
		Assertions.assertNotEquals(pass2, modifiedCredentialPassword);

		// Validate encrypted password
		Assertions.assertNotEquals(firstEncryptedPassword, modifiedCredentialPassword);

		deleteCredential(homePage);
		homePage.logout();
	}

	@Test
	public void testDelete() {
		// Create two credentials
		HomePage homePage = new HomePage(driver);
		createNewCredentials(homePage);

		// Delete two credentials
		deleteCredential(homePage);
		deleteCredential(homePage);

		Assertions.assertTrue(homePage.noCredentials(driver));
		homePage.logout();
	}

	private void createNewCredentials(HomePage homePage) {
		String url1 = "https://www.google.com/";
		String username1 = "username1";
		String pass1 = "password1";

		String url2 = "https://translate.google.com/";
		String username2 = "username2";
		String pass2 = "password2";

		homePage.navToCredentialsTab();
		createCredential(homePage, url1, username1, pass1);
		createCredential(homePage, url2, username2, pass2);
		Assertions.assertFalse(homePage.noCredentials(driver));
	}

	private void createCredential(HomePage homePage, String url, String username, String password) {
		homePage.addNewCredential();
		setCredentialFields(homePage, url, username, password);
		homePage.saveCredentialChanges();
		successToCredentialsTab(homePage);
	}

	private void verifyViewFirstCredential(HomePage homePage, String url, String username, String password) {
		Credential credential = homePage.getFirstCredential();
		Assertions.assertEquals(url, credential.getUrl());
		Assertions.assertEquals(username, credential.getUsername());
		Assertions.assertNotEquals(password, credential.getPassword());
	}

	private void deleteCredential(HomePage homePage) {
		homePage.deleteCredential();
		successToCredentialsTab(homePage);
	}

	private void setCredentialFields(HomePage homePage, String url, String username, String password) {
		homePage.setCredentialUrl(url);
		homePage.setCredentialUsername(username);
		homePage.setCredentialPassword(password);
	}

	private void successToCredentialsTab(HomePage homePage) {
		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertTrue(resultPage.getSuccessMsg().contains("successfully"));
		resultPage.clickOk();
		homePage.navToCredentialsTab();
	}

	private void newLogin() {
		String username = "username";
		String pass = "password";
		goSignup();
		signUser(username, pass);
		goLogin();
		loginUser(username, pass);
		goHome();
	}

	private void signUser(String username, String pass) {
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signUpUser("First", "Last", username, pass);
	}

	private void loginUser(String username, String pass) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(username, pass);
	}

	private void goLogin() {
		driver.get(URL + "/" + LOGIN_PAGE);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	private void goSignup() {
		driver.get(URL + "/" + SIGNUP_PAGE);
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	private void goHome() {
		driver.get(URL + "/" + HOME_PAGE);
		Assertions.assertEquals("Home", driver.getTitle());
	}
}
