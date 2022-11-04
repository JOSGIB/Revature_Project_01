package com.revature.pages;

import com.revature.runner.MainRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MainPage {
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    // GET URL
    public void get(String pageUrl) {
        MainRunner.driver.get(pageUrl);
    }

    // Locate page title
    @FindBy(xpath = "//h1[contains(text(),'')]")
    private WebElement pageTitle;

    public String check_page_title(String title) {
        MainRunner.driver.findElement(By.xpath("//h1[contains(text(),'" + title + "')]")).getText();
        return title;
    }

    // Check link titles
    @FindBy(xpath = "//nav/a[position()<5]")
    private WebElement linkTitles;

    public String check_link_titles(String title) {
        title = String.valueOf(MainRunner.driver.findElement(By.xpath("//nav/a[position()<5]")));
        return title;
    }

    // Check welcome title
    @FindBy(xpath = "//p")
    private WebElement welcomeTitle;

    public void check_welcome_tile(String title) {
        MainRunner.driver.findElement(By.xpath("//p"));
    }

    // Insert Text Field
    public String insertText(String xpathLocation, String text) {
        MainRunner.driver.findElement(By.xpath(xpathLocation)).sendKeys(text);
        return text;
    }

    // Select Dropdown
    public Select selectDropdown(By xpathLocation) {
        Select selectDropdown = new Select(MainRunner.driver.findElement(By.xpath(String.valueOf(xpathLocation))));
        return selectDropdown;
    }

    // Click link
    public void clickLink(String linkTitle) {
        MainRunner.driver.findElement(By.xpath("//a[contains(text(),'" + linkTitle+ "')]")).click();
    }

    // Get Alert
    public String getAlert() {
        MainRunner.driver.switchTo().alert().getText();
        return null;
    }
}
