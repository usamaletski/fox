package supportFactory;

import enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import testRunner.TestRunner;

public class BrowserFactory {
    static Browser browser = Browser.valueOf(TestRunner.config.get("browser"));

    public static WebDriver selectLocalBrowser() {
        switch (browser) {
            case Chrome:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--test-type");
                return new ChromeDriver(options);
            case Firefox:
                return new FirefoxDriver();
            case IE:
                return new InternetExplorerDriver();
            case Safari:
                return new SafariDriver();
            default:
                throw new WebDriverException("No browser specified");
        }
    }
}
