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
import java.util.Arrays;

public class NavigationSteps {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));

    // Link List
    public String[] linkList = {
            MainRunner.driver.findElement(By.xpath("//a[@href='/matrices']")).getText(),
            MainRunner.driver.findElement(By.xpath("//a[@href='/testcases']")).getText(),
            MainRunner.driver.findElement(By.xpath("//a[@href='/defectreporter']")).getText(),
            MainRunner.driver.findElement(By.xpath("//a[@href='/defectoverview']")).getText()
    };

    @Then("The manager should see links for {string}, {string}, {string} and {string}")
    public void the_manager_should_see_links_for_matrices_test_cases_defect_reporting_and_defect_overview(String matrices, String testCase, String reportADefect, String defectOverview) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//nav/a[position()<5]")));
        String expectedAlert = Arrays.toString(new String[]{matrices, testCase, reportADefect, defectOverview});
        Assert.assertEquals(Arrays.toString(linkList), expectedAlert);
    }

    @When("The manager clicks on Matrices")
    public void the_manager_clicks_on_matrices() {
        MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Matrices')]")).click();
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/matrices"));
    }
    
    @Then("The title of the page should be Matrices")
    public void the_title_of_the_page_should_be_matrix_page() {
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/matrices"));
        String actualAlert = MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Matrices')]")).getText();
        Assert.assertEquals(actualAlert, "Matrices");
    }
    @When("The manager clicks the browser back button")
    public void the_manager_clicks_the_browser_back_button() {
        MainRunner.driver.navigate().back();
    }

    @Then("The manager should be on the home page and the title of page is Home")
    public void the_manager_should_be_on_the_home_page_and_the_title_of_page_is_home() {
        String actualAlert = MainRunner.driver.findElement(By.xpath("//h1[contains(text(), 'Manager Home')]")).getText();
        Assert.assertEquals(actualAlert, "Manager Home");
    }
    @When("The manager clicks on Test Cases")
    public void the_manager_clicks_on_test_cases() {
        MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Test Cases')]")).click();
    }

    @When("The manager clicks on {string}")
    public void the_manager_clicks_on (@NotNull String link) {
        if (link.equals(linkList[0])) {
            MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Matrices')]")).click();
        }
        if (link.equals(linkList[1])) {
            MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Test Cases')]")).click();
        }
        if (link.equals(linkList[2])) {
            MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Report a Defect')]")).click();
        }
        if (link.equals(linkList[3])) {
            MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Defect Overview')]")).click();
        }
    }

    // CHECK PAGE TITLE
    @Then("The title of page should be {string}")
    public void the_title_page_should_be (@NotNull String title) {
        String actualAlert = MainRunner.driver.findElement(By.xpath("//h1[contains(text(),'" + title + "')]")).getText();
        Assert.assertEquals(actualAlert, title);
    }
}
