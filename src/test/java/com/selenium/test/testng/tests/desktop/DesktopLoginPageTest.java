package com.selenium.test.testng.tests.desktop;

import com.selenium.test.pages.desktop.DesktopDashboardPage;
import com.selenium.test.pages.desktop.DesktopLoginPage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import com.selenium.test.testng.tests.desktop.*;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;

/**
 * Uses TestNG test framework
 * Tests uses work with Page Object Pattern (https://code.google.com/p/selenium/wiki/PageObjects)
 */
@Listeners({ScreenShotOnFailListener.class})
public class DesktopLoginPageTest extends BaseTest {


    @BeforeTest
    public void beforeTest() throws Exception{ }


    /**
     * Checks defaults for elements on Login page
     */
    @Test(priority = 1)
    public void testLoginPageElementsDefaults() throws Exception{
        dn.dLoginPage.openPage();


        //Default value for username field
        String usernameCurrentValue = dn.dLoginPage.getUserNameInputText();
        softAssert.assertEquals(usernameCurrentValue, "", "Username field is not empty by default");

        //Default value for password field
        String passwordCurrentValue = dn.dLoginPage.getPasswordInputText();
        softAssert.assertEquals(passwordCurrentValue, "", "Password field is not empty by default");

        //Default value for rememberMe checkbox
        softAssert.assertFalse(dn.dLoginPage.isRememberMeCheckboxChecked(), "Remember me checkbox is not unchecked by default");

        //Checks that login button is displayed
        softAssert.assertTrue(dn.dLoginPage.isLoginButtonDisplayed(), "Login button is not displayed");

        //Checks that forgotPasswordLink is displayed
        softAssert.assertTrue(dn.dLoginPage.isForgotPasswordLinkDisplayed(), "Forgot password link is not displayed");

        //Mark test as failed if at least one of above assertions is failed
        softAssert.assertAll();
    }


    /**
     * Checks simple login with correct credentials
     */
    @Test(priority = 2)
    public void testCorrectLogin() throws Exception{
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ed1", "tomcattom");
        dn.dDashboard.openPage();
//        softAssert.assertTrue(mobileMySchedulePage.isPageOpened(),"My Schedule page is not opened");
    }


    /**
     * Checks simple login with incorrect credentials
     */
    @Test(priority = 3)
    public void testIncorrectLogin() throws Exception{
        dn.dLoginPage.openPage();
        dn.dLoginPage.setCredentials("1org", "tomtomcat");
        dn.dLoginPage.clickOnLoginButton();
        softAssert.assertTrue(dn.dLoginPage.isErrorMessageDisplayed(), "Error message is not opened");
        softAssert.assertEquals(dn.dLoginPage.getErrorMessageText(), DesktopLoginPage.errorMessageIncorrectLogin_ENG, "Displayed and expected error messages are not equal");
        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() { }

}