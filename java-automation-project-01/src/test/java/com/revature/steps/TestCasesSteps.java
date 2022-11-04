package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class TestCasesSteps {
    private static String ID = "";
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));
    public static Alert alertConfirmation;

    @Given("The tester is logged in")
    public void the_tester_is_logged_in() {
        MainRunner.mainPage.get("https://bugcatcher-jasdhir.coe.revaturelabs.com/?dev=13");
        MainRunner.loginPage.enter_username("ryeGuy");
        MainRunner.loginPage.enter_password("coolbeans");
        MainRunner.loginPage.login_buttion();
        wait.until(ExpectedConditions.urlMatches("https://bugcatcher-jasdhir.coe.revaturelabs.com/testerhome"));
    }
    @Given("The tester is on the test case dashboard")
    public void the_tester_is_on_the_test_case_dashboard() {
        MainRunner.driver.findElement(By.linkText("Test Cases")).click();
        wait.until(ExpectedConditions.urlContains("/testcases"));
    }
    @When("The tester types {string} into input with")
    public void the_tester_types_into_input_with(String string, String docString) {
        if (string.equals("Description")) {
            MainRunner.testCasesPage.insertDesc(docString);
        } else {
            MainRunner.testCasesPage.insertSteps(docString);
        }
    }
    @When("The tester presses the submit button")
    public void the_tester_presses_the_submit_button() {
        MainRunner.driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    @Then("The test case should appear at the bottom of the table")
    public void the_test_case_should_appear_at_the_bottom_of_the_table() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table")));
        String actualAlert = MainRunner.driver.findElement(By.xpath("//table[1]//tbody//tr[last()]")).getText();
        String expectedAlert = MainRunner.driver.findElement(By.xpath("//table[1]//tbody//tr[last()]")).getText();
        Assert.assertEquals(actualAlert, expectedAlert);
    }
    @Then("The test case result should say {string}")
    public void the_test_case_result_should_say(String result) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table")));
        String actualAlert = MainRunner.driver.findElement(By.xpath("//tbody/tr[5]/td[3]")).getText();
        Assert.assertEquals(actualAlert, result);
    }
    @When("The tester presses on details")
    public void the_tester_presses_on_details() {
        MainRunner.driver.findElement(By.xpath("//tr[last()]/td[4]/button")).click();
    }
    @Then("A test case modal should appear showing the {string} ID")
    public void a_test_case_modal_should_appear_showing_the(String caseID) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//body/div[3]/div[1]/div[1]")));
        caseID = MainRunner.driver.findElement(By.xpath("//h3[1]")).getText();
        String actualAlert = MainRunner.driver.findElement(By.xpath("//h3[1]")).getText();
        Assert.assertEquals(actualAlert, caseID);
    }
    @Then("The performed by field should say {string}")
    public void the_performed_by_field_should_say_no_one(String message) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'Performed By')]")));
        String actualAlert = MainRunner.driver.findElement(By.xpath("//p[contains(text(),'No One')]")).getText();
        Assert.assertEquals(actualAlert, message);
    }
    @When("The tester presses the close button")
    public void the_tester_presses_the_close_button() {
        MainRunner.driver.findElement(By.xpath("//div/button[1]")).click();
    }
    @Then("The Modal Should be closed")
    public void the_modal_should_be_closed() {
        // MANUAL
    }
    @When("The tester clicks on details")
    public void the_tester_clicks_on_details() {
        MainRunner.driver.findElement(By.xpath("//tr[last()]/td[4]/button")).click();
    }
    @When("The tester clicks on edit within the modal")
    public void the_tester_clicks_on_edit_within_the_modal() {
        MainRunner.driver.findElement(By.xpath("//div/button[2]")).click();
    }
    @Then("The tester should be on the {string} for that case")
    public void the_tester_should_be_on_the_case_editor_for_that_case(String caseTile) {
        String actualAlert = MainRunner.driver.getTitle();
        Assert.assertEquals(actualAlert, caseTile);
    }
    @Then("The fields should be uneditable")
    public void the_fields_should_be_uneditable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[2]/textarea[1]")));
        Assert.assertFalse(MainRunner.driver.findElement(By.xpath("//fieldset[1]/textarea[1]")).isEnabled());
    }
    @When("The tester clicks on the edit button")
    public void the_tester_clicks_on_the_edit_button() {
        MainRunner.driver.findElement(By.xpath("//button[contains(text(),'Edit')]")).click();
    }
    @Then("The test case fields should be editable")
    public void the_test_case_fields_should_be_editable() {
        String actualAlert = MainRunner.driver.findElement(By.xpath("//div/fieldset/textarea")).getAttribute("disabled");
        Assert.assertNull(actualAlert);
    }
    @When("The tester types in a new description into the description text area")
    public void the_tester_types_in_a_new_description_into_the_description_text_area() {
        MainRunner.driver.findElement(By.xpath("//fieldset[1]//textarea[1]")).getText();
        MainRunner.mainPage.insertText("//fieldset[1]//textarea[1]", "Test Description Insert!");
    }
    @When("The tester types in a new steps into the steps text area")
    public void the_tester_types_in_a_new_steps_into_the_steps_text_area() {
        MainRunner.driver.findElement(By.xpath("//fieldset[1]//textarea[2]")).getText();
        MainRunner.mainPage.insertText("//fieldset[1]//textarea[2]", "1. Insert Step" +
                "2. This is a test!!!");
    }
    @When("The tester clicks on the automated check mark")
    public void the_tester_clicks_on_the_automated_check_mark() {
        MainRunner.driver.findElement(By.xpath("//fieldset[1]//input[1]")).click();
    }
    @When("The tester selects ryeGuy for performed from drop down")
    public void the_tester_selects_rye_guy_for_performed_from_drop_down() {
        WebElement selectTester = MainRunner.driver.findElement(By.xpath("//fieldset[1]/select[1]"));
        Select testerDropdown = new Select(selectTester);
        testerDropdown.selectByValue("ryeGuy"); // ryeGuy
    }
    @When("The tester selects FAIL for test result from drop down")
    public void the_tester_selects_fail_for_test_result_from_drop_down() {
        WebElement selectResult = MainRunner.driver.findElement(By.xpath("//fieldset[2]/select[1]"));
        Select testerDropdown = new Select(selectResult);
        testerDropdown.selectByValue("FAIL"); // ryeGuy
    }
    @When("The tester types in a new summary into the summary text area")
    public void the_tester_types_in_a_new_summary_into_the_summary_text_area() {
        MainRunner.driver.findElement(By.xpath("//fieldset[2]//textarea[1]")).sendKeys("This is the summary for the test");
    }
    @When("The tester clicks save on test case page")
    public void the_tester_clicks_save_on_test_case_page() {
        MainRunner.testCasesPage.clickSaveButton();
    }
    @Then("A confirmation prompt should appear")
    public void a_confirmation_prompt_should_appear() {
        wait.until(ExpectedConditions.alertIsPresent());
        alertConfirmation = MainRunner.driver.switchTo().alert();
    }
    @When("The tester clicks on Ok")
    public void the_tester_clicks_on_ok() {
        alertConfirmation.accept();
    }
    @Then("An alert says the {string}")
    public void an_alert_says_the_test_case_has_been_saved(String message) {
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlert = (MainRunner.driver.switchTo().alert().getText());
        Assert.assertEquals(actualAlert, message);
    }
    @Given("the tester is on the test case editor for a specific test case")
    public void the_tester_is_on_the_test_case_editor_for_a_specific_test_case() {
        WebElement caseID = MainRunner.driver.findElement(By.xpath("//h1"));
        wait.until(ExpectedConditions.visibilityOf(caseID)); // ID
        Assert.assertTrue(caseID.getText().contains(ID));
    }
    @When("The tester clicks on the reset button")
    public void the_tester_clicks_on_the_reset_button() {
        MainRunner.driver.findElement(By.xpath("//button[contains(text(),'Reset')]")).click();
    }
    @Then("The fields should be populated to their old values")
    public void the_fields_should_be_populated_to_their_old_values() {
        // REVIEW LATER
    }
}
