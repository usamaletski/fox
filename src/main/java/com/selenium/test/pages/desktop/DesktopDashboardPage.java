package com.selenium.test.pages.desktop;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.pages.desktop.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.selenium.test.utils.TimeUtils.waitForSeconds;

/**
 * Mobile Desktop Dashboard Page object
 */
public class DesktopDashboardPage extends BasePage {

    private static final String PAGE_URL = "dashboardPage.htm";

    private static DesktopDashboardPage dDashboard;

    @FindBy(xpath = "//span[@class='controlHeader' and text()='Dashboard']")
    //TODO optimize for multi locales (.. OR ..)
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@id='page-is-fully-loaded' and @value='true']")
    private WebElement locatorPageFullyLoaded;

    @FindBy(xpath = "//input[@id='page-is-fully-loaded' and @value='false']")
    private WebElement locatorPageNotFullyLoaded;

    public static DesktopDashboardPage getInstance() throws Exception {
        if (dDashboard == null) dDashboard = new DesktopDashboardPage();
        return dDashboard;
    }

    public DesktopDashboardPage() throws Exception {
        super();
    }

    public DesktopDashboardPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open Dashboard page with default url for specified mobile.application.url
     */
    @Override
    public void openPage() throws Exception {
        openPage(PAGE_URL);
    }

    /**
     * @return true when Dashboard page is opened (title exists and wait 5 sec(temporary solution))
     */
    @Override
    public boolean isPageOpened() {
        locatorPageFullyLoaded.isDisplayed();
        try {
            return  pageTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}