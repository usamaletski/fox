package supportFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import webDriver.Driver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebdriverFactory {
    private static DesiredCapabilities caps;

    public static WebDriver createWebdriver() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        caps = new DesiredCapabilities();
        Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
        return BrowserFactory.selectLocalBrowser();
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            Driver.cleanUp();
        }
    }
}
