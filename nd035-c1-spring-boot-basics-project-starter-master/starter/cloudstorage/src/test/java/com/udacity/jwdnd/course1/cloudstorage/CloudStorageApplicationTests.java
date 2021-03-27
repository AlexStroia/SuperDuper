package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {
    protected WebDriver driver;
    @LocalServerPort
    protected int port;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        assertEquals("Login", driver.getTitle());
    }

    @Test
    @Order(1)
    public void signup() {
        driver.get("http://localhost:" + this.port + "/signup");
        Signup signupPage = new Signup(driver);
        signupPage.signup("Admin", "Admin", "admin", "admin");
    }

    @Test
    @Order(2)
    public void login() {
        driver.get("http://localhost:" + this.port + "/login");
        Login loginPage = new Login(driver);
        loginPage.login("admin", "admin");
    }

    @Test
    public void testLoginAndSignupPagesUnauthorized() {
        driver.get("http://localhost:" + this.port + "/signup");
        assertEquals("Sign Up", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/login");
        assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/home");
        assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/");
        assertEquals("Login", driver.getTitle());
    }

    @Test
    public void homeNotAccessible() {
        this.driver.get("http://localhost:" + this.port + "/file-upload");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void addNote() {
        signup();
        login();

        this.driver.get("http://localhost:" + this.port + "/");
        Home home = new Home(driver);
        home.openNotesTab();
        NoteTab noteTab = new NoteTab(driver);
        noteTab.addNote("New note", "New note");
        assertNotNull(noteTab.getNoteRow("New note", "New note"));
    }

    @Test
    public void editNote() {
        signup();
        login();

        this.driver.get("http://localhost:" + this.port + "/");
        Home home = new Home(driver);
        home.openNotesTab();
        NoteTab noteTab = new NoteTab(driver);
        noteTab.addNote("New note", "New note");
        noteTab.editNote("New note", "New note", "Other note", "Other note");
        assertNotNull(noteTab.getNoteRow("Other note", "Other note"));
    }

    @Test
    public void deleteNote() {
        signup();
        login();

        this.driver.get("http://localhost:" + this.port + "/");
        Home home = new Home(driver);
        home.openNotesTab();
        NoteTab noteTab = new NoteTab(driver);
        noteTab.addNote("New note", "New note");
        noteTab.deleteNote("New note", "New note");
        assertNull(noteTab.getNoteRow("New note","New note"));
    }

    @Test
    public void addCredential() {
        signup();
        login();
        String password = "password";
        this.driver.get("http://localhost:" + this.port + "/");
        Home home = new Home(driver);
        home.openCredentialsTab();
        CredentialTab credentialTab = new CredentialTab(driver);
        credentialTab.addCredential("www.udacity.com","CREDENTIAL",password);

        WebElement credentialRow = credentialTab.getCredentialRow("www.udacity.com", "CREDENTIAL");
        assertNull(credentialRow);
        String credentialPassword = credentialTab.getCredentialPassword();
        assertEquals(password,credentialPassword);
    }

    @Test
    public void editCredential() {
        signup();
        login();
        String password = "oldpass";
        String newPassword = "newpass";
        this.driver.get("http://localhost:" + this.port + "/");
        Home home = new Home(driver);
        home.openCredentialsTab();
        CredentialTab credentialTab = new CredentialTab(driver);
        credentialTab.addCredential("www.udacity.com","CREDENTIAL",password);
        credentialTab.editCredential("www.udacity.com","CREDENTIAL","www.google.com","My USERNAME",newPassword);
        String credentialPassword = credentialTab.getCredentialPassword();
        assertNotNull(credentialTab.getCredentialRow("www.google.com", "My USERNAME"));
        assertNotEquals(newPassword,credentialPassword);
    }

    @Test
    public void deleteCredential() {
        signup();
        login();

        this.driver.get("http://localhost:" + this.port + "/");
        Home home = new Home(driver);
        home.openCredentialsTab();
        CredentialTab credentialTab = new CredentialTab(driver);
        credentialTab.addCredential("www.udacity.com","CREDENTIAL","CREDENTIAL");
        credentialTab.deleteCredential("www.udacity.com","CREDENTIAL");

        assertNull(credentialTab.getCredentialRow("www.google.com", "My USERNAME"));

    }
}