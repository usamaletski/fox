package com.selenium.test.utils;

import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Operations with time
 */
public class TimeUtils {

    /**
     * waiting for seconds TODO Refactor to avoid Thread.sleep using
     * @param timeoutInSeconds timeout in seconds for wait
     */
    public static void waitForSeconds(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set pre-conditions for waitForPageIsLoaded:
     * added var window.added _testPageLoading = 'false' by default
     *  - get 'true' value, when there are any active js requests on the page;
     *  - get 'false' value, when the page is fully loaded(no js requests are active);
     *
     *  should be run after each page reload
     */
    public static void startVerifyPageLoading() {
        System.out.println("INFO: startVerifyPageLoading() method started");

        JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getDriver();

        String jsCode;
        jsCode = "window._testPageLoading !== undefined ? 0 : (function() {" +
                "                       window._testPageLoading = false;" +
                "                       var active = 0;" +
                "                       var origOpen = XMLHttpRequest.prototype.open;" +
                "                       XMLHttpRequest.testActive = function() {" +
                "                           if (active == 0) {" +
                "                               window._testPageLoading = false;" +
                "                           }" +
                "                       };" +
                "                       XMLHttpRequest.prototype.open = function() {" +
                "                           window._testPageLoading = true;" +
                "                           active++;" +
                "                           this.addEventListener('load', (function() {" +
                "                               active--;" +
                "                               XMLHttpRequest.testActive();" +
                "                           }));" +
                "                           origOpen.apply(this, arguments);" +
                "                       };" +
                "                   })();";

        js.executeScript(jsCode);
    }

    /**
     * Waiting for page is fully loaded - no js requests are in progress
     *
     * pre-condition:
     * startVerifyPageLoading() method should be called before using this wait to set up _testPageLoading var
     *
     * @param timeout timeout in mseconds for wait
     */
    public static void waitForPageIsLoaded(int timeout) {
        System.out.println("INFO: waitForPageIsLoaded method started with parameter = " + timeout);

        JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getDriver();

        long pause = 250;
        long start = System.currentTimeMillis();
        long finish = start + timeout;
        try {
            do {
                Thread.sleep(pause);
            }while (Boolean.valueOf(js.executeScript("return window._testPageLoading").toString()) && (System.currentTimeMillis() <= finish));

            System.out.println("INFO: Waiting for Page is loaded took " + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            System.out.println("EXCEPTION: WaitForPageIsLoaded Exception: " + e);
        }

    }
}