package com.udacity.jwdnd.course1.cloudstorage;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.pages.*;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UAMTests {

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
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testUnloggedUsersAccess() {
		// Test pages can access:
		goLogin();
		goSignup();

		// Test page that cannot access
		driver.get(URL + "/" + HOME_PAGE);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testHomeAfterLogout() {

		String username = "username";
		String pass = "password";

		goSignup();
		signUser(username, pass);

		goLogin();
		loginUser(username, pass);

		goHome();

		HomePage homePage = new HomePage(driver);
		homePage.logout();

		driver.get(URL + "/" + HOME_PAGE);
		Assertions.assertEquals("Login", driver.getTitle());
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
