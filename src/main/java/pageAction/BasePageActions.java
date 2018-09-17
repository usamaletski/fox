package pageAction;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.BasePage;
import pageObject.UserProfilePage;
import testRunner.TestRunner;
import webDriver.Driver;

public class BasePageActions {
    protected static WebDriver driver = Driver.getCurrentDriver();
    private static int wdWait = Integer.parseInt(TestRunner.config.get("wdWait"));
    public static WebDriverWait wait = new WebDriverWait(driver, wdWait);
    private static int sleepTimeout = Integer.parseInt(TestRunner.config.get("msSleep"));

    public static void open() throws InterruptedException {
        BrowserActions.openUrl(TestRunner.config.get("siteUrl"));
    }

    public static void openUserProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(BasePage.userProfile()));
        BasePage.userProfile().click();
        wait.until(ExpectedConditions.elementToBeClickable(UserProfilePage.treatPage()));
    }

    public static void openTopCategory(String topCategory) {
        BasePage.topCategory(topCategory).click();
    }

    public static void scrollDown() throws InterruptedException {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    protected static void scrollToElement(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
    }

    protected static void sleep() throws InterruptedException {
        Thread.sleep(sleepTimeout);
    }

    public static class DropDown {
        public static boolean isOpened() {
            try {
                wait.until(ExpectedConditions.visibilityOf(BasePage.DropDown.layer()));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        public static void open(WebElement ele) {
            scrollToElement(ele);
            ele.click();
        }

        public static void set(String item) {
            if (isOpened()) {
                scrollToElement(BasePage.DropDown.item(item));
                BasePage.DropDown.item(item).click();
            }
        }
    }
}