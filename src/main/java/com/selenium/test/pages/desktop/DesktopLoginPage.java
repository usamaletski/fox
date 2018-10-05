package com.selenium.test.pages.desktop;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.pages.desktop.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * Mobile Login Page object
 */
public class DesktopLoginPage extends BasePage {

    private static final String PAGE_URL = "login.htm";

    private static DesktopLoginPage dLoginPage;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

	@FindBy(className = "aCheckBox")
	private WebElement rememberMeCheckbox;

    @FindBy(id = "login-button")
    private WebElement loginButtonElement;

    @FindBy(id = "forgot-link")
	private WebElement forgotPasswordLink;

	@FindBy(id ="default-error")
	private WebElement errorMessage;

    //Locator for start page (universal)
    @FindBy(className = "controlHeader")
    private WebElement locatorStartPage;

    //Properties and text resources TODO Move it to config.properties?
    public static String errorMessageIncorrectLogin_ENG = "We could not find any user profile with this email address or the\n" +
            "password you entered is incorrect. Please try again.";

    public static DesktopLoginPage getInstance() throws Exception {
        if (dLoginPage  == null) dLoginPage  = new DesktopLoginPage();
        return dLoginPage;
    }

    public DesktopLoginPage() throws Exception {
        super();
    }

    public DesktopLoginPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    @Override
    public void openPage() throws Exception {
        openPage(PAGE_URL);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return loginButtonElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * inserts username and password into appropriate fields
     * @param userName for input
     * @param password for input
     */
    public void setCredentials(String userName, String password) {
        usernameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
    }

    /**
     * getting username text from input
     * @return text from username input
     */
    public String getUserNameInputText(){
        return usernameInput.getAttribute("value");
    }

    /**
     * getting password text from input
     * @return text from password input
     */
    public String getPasswordInputText(){
        return passwordInput.getAttribute("value");
    }

    /**
     * clears username field
     */
    private void clearUserNameInput(){
        usernameInput.clear();
    }

    /**
     * clears password field
     */
    private void clearPasswordInput(){
        passwordInput.clear();
    }

    /**
     * checks rememberMe checkbox if not checked
     */
    public void checkRememberMeCheckbox(){
        if (!rememberMeCheckbox.getCssValue("class").contains("checked"))
            rememberMeCheckbox.click();
    }

    /**
     * @return state for rememberMe checkbox (checked/unchecked) based on class
     */
    public boolean isRememberMeCheckboxChecked(){
        try {
            return rememberMeCheckbox.getCssValue("class").contains("checked");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks on "LOGIN" button
     * @return results page instance
     */
    public void doLogin() {
        loginButtonElement.click();
        WebDriverFactory.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue("Start page is not represented",locatorStartPage.isDisplayed());
    }

    /**
     * Performs the simple login
     * @return results page instance
     * @param userName for input
     * @param password for input
     */
    public void doLoginAs(String userName, String password){
        //Clear username and password fields
        clearPasswordInput();
        clearUserNameInput();
        //Fill username and password fields
        setCredentials(userName, password);
        //Perform the login TODO Add validations for incorrect login
        doLogin();
    }

    /**
     * Click on Login button
     */
    public void clickOnLoginButton(){
        if (isLoginButtonDisplayed()){
            loginButtonElement.click();
        } else throw new AssertionError("Login button is not displayed");
    }

    /**
     * @return state for loginButton (displayed or not) using WebDriver isDisplayed method
     */
    public boolean isLoginButtonDisplayed(){
        try {
            return loginButtonElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * perform the click on forgot password link
     * @return results Forgotten Password page instance TODO
     */
    public void clickForgotPasswordLink(){
        forgotPasswordLink.click();
    }

    /**
     * @return state for forgot password link (displayed or not) using WebDriver isDisplayed method
     */
    public boolean isForgotPasswordLinkDisplayed(){
        try {
            return forgotPasswordLink.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @return displayed state of error message
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @return error message's text
     */
    public String getErrorMessageText(){
        String errorMessageText = "Error message is not displayed";

        if (errorMessage.isDisplayed()) {
            errorMessageText = errorMessage.getText();
            return errorMessageText;
        } else return errorMessageText;
    }

}