package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));
    // SET SCENE
    @Given("The employee is on the login page")
    public void the_employee_is_on_the_login_page() {
        MainRunner.mainPage.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=13");
    }

    // USER ENTERS USERNAME
    @When("The employee types {string} into username input")
    public void the_employee_types_into_username_input(String username) {
        MainRunner.loginPage.enter_username(username);
    }

    // USER ENTERS PASSWORD
    @When("The employee types {string} into password input")
    public void the_employee_types_into_password_input(String password) {
        MainRunner.loginPage.enter_password(password);
    }

    // CLICK BUTTON
    @When("The employee clicks on the login button")
    public void the_employee_clicks_on_the_login_button() {
        MainRunner.loginPage.login_buttion();
    }

    // TEST(S)
    @Then("The employee should see an alert saying they have the wrong password")
    public void the_employee_should_see_an_alert_saying_they_have_the_wrong_password() {
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlert = MainRunner.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlert, "Wrong password for User");
    }

    @Then("The employee should see an alert saying no user with that username found")
    public void the_employee_should_see_an_alert_saying_no_user_with_that_username_found() {
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlert = (MainRunner.driver.switchTo().alert().getText());
        Assert.assertEquals(actualAlert, "Username not found");
    }

    @Then("The employee should be on the {string} page")
    public void the_employee_should_be_on_the_page(@NotNull String role) {
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/" + role.toLowerCase() + "home"));
        String actualAlert = MainRunner.driver.findElement(By.xpath("//h1[contains(text(), '" + role + " Home')]")).getText();
        Assert.assertEquals(actualAlert, (role + " Home"));
    }

    @Then("The employee should see their name {string} {string} on the home page")
    public void the_employee_should_see_their_name_on_the_home_page(String fName, String lName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p")));
        String actualAlert = MainRunner.driver.findElement(By.xpath("//p")).getText();
        Assert.assertEquals(actualAlert, ("Welcome " + fName + " " + lName));
    }
}
