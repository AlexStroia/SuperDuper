package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CredentialTab {

    @FindBy(id = "add-new-credential-btn")
    private WebElement addNewCredentialBtn;

    @FindBy(className = "credential-row")
    private List<WebElement> credentialElements;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlField;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "credential-save")
    private WebElement credentialSaveBtn;

    @FindBy(id = "credential-delete")
    private WebElement credentialDeleteBtn;

    @FindBy(id = "credential-edit")
    private WebElement credentialEditBtn;

    private WebDriver driver;

    public CredentialTab(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getCredentialRow(String url, String username) {

        for (WebElement credentialElemnt : credentialElements) {
            WebElement urlElement = credentialElemnt.findElement(By.className("credential-url"));
            WebElement usernameElement = credentialElemnt.findElement(By.className("credential-username"));

            if (urlElement.getText().equals(url) && usernameElement.getText().equals(username)) return credentialElemnt;
        }
        return null;
    }

    public String getCredentialPassword() {
        for (WebElement credentialElemnt : credentialElements) {
            WebElement password = credentialElemnt.findElement(By.className("credential-password"));
            return password.getText();
        }
        return null;
    }

    public void addCredential(String url, String username, String decryptedPassword) {
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(addNewCredentialBtn)).click();
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(credentialSaveBtn));
        credentialUrlField.sendKeys(url);
        credentialUsernameField.sendKeys(username);
        credentialPasswordField.sendKeys(decryptedPassword);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(credentialSaveBtn)).click();
    }

    public void editCredential(String oldUrl, String oldUsername, String newUrl, String newUsername, String newDecryptedPassword) {

        WebElement credentialRow = getCredentialRow(oldUrl, oldUsername);

        if (credentialRow == null) return;
        credentialEditBtn.click();
        credentialUrlField.clear();
        credentialUsernameField.clear();
        credentialPasswordField.clear();
        credentialUrlField.sendKeys(newUrl);
        credentialUsernameField.sendKeys(newUsername);
        credentialPasswordField.sendKeys(newDecryptedPassword);
        credentialSaveBtn.click();
    }

    public void deleteCredential(String url, String username) {
        WebElement credentialRow = getCredentialRow(url, username);
        if (credentialRow == null) return;
        credentialDeleteBtn.click();
        credentialRow.findElement(By.className("credential-delete")).click();
    }
}