package webDriver;

import org.openqa.selenium.WebDriver;
import supportFactory.WebdriverFactory;
import testRunner.TestRunner;

import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver webdriver;
    private static int implicitWait = Integer.parseInt(TestRunner.config.get("implicitWait"));
    private static int pageLoadTimeout = Integer.parseInt(TestRunner.config.get("pageLoadTimeout"));

    public synchronized static WebDriver getCurrentDriver() {
        if (webdriver == null) {
            webdriver = WebdriverFactory.createWebdriver();
            assert webdriver != null;
            webdriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
            webdriver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            webdriver.manage().timeouts().setScriptTimeout(implicitWait, TimeUnit.SECONDS);
            webdriver.manage().window().maximize();
        }
        return webdriver;
    }

    public static void cleanUp() {
        try {
            getCurrentDriver().close();
        } catch (Exception e) {
            throw new RuntimeException("Cannot close browser", e);
        }
    }
}