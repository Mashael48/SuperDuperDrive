package com.udacity.jwdnd.course1.cloudstorage;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.pages.*;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests {

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
		String noteTitle = "Note title";
		String noteDescription = "Note description...";
		HomePage homePage = new HomePage(driver);
		createNote(homePage, noteTitle, noteDescription);

		// Test viewing
		homePage.navToNotesTab();
		homePage = new HomePage(driver);
		Note note = homePage.getFirstNote();
		Assertions.assertEquals(noteTitle, note.getNoteTitle());
		Assertions.assertEquals(noteDescription, note.getNoteDescription());
		homePage.logout();
	}

	@Test
	public void testEdit() {
		// Create new note
		HomePage homePage = new HomePage(driver);
		creatNewNote(homePage);

		// Edit note
		homePage.editNote();
		String modifiedNoteTitle = "Modified tite";
		homePage.modifyNoteTitle(modifiedNoteTitle);
		String modifiedNoteDescription = "Modified note description...";
		homePage.modifyNoteDescription(modifiedNoteDescription);
		homePage.saveNoteChanges();

		successToNoteTab(homePage);
		Note note = homePage.getFirstNote();
		Assertions.assertEquals(modifiedNoteTitle, note.getNoteTitle());
		Assertions.assertEquals(modifiedNoteDescription, note.getNoteDescription());
	}

	@Test
	public void testDelete() {
		// Create new note
		HomePage homePage = new HomePage(driver);
		creatNewNote(homePage);

		// Delete
		homePage = new HomePage(driver);
		Assertions.assertFalse(homePage.noNotes(driver));
		deleteNote(homePage);
		Assertions.assertTrue(homePage.noNotes(driver));
	}

	public void creatNewNote(HomePage homePage) {
		String noteTitle = "Note title";
		String noteDescription = "Note description...";
		createNote(homePage, noteTitle, noteDescription);
	}

	private void createNote(HomePage homePage, String noteTitle, String noteDescription) {
		homePage.navToNotesTab();
		homePage.addNewNote();
		homePage.setNoteTitle(noteTitle);
		homePage.setNoteDescription(noteDescription);
		homePage.saveNoteChanges();
		successToNoteTab(homePage);
	}

	private void deleteNote(HomePage homePage) {
		homePage.deleteNote();
		successToNoteTab(homePage);
	}

	private void successToNoteTab(HomePage homePage) {
		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertTrue(resultPage.getSuccessMsg().contains("successfully"));
		resultPage.clickOk();
		homePage.navToNotesTab();
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
