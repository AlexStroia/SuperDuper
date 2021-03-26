package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.util.Base64;

public class Home {
    @FindBy(id = "btnLogout")
    private WebElement logoutButton;

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "btnAddNewNote")
    private WebElement btnAddNewNote;

    @FindBy(id = "btnAddNewCredential")
    private WebElement btnAddNewCredential;

    @FindBy(id = "note-title")
    private WebElement noteTitleText;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionText;

    @FindBy(id = "btnSaveChanges")
    private WebElement btnSaveChanges;

    @FindBy(id = "tableNoteTitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "tableNoteDescription")
    private WebElement tableNoteDescription;

    @FindBy(id = "btnEditNote")
    private WebElement btnEditNote;

    @FindBy(id = "btnEditCredential")
    private WebElement btnEditCredential;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "ancDeleteNote")
    private WebElement ancDeleteNote;

    @FindBy(id = "aDeleteCredential")
    private WebElement aDeleteCredential;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlText;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameText;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordText;

    @FindBy(id = "tblCredentialUsername")
    private WebElement tblCredentialUsername;

    @FindBy(id = "tblCredentialPassword")
    private WebElement tblCredentialPassword;

    @FindBy(id = "btnCredentialSaveChanges")
    private WebElement btnCredentialSaveChanges;

    @FindBy(id = "tblCredentialUrl")
    private WebElement tblCredentialUrl;

    private final JavascriptExecutor javascriptExecutor;

    private final WebDriverWait wait;

    public Home(WebDriver driver) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 1000);
    }

    public void logout() {
        javascriptExecutor.executeScript("arguments[0].click();", logoutButton);
    }

    public void editNote() {
        javascriptExecutor.executeScript("arguments[0].click();", btnEditNote);
    }

    public void editCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", btnEditCredential);
    }

    public void deleteNote() {
        javascriptExecutor.executeScript("arguments[0].click();", ancDeleteNote);
    }

    public void deleteCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", aDeleteCredential);
    }

    public void uploadFile() {
        javascriptExecutor.executeScript("arguments[0].click();", fileUpload);
    }

    public void addNewNote() {
        javascriptExecutor.executeScript("arguments[0].click();", btnAddNewNote);
    }

    public void addNewCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", btnAddNewCredential);
    }

    public void setNoteTitle(String noteTitle) {
        javascriptExecutor.executeScript("arguments[0].value='" + noteTitle + "';", noteTitleText);
    }

    public void setCredentialUrl(String url) {
        javascriptExecutor.executeScript("arguments[0].value='" + url + "';", credentialUrlText);
    }

    public void setCredentialUsername(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", credentialUsernameText);
    }

    public void setCredentialPassword(String password) {
        javascriptExecutor.executeScript("arguments[0].value='" + password + "';", credentialPasswordText);
    }

    public void modifyNoteTitle(String newNoteTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleText)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleText)).sendKeys(newNoteTitle);
    }

    public void modifyNoteDescription(String newNoteDescription) {
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).sendKeys(newNoteDescription);
    }

    public void navToNotesTab() {
        javascriptExecutor.executeScript("arguments[0].click();", navNotesTab);
    }

    public void navToCredentialsTab() {
        javascriptExecutor.executeScript("arguments[0].click();", navCredentialsTab);
    }

    public void setNoteDescription(String noteDescription) {
        javascriptExecutor.executeScript("arguments[0].value='" + noteDescription + "';", noteDescriptionText);
    }

    public void saveNoteChanges() {
        javascriptExecutor.executeScript("arguments[0].click();", btnSaveChanges);
    }

    public void saveCredentialChanges() {
        javascriptExecutor.executeScript("arguments[0].click();", btnCredentialSaveChanges);
    }

    public boolean noNotes(WebDriver driver) {
        return !isElementPresent(By.id("tableNoteTitle"), driver) && !isElementPresent(By.id("tableNoteDescription"), driver);
    }

    public boolean noCredentials(WebDriver driver) {
        return !isElementPresent(By.id("tblCredentialUrl"), driver) &&
                !isElementPresent(By.id("tblCredentialUsername"), driver) &&
                !isElementPresent(By.id("tblCredentialPassword"), driver);
    }

    public boolean isElementPresent(By locatorKey, WebDriver driver) {
        try {
            driver.findElement(locatorKey);

            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public Note getFirstNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
        String description = tableNoteDescription.getText();

        return new Note(0, title, description, 1);
    }

    public Credential getFirstCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(tblCredentialUrl)).getText();
        String username = tblCredentialUsername.getText();
        String password = tblCredentialPassword.getText();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        return new Credential(0, url, username, password, encodedKey, 1);
    }
}