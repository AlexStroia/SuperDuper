package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
    @FindBy(id = "logout-button")
    private WebElement logoutBtn;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTabButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTabButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTabButton;

    private NoteTab notesTab;

    public Home(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openFilesTab(){
        filesTabButton.click();
    }

    public void openNotesTab(){
        notesTabButton.click();
    }

    public void openCredentialsTab(){
        credentialsTabButton.click();
    }

    public void logout() {
        logoutBtn.click();
    }
}
