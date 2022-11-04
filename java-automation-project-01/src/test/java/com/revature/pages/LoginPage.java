package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //  USERNAME INPUT
    @FindBy(name = "username")
    private WebElement usernameInput;

    public void enter_username(String username) {
        usernameInput.sendKeys(username);
    }

    //  PASSWORD INPUT
    @FindBy(name = "pass")
    private WebElement passwordInput;

    public void enter_password(String password) {
       passwordInput.sendKeys(password);
    }

    //  LOGIN BUTTON
    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;

    public void login_buttion() {
        loginButton.click();
    }
}
