package com.selenium.test.testng.tests;

import com.selenium.test.pages.MobileLoginPage;
import com.selenium.test.pages.MobileMySchedulePage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Uses TestNG test framework
 * Tests uses work with Page Object Pattern (https://code.google.com/p/selenium/wiki/PageObjects)
 */
@Listeners({ScreenShotOnFailListener.class})
public class MobileLoginPageTest extends BaseTest{

    @BeforeTest
    public void beforeTest() { }

    /**
     * Checks defaults for elements on Login page
     */
    @Test(priority=1)
    public void testLoginPageElementsDefaults() throws Exception {
        dn.mLoginPage.openPage();

        //Default value for username field
        String usernameCurrentValue = dn.mLoginPage.getUserNameInputText();
        softAssert.assertEquals(usernameCurrentValue,"","Username field is not empty by default");

        //Default value for password field
//        String passwordCurrentValue = mobileLoginPage.getPasswordInputText();
        String passwordCurrentValue = dn.mLoginPage.getPasswordInputText();
        softAssert.assertEquals(passwordCurrentValue,"","Password field is not empty by default");

        //Default value for rememberMe checkbox
        softAssert.assertFalse(dn.mLoginPage.isRememberMeCheckboxChecked(),"Remember me checkbox is not unchecked by default");

        //Checks that login button is displayed
        softAssert.assertTrue(dn.mLoginPage.isLoginButtonDisplayed(),"Login button is not displayed");

        //Checks that forgotPasswordLink is displayed
        softAssert.assertTrue(dn.mLoginPage.isForgotPasswordLinkDisplayed(),"Forgot password link is not displayed");

        //Mark test as failed if at least one of above assertions is failed
        softAssert.assertAll();
    }


    /**
     * Checks simple login with correct credentials
     */
    @Test(priority=2)
    public void testCorrectLogin() throws Exception {
        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("org1", "tomcattom");
//        MobileMySchedulePage mobileMySchedulePage = new MobileMySchedulePage();
        softAssert.assertTrue(dn.mMySchedule.isPageOpened(),"My Schedule page is not opened");
//        softAssert.assertTrue(mobileMySchedulePage.isPageOpened(),"My Schedule page is not opened");
    }


    /**
     * Checks simple login with incorrect credentials
     */
    @Test(priority=3)
    public void testIncorrectLogin() throws Exception {
        dn.mLoginPage.openPage();
        dn.mLoginPage.setCredentials("1org","tomtomcat");
        dn.mLoginPage.clickOnLoginButton();
        softAssert.assertTrue(dn.mLoginPage.isErrorMessageDisplayed(),"Error message is not opened");
        softAssert.assertEquals(dn.mLoginPage.getErrorMessageText(), MobileLoginPage.errorMessageIncorrectLogin_ENG, "Displayed and expected error messages are not equal");
        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() { }

}