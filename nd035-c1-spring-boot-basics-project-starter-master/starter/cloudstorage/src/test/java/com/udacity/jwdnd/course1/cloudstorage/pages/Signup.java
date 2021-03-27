package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Signup {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    @FindBy(id = "login-link")
    private WebElement toLoginBtn;

    private final JavascriptExecutor javascriptExecutor;

    WebDriver driver;

    public Signup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
    }


    public void signup(String firstname, String lastname, String username, String password) {
        Assertions.assertEquals("Sign Up", driver.getTitle());
        Assertions.assertDoesNotThrow(() -> {
            inputLastName.sendKeys(lastname);
            inputFirstName.sendKeys(firstname);
            inputUserName.sendKeys(username);
            inputPassword.sendKeys(password);
            Assertions.assertEquals("Sign Up", submitButton.getText());
            submitButton.click();
        });
    }
}