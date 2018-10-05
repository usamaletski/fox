package com.selenium.test.pages;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.utils.DNUtils;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * This is the main class for pages. When you create your page - you must extend your class from this
 */
public abstract class BasePage {
    protected static final int WAIT_FOR_PAGE_LOAD_IN_SECONDS = 5;
    // TODO We need waiting logic here based on 'is-page-fully-loaded' element state (like in Desktop app)
    //protected static final int WAIT_FOR_PAGE_FULLY_LOADED_IN_SECONDS = 5;
    /**
     * In subclasses  should be used for page opening
     */
    protected abstract void openPage() throws Exception;

    /**
     * checks is page opened
     * @return true if opened
     */
    public abstract boolean isPageOpened();

    public BasePage() throws Exception {
        PageFactory.initElements(getDriver(), this);
    }

    public BasePage(boolean openPageByUrl) throws Exception {
        if(openPageByUrl){
            openPage();
        }
        PageFactory.initElements(getDriver(), this);
        waitForPageOpen();
    }

    public void openPage(String url) throws Exception {
        String appUrl = TestsConfig.getConfig().getMobileAppUrl();
        DNUtils.navigateURL(appUrl + url);
    }

    /**
     * @param date in YYYY-MM-DD format
     */
    public void navigateToDate(String page_url, String date) {
        String appUrl = TestsConfig.getConfig().getMobileAppUrl();
        DNUtils.navigateURL(appUrl + page_url + "date=" + date);
    }

    /**
     * Waiting for page opening
     */
    protected void waitForPageOpen() {
        getDriver().manage().timeouts().implicitlyWait(WAIT_FOR_PAGE_LOAD_IN_SECONDS, TimeUnit.SECONDS);
            if (!isPageOpened()) {
                 throw new AssertionError("Page was not opened");
            }
    }

//    TODO We need waiting logic here based on 'is-page-fully-loaded' element state (like in Desktop app)
//    protected void waitForPageLoad() {
//        WebDriverFactory.getDriver().manage().timeouts().implicitlyWait(WAIT_FOR_PAGE_FULLY_LOADED_IN_SECONDS, TimeUnit.SECONDS);
//        }

    /**
     * getting webdriver instance
     * @return initialized in tests webdriver instance
     */
    protected WebDriver getDriver(){
        return WebDriverFactory.getDriver();
    }

}