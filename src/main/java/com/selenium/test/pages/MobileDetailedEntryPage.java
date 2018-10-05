package com.selenium.test.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.selenium.test.utils.TimeUtils.waitForSeconds;

/**
 * Mobile Detailed Entry page
 */
public class MobileDetailedEntryPage extends BasePage {

    private static MobileDetailedEntryPage mDetailedEntry;

    private static final String PAGE_URL = "mobile.htm#DetailedEntry:";

    public static String MESSAGE_RO_MODE_ENG = "You can read, but not edit this entry.";

    @FindBy(xpath = "//div[@class='headTitle']")
    private WebElement pageTitle;

    @FindBy(className ="bullet")
    private WebElement locatorErrorMessage;

    @FindBy(xpath ="//a[contains(@class,'button bold primary-button')]")
    private WebElement locatorSaveButton;

    // Universal locator for any button
    String locatorButton = "//a[contains(@class,'button') and text()='%s']";

    public static MobileDetailedEntryPage getInstance() throws Exception {
        if (mDetailedEntry == null) mDetailedEntry = new MobileDetailedEntryPage();
        return mDetailedEntry;
    }

    public MobileDetailedEntryPage() throws Exception {
        super();
    }

    public MobileDetailedEntryPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open Detailed Entry page with default url for specified mobile.application.url
     */
    @Override
    public void openPage() throws Exception {
        openPage(PAGE_URL);
    }

    /**
     * @return true when Detailed Entry page is opened (title exists and wait 5 sec(temporary solution))
     */
    @Override
    public boolean isPageOpened() {
        try {
            //TODO Here we must use similar logic as in Desktop: "is-page-fully-loaded" element's state
            waitForSeconds(5);
            return pageTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private WebElement getLocatorButton (String buttonName) {
        return getDriver().findElement(By.xpath(String.format(locatorButton, buttonName)));
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return locatorErrorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //TODO: optimize the same method for Login page
    public String getErrorMessageText() {
        Assert.assertTrue("Error message is not displayed",isErrorMessageDisplayed());
        return locatorErrorMessage.getText();
    }

    private boolean isButtonDisabled(WebElement locatorButton) {
        if ((locatorButton.getAttribute("class")).contains("disabled")) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isDEPageOpenedInROMode() {
        if ((getErrorMessageText().equals(MESSAGE_RO_MODE_ENG)) && isButtonDisabled(locatorSaveButton)) return true;
        else return false;
    }


}