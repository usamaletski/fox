package pageAction;

public class BrowserActions extends BasePageActions {
    public static void openUrl(String websiteUrl) throws InterruptedException {
        driver.get(websiteUrl);
    }
}