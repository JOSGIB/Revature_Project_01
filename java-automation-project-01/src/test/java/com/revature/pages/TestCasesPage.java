package com.revature.pages;

import com.revature.runner.MainRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasesPage {
    public TestCasesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//textarea[@name='desc']")
    private WebElement descTextarea;

    public void insertDesc(String description) {
        descTextarea.sendKeys(description);
    }


    @FindBy(xpath = "//textarea[@name='steps']")
    private WebElement stepTextarea;

    public void insertSteps(String steps) {
        stepTextarea.sendKeys(steps);
    }



    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private WebElement saveButton;

    public void clickSaveButton() {
        saveButton.click();
    }
}
