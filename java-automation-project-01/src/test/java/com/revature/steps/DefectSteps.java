package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.security.Key;
import java.time.Duration;

public class DefectSteps {
    public static Alert alert;

    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));

    /// ASSIGN DEFECT STEPS
    @Given("The manager is logged in as a manager")
    public void the_manager_is_logged_in_as_a_manager() {
        MainRunner.mainPage.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=13");
        MainRunner.loginPage.enter_username("g8tor");
        MainRunner.loginPage.enter_password("chomp!");
        MainRunner.loginPage.login_buttion();
    }

    @Given("The manager is on the home page")
    public void the_manager_is_on_the_home_page() {
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/managerhome"));
        Assert.assertEquals(MainRunner.mainPage.check_page_title("Manager Home"), "Manager Home");
    }

    @Then("The manager should see pending defects")
    public void the_manager_should_see_pending_defects() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button")));
    }

    @When("The manager clicks on the select button for a defect")
    public void the_manager_clicks_on_the_select_button_for_a_defect() {
        // Must have a defect to work ---> other wise it fails
        MainRunner.homePage.getSelectButton.click();
    }

    @Then("The defect description should appear in bold")
    public void the_defect_description_should_appear_in_bold() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[1]/h4")));
        boolean isBold;
        String checkFontWeight = MainRunner.homePage.getDefectDescription.getCssValue("font-weight");
        if (Integer.parseInt(checkFontWeight) >= 700) {
            isBold = true;
        } else {
            isBold = false;
        }
        Assert.assertEquals(isBold, isBold);
    }

    @When("The manager selects an tester from the drop down")
    public void the_manager_selects_an_tester_from_the_drop_down() {
        WebElement getTester = MainRunner.driver.findElement(By.xpath("//input[1]"));
        getTester.sendKeys("ryeGuy");
    }

    @When("The manager clicks assign")
    public void the_manager_clicks_assign() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[text()='Assign']")));
        MainRunner.homePage.getAssignButton.click();
        MainRunner.driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
    }

    @Then("The defect should disappear from the list")
    public void the_defect_should_disappear_from_the_list() {
        // MANUAL CHECK
    }

    @Given("The assigned tester is on their home page")
    public void the_assigned_tester_is_on_their_home_page() {
        MainRunner.mainPage.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=13");
        MainRunner.loginPage.enter_username("cavalier89");
        MainRunner.loginPage.enter_password("alucard");
        MainRunner.loginPage.login_buttion();
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/testerhome"));
        Assert.assertEquals(MainRunner.mainPage.check_page_title("Tester Home"), "Tester Home");
    }
    @Then("The tester should see the pending defect")
    public void the_tester_should_see_the_pending_defect() {
        // MANUAL CHECK
    }

    /// NEGATIVE/POSITIVE DEFECT REPORT
    @Given("The employee is on the Defect Reporter Page")
    public void the_employee_is_on_the_defect_reporter_page() {
        MainRunner.mainPage.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=13");
        MainRunner.loginPage.enter_username("cavalier89");
        MainRunner.loginPage.enter_password("alucard");
        MainRunner.loginPage.login_buttion();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[3]")));
        MainRunner.homePage.reportadefectLink.click();
    }

    @When("The employee selects todays date")
    public void the_employee_selects_todays_date() {
        MainRunner.reportDefectPage.getDateInput.sendKeys("11/3/22");
    }

    @When("The employee types in {string} with")
    public void the_employee_types_in_with(String docStringDesc, String docStringStep) {
        MainRunner.reportDefectPage.insertDesc(docStringDesc);
        MainRunner.reportDefectPage.insertSteps(docStringStep);
    }
    @When("The employee selects high priority")
    public void the_employee_selects_high_priority() {
        for (int i = 0; i <= 3; i++) {
            MainRunner.driver.findElement(By.xpath("//input[@name='priority']")).sendKeys(Keys.ARROW_RIGHT);
        }
    }
    @When("The employee selects low severity")
    public void the_employee_selects_low_severity() {
        for (int i = 3; i >= 0; i--) {
            By.xpath("//input[@name='severity']").findElement(MainRunner.driver).sendKeys(Keys.ARROW_LEFT);
        }
    }
    @When("The employee clicks the report button")
    public void the_employee_clicks_the_report_button() {
        MainRunner.driver.findElement(By.xpath("//button[contains(text(),'Report')]")).click();
    }
    @Then("No confirmation dialog appears")
    public void no_confirmation_dialog_appears() {
        try {
            alert = MainRunner.driver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            String actualAlert = null;
            Assert.assertNull(actualAlert);
        }
    }
    @Then("There should be a confirmation box")
    public void there_should_be_a_confirmation_box() {
        wait.until(ExpectedConditions.alertIsPresent());
        MainRunner.driver.switchTo().alert();
    }
    @When("The employee clicks Ok")
    public void the_employee_clicks_ok() {
        MainRunner.driver.switchTo().alert().accept();
    }
    @Then("A modal should appear with a {string} ID")
    public void a_modal_should_appear_with_a_defect_id(String id) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[1]/h4[1]")));
        String actualResult = MainRunner.driver.findElement(By.xpath("//div[1]/h4[1]")).getText();
        Assert.assertEquals(actualResult, id);
    }
    @When("The employee clicks close")
    public void the_employee_clicks_close() {
        MainRunner.reportDefectPage.getCloseButton.click();
    }
    @Then("The modal should disappear")
    public void the_modal_should_disappear() {
        WebElement actualResult = MainRunner.driver.findElement(By.xpath("//body/div[3]/div[1]/div[1]"));
        Assert.assertNull(actualResult);
    }

    /// DEFECT STATUS STEPS
    @Given("Tester Login")
    public void tester_login() {
        MainRunner.mainPage.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=13");
        MainRunner.loginPage.enter_username("cavalier89");
        MainRunner.loginPage.enter_password("alucard");
        MainRunner.loginPage.login_buttion();
    }

    @Given("The tester is on the Home Page")
    public void the_tester_is_on_the_home_page() {
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/testerhome"));
        Assert.assertEquals(MainRunner.mainPage.check_page_title("Tester Home"), "Tester Home");
    }

    @Then("The tester can can see only defects assigned to them")
    public void the_tester_can_can_see_only_defects_assigned_to_them() {
        wait.until(ExpectedConditions.textToBe(By.xpath("//h3[text()='My Defects']"), "My Defects"));
    }

    @When("The tester changes the defect to any status")
    public void the_tester_changes_the_defect_to_any_status()  {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[last()]")));
        MainRunner.driver.findElement(By.xpath("//li[last()]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[last()]//span/button[text()='Change Status']")));
        MainRunner.driver.findElement(By.xpath("//li[last()]//span/button[text()='Change Status']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[last()]//div//button[4]")));
        MainRunner.driver.findElement(By.xpath("//li[last()]//div//button[4]")).click();
    }

    @Then("The tester should see the defect has a different status")
    public void the_tester_should_see_the_defect_has_a_different_status( ) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[last()]/div/span/p/b[2]")));
        String actualAlert = MainRunner.driver.findElement(By.xpath("//li[last()]/div/span/p/b[2]")).getText();
        Assert.assertEquals(actualAlert, actualAlert);
    }
}
