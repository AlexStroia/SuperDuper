package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NoteTab {

    @FindBy(id = "add-new-note-btn")
    private WebElement addNewNoteBtn;

    @FindBy(className = "note-row")
    private List<WebElement> noteElements;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "note-save")
    private WebElement noteSaveBtn;

    @FindBy(id = "note-delete")
    private WebElement noteDeleteBtn;

    @FindBy(id = "note-edit")
    private WebElement noteEditBtn;

    private WebDriver driver;

    public NoteTab(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Test
    public WebElement getNoteRow(String noteTitle, String noteDescription) {

        for (WebElement noteElement : noteElements) {
            WebElement titleElement = noteElement.findElement(By.className("note-title"));
            WebElement descriptionElement = noteElement.findElement(By.className("note-description"));

            if (titleElement.getText().equals(noteTitle) && descriptionElement.getText().equals(noteDescription))
                return noteElement;
        }
        return null;
    }

    public String getNoteRowTitle() {
        return noteTitleField.getAttribute("value");
    }

    @Test
    public void addNote(String noteTitle, String noteDescription) {
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(addNewNoteBtn)).click();
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(noteSaveBtn));
        noteTitleField.sendKeys(noteTitle);
        noteDescriptionField.sendKeys(noteDescription);
        noteSaveBtn.click();
    }

    public void editNote(String oldTitle, String oldDescription, String newTitle, String newDescription) {
        noteEditBtn.click();
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(addNewNoteBtn)).click();
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(noteSaveBtn));
        noteTitleField.sendKeys(newTitle);
        noteDescriptionField.sendKeys(newDescription);
        noteSaveBtn.click();
    }

    public void deleteNote(String title, String description) {
        WebElement noteRow = getNoteRow(title, description);
        if (noteRow == null) return;
        noteDeleteBtn.click();
    }
}
