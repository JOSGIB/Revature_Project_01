package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.print.attribute.standard.MediaName;
import java.time.Duration;

public class MatrixSteps {
    public static String tempTitle;
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));
    @Given("Manger Login")
    public void manger_login() {
        MainRunner.mainPage.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=13");
        MainRunner.loginPage.enter_username("g8tor");
        MainRunner.loginPage.enter_password("chomp!");
        MainRunner.loginPage.login_buttion();
    }
    @Given("A manager is on their home page")
    public void a_manager_is_on_their_home_page() {
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/managerhome"));
        Assert.assertEquals(MainRunner.mainPage.check_page_title("Manager Home"), "Manager Home");
    }
    @When("A manager can pull up a form to make a new matrix")
    public void a_manager_can_pull_up_a_form_to_make_a_new_matrix() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Create A new Requirements Matrix']")));
        MainRunner.driver.findElement(By.xpath("//button[text()='Create A new Requirements Matrix']")).click();
    }
    @When("A manager creates a title for a matrix")
    public void a_manager_creates_a_title_for_a_matrix() {
        tempTitle = MainRunner.mainPage.insertText("//body/div[@id='root']/input[1]", "Test Test Test");
    }
    @When("A manager adds requirements to a matrix")
    public void a_manager_adds_requirements_to_a_matrix() {
        MainRunner.mainPage.insertText("//input[@placeholder='User Story']", "Test Test Test");
        MainRunner.matricesPage.getRandPriority();
        MainRunner.mainPage.insertText("//input[@placeholder='Note']", "Test Test Test");
        MainRunner.homePage.getReqButton.click();
    }
    @When("A manager saves a matrix")
    public void a_manager_saves_a_matrix() {
        MainRunner.driver.findElement(By.xpath("//button[text()='Create Matrix']")).click();
    }
    @Then("The matrix should be visible for all testers and managers")
    public void the_matrix_should_be_visible_for_all_testers_and_managers() throws InterruptedException {
        wait.until(ExpectedConditions.alertIsPresent());
        MainRunner.driver.switchTo().alert().accept();
        MainRunner.mainPage.clickLink("Matrices");
        Thread.sleep(500);
        System.out.println(MainRunner.driver.getCurrentUrl());
        String actualTitle = MainRunner.driver.findElement(By.xpath("//li[contains(text(), '" + tempTitle + "')]")).getText();
        Assert.assertEquals(actualTitle.contains(tempTitle), true);
    }

    @Given("A manager is on the Matrices Page")
    public void a_manager_is_on_the_matrices_page() {
        MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Matrices')]")).click();
    }

    @Given("A manager or tester has selected a matrix")
    public void a_manager_or_tester_has_selected_a_matrix() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[1]/li[last()]/div[1]/span[1]/button[text()='Show']")));
        MainRunner.driver.findElement(By.xpath("//ul[1]/li[last()]/div[1]/span[1]/button[text()='Show']")).click();
    }
    @When("A manager or tester adds or removes defects")
    public void a_manager_or_tester_adds_or_removes_defects() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[1]/li[last()]//div//button[text()='Edit']")));
        MainRunner.driver.findElement(By.xpath("//ul[1]/li[last()]//div//button[text()='Edit']")).click();
        MainRunner.driver.findElement(By.xpath("//ul[1]//li[last()]/div//ul[2]/li[1]/input")).sendKeys("800");
        MainRunner.matricesPage.getAddDefectButton.click();
    }
    @When("A manager or tester adds or removes Test Cases")
    public void a_manager_or_tester_adds_or_removes_test_cases() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[1]/li[last()]//div//button[text()='Edit']")));
        MainRunner.driver.findElement(By.xpath("//ul[1]/li[last()]//div//button[text()='Edit']")).click();
        MainRunner.driver.findElement(By.xpath("//ul[1]//li[last()]/div//ul[1]/li[1]/input")).sendKeys("900");
        MainRunner.matricesPage.getAddTestButton.click();
    }
    @When("A manager or tester confirms their changes")
    public void a_manager_or_tester_confirms_their_changes() {
        MainRunner.driver.findElement(By.xpath("//ul[1]/li[last()]//div/button")).click();
    }
    @Then("Then the matrix should saved")
    public void then_the_matrix_should_saved() {
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlert = (MainRunner.driver.switchTo().alert().getText());
        Assert.assertEquals(actualAlert, "Matrix Saved");
    }
}
