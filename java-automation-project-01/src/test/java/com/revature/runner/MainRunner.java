package com.revature.runner;

import com.revature.pages.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@CucumberOptions(features = "classpath:features", glue = "com.revature.steps")
public class MainRunner extends AbstractTestNGCucumberTests {
    public static WebDriver driver;

    public static MainPage mainPage;
    public static HomePage homePage;
    public static LoginPage loginPage;
    public static MatricesPage matricesPage;
    public static ReportDefectPage reportDefectPage;
    public static TestCasesPage testCasesPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        matricesPage = new MatricesPage(driver);
        reportDefectPage = new ReportDefectPage(driver);
        testCasesPage = new TestCasesPage(driver);
    }

    @AfterMethod
    public void cleanup() { driver.quit();}
}