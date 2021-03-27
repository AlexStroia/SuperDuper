package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.Home;
import com.udacity.jwdnd.course1.cloudstorage.pages.Login;
import com.udacity.jwdnd.course1.cloudstorage.pages.NoteTab;
import com.udacity.jwdnd.course1.cloudstorage.pages.Signup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}