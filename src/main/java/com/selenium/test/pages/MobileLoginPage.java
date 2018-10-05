package com.selenium.test.pages;

import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.concurrent.TimeUnit;

/**
 * Mobile Login Page object
 */
public class MobileLoginPage extends BasePage {

    private static MobileLoginPage mLoginPage;

    private static final String PAGE_URL = "login.htm";

    @FindBy(name = "j_username")
    private WebElement usernameInput;

    @FindBy(name = "j_password")
    private WebElement passwordInput;

	@FindBy(className = "aCheckBox")
	private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@class='gwt-Label' and text()='LOGIN']")
    private WebElement loginButtonElement;

    @FindBy(xpath = "//div[@class='gwt-Label' and text()='Reset your password']")
	private WebElement forgotPasswordLink;

	@FindBy(className ="bullet")
	private WebElement errorMessage;

    //Locator for start page (universal)
    @FindBy(id = "element-container-wrapper")
    private WebElement locatorStartPage;

    //Properties and text resources TODO Move it to config.properties?
    public static String errorMessageIncorrectLogin_ENG = "You could not sign in. Please check your entries and try again.";

    public static MobileLoginPage getInstance() throws Exception {
        if (mLoginPage == null) mLoginPage = new MobileLoginPage();
        return mLoginPage;
    }

    public MobileLoginPage() throws Exception {
        super();
    }

    public MobileLoginPage(boolean openPageByUrl) throws Exception {
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
        WebDriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue("Start page is not represented",locatorStartPage.isDisplayed());
    }

    /**
     * Performs the simple login
     * @return results page instance
     * @param userName for input
     * @param password for input
     */
    public void doLoginAs(String userName, String password) throws Exception {
        //Open login Page
        this.openPage();
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

    /**
     * @return expected text for property by variable name ("Properties and text resources" block)
     * @param propertyName for input
     */
    public String getExpectedPropertyText(String propertyName) {
        try
        {
            return (String) (String) this.getClass().getDeclaredField(propertyName).get(this);
        }catch (Exception e)
        {
            return "Property is not found";
        }
    }
}