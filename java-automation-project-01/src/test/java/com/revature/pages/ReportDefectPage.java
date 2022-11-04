package com.revature.pages;

import com.revature.runner.MainRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportDefectPage {
    /// DEFINED ELEMENTS
    @FindBy(xpath = "//h1")
    public WebElement getPageTitle;

    /// INPUTS
    @FindBy(xpath = "//form/input[@name='dateReported']")
    public WebElement getDateInput;

    @FindBy(xpath = "//form/textarea[@name='desc']")
    public WebElement getDescTextbox;

    @FindBy(xpath = "//form/textarea[@name='stepsToReproduce']")
    public WebElement getStepTextarea;

    /// BUTTON
    @FindBy(xpath = "//form/button[text()='Report']")
    public WebElement getReportButton;

    @FindBy(xpath = "//div[@role='dialog']/button")
    public WebElement getCloseButton;

    @FindBy(xpath = "//textarea[1]")
        private WebElement descTextarea;

    public void insertDesc(String description) {
        descTextarea.sendKeys(description);
    }

    @FindBy(xpath = "//textarea[2]")
    private WebElement stepTextarea;

    public void insertSteps(String steps) {
        stepTextarea.sendKeys(steps);
    }

    public ReportDefectPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
