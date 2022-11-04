package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    /// DEFINED ELEMENTS
    @FindBy(xpath = "//h1")
    public WebElement homeTitle;

    @FindBy(xpath = "//nav/p")
    public WebElement greetings;

    // LINKS
    @FindBy(xpath = "//a[1]")
    public WebElement matricesLink;

    @FindBy(xpath = "//a[2]")
    public WebElement testcasesLink;

    @FindBy(xpath = "//a[3]")
    public WebElement reportadefectLink;

    @FindBy(xpath = "//a[4]")
    public WebElement defectoverviewLink;

    /// MATRIX WEB ELEMENTS
    // DEFINED
    @FindBy(xpath = "//h2[(text()='New matrix')]")
    public WebElement getMatrixTitle;

    // INPUTS
    @FindBy(xpath = "//input[@name='title']")
    public WebElement getTitleTextbox;

    @FindBy(xpath = "//input[@placeholder='User Story']")
    public WebElement getUserstoryTextbox;

    @FindBy(xpath = "//input[@placeholder='Note']")
    public WebElement getNoteTextbox;

    // BUTTONS
    @FindBy(xpath = "//fieldset/button[1][contains(text(),'Add Requirement')]")
    public WebElement getReqButton;

    @FindBy(xpath = "//button[text()='Create A new Requirements Matrix']")
    public WebElement getCreateButton;

    /// DEFECT WEB ELEMENTS
    // INPUTS
    @FindBy(xpath = "//div/div[1]/h4")
    public WebElement getDefectDescription;

    // BUTTONS
    @FindBy(xpath = "//button[contains(text(),'Select')]")
    public WebElement getSelectButton;

    @FindBy(xpath = "//div/button[text()='Assign']")
    public WebElement getAssignButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
