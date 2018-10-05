package com.selenium.test.utils;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provides general utility functionality that is not part of the application
 */
public class DNUtils {

    public static WebDriver driver;
    public static TestsConfig config;

    public static void init() throws Exception {
        driver = WebDriverFactory.getDriver();
        config = TestsConfig.getConfig();
    }

    /**
     * Loads a new URL in the browser window.
     * Waits for Page is loaded after navigating with using 'clever' wait
     * @param url the URL to be loaded by the browser
     */
    public static void navigateURL(String url){
        System.out.println("INFO: Loads URL: " + url);
        driver.get(url);
        TimeUtils.startVerifyPageLoading();
        TimeUtils.waitForPageIsLoaded(10000);
    }

    /**
     * Method for screenshot taking.
     * This method calls in tests listeners on test fail at least
     */
    public static void takeScreenShot() throws IOException {
        System.out.println("ScreenshotOnError method called");

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(config.getScreenshotsLocation() + "ScreenshotOnError" + getCurrentTimeStamp("_ddMMyyyy_HHmmss") + ".png"));
    }

    /**
     * Gets Current Date and Time
     * @param format format to be used when forming the date/time stamp e.g. "yyyy/MM/dd HH:mm"
     * @return String representation of Date and Time e.g. "2017/01/30 16:45"
     */
    public static String getCurrentTimeStamp(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();

        return dateFormat.format(date);
    }

}
