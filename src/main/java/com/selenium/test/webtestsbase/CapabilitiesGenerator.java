package com.selenium.test.webtestsbase;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Class presents functionality for generation of {@link org.openqa.selenium.remote.DesiredCapabilities} object
 * need for some browsers start
 */
public class CapabilitiesGenerator {

    /**
     * getting {@link org.openqa.selenium.remote.DesiredCapabilities} object based on browser
     * ATTENTION: you should specify the path to chrome driver executable file to run tests on it(@see <a href="https://sites.google.com/a/chromium.org/chromedriver/getting-started">here for more info</a>)
     * @param browser {@link com.selenium.test.webtestsbase.Browser} object
     * @return capabilites needed for some browsers start
     */
    public static DesiredCapabilities getDefaultCapabilities(Browser browser) {
        switch (browser) {
            case FIREFOX:
                return DesiredCapabilities.firefox();
            case CHROME:
                /*//[svo]Following is actual for DESKTOP CHROME
                if (System.getProperty("webdriver.chrome.driver") == null) {
                    throw new IllegalStateException("System variable 'webdriver.chrome.driver' should be set to path for executable driver");
                }
                return DesiredCapabilities.chrome();*/
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
                caps.setCapability(MobileCapabilityType.TAKES_SCREENSHOT,"true");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME,"0123456789ABCDEF");
                return caps;
            case IE10:
                DesiredCapabilities capsIE = DesiredCapabilities.internetExplorer();
                capsIE.setVersion("10");
                return capsIE;
            case SAFARI:
                return new DesiredCapabilities();
            default:
                throw new IllegalStateException("Browser is not supported");
        }
    }

}
