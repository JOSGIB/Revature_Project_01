package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MatricesPage {
    public MatricesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // DECLARES
    @FindBy(xpath = "(//ul/li)[last()]")
    public WebElement getTitle;
    @FindBy(xpath = "//ul[1]/li[last()]/div//ul[1]/li[2]/input")
    public WebElement testCaseInput;
    @FindBy(xpath = "//ul[1]/li[last()]/div//ul[2]/li[2]/input")
    public WebElement defectIdInput;

    // BUTTONS
    @FindBy(xpath = "//ul/li[last()])//button[contains(text(),'Show')]")
    public WebElement getShowButton;
    @FindBy(xpath = "(//ul/li[last()])//button[contains(text(),'Edit')]")
    public WebElement getEditButton;
    @FindBy(xpath = "//ul[1]/li[last()]//div/ul[1]/li[1]/button[contains(text(),'Add')]")
    public WebElement getAddTestButton;
    @FindBy(xpath = "//ul[1]/li[last()]//div/ul[2]/li[1]/button[contains(text(),'Add')]")
    public WebElement getAddDefectButton;
    @FindBy(xpath = "//ul[1]/li[last()]//ul[1]/li[1]/button[contains(text(),'Remove')]")
    public WebElement getRemoveTestButton;
    @FindBy(xpath = "//ul[1]/li[last()]//ul[2]/li[1]/button[contains(text(),'Remove')]")
    public WebElement getRemoveDefectButton;
    @FindBy(xpath = "(//ul[1]/li[last()]/div//button[contains(text(),'Save Requirements')]")
    public WebElement getSaveButton;

    @FindBy(xpath = "//select/option[@value='Low']")
    public WebElement matrixLowPriority;
    @FindBy(xpath = "//select/option[@value='Medium']")
    public WebElement matrixMediumPriority;
    @FindBy(xpath = "//select/option[@value='High']")
    public WebElement matrixHighPriority;
    public void getRandPriority() {
        WebElement[] randPriority = {
                matrixLowPriority,
                matrixMediumPriority,
                matrixHighPriority
        };
        for(WebElement randP : randPriority) {
            randP.click();
        }
    }
}
