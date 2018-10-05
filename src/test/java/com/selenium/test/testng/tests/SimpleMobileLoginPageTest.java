package com.selenium.test.testng.tests;

import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

/**
 * Uses TestNG test framework
 * Tests are using simple WebDriver functions (without PageObject pattern)
 */
@Listeners({ScreenShotOnFailListener.class})
public class SimpleMobileLoginPageTest extends BaseTest {

    @BeforeTest
    public void beforeTest() { }

    /**
     * Checks simple login with correct credentials
     */
    @Test
    public void testCorrectLogin() {

        String userName = "org1";
        String password = "tomcattom";
        String loginButtonXPath = "//div[@class=\"gwt-Label\" and text()=\"LOGIN\"]";
        String mySchedulePageTitleExpected = "MY SCHEDULE";
        String mySchedulePageTitleXPath = "//div[text()=\"MY SCHEDULE\"]";

        WebDriverFactory.getDriver().get("https://vm05.atwss.com:8443/m/login.htm");
        //Fill username
        WebElement usernameInput = WebDriverFactory.getDriver().findElement(By.name("j_username"));
        usernameInput.sendKeys(userName);
        //Fill password
        WebElement passwordInput = WebDriverFactory.getDriver().findElement(By.name("j_password"));
        passwordInput.sendKeys(password);
        //Click on Login button
        WebElement loginButtonElement = WebDriverFactory.getDriver().findElement(By.xpath(loginButtonXPath));
        loginButtonElement.click();
        //Check that login is successful and My Schedule page is opened
        WebElement mySchedulePageTitle = WebDriverFactory.getDriver().findElement(By.xpath(mySchedulePageTitleXPath));
        String mySchedulePageTitleActual = mySchedulePageTitle.getText();
        assertTrue("Page title (" + mySchedulePageTitle + ") not equals expected title (" + mySchedulePageTitleExpected + ")", mySchedulePageTitleActual.equals(mySchedulePageTitleExpected));
    }

    @AfterTest
    public void afterTest() { }

}