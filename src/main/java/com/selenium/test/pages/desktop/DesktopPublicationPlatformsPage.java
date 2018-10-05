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
 * Desktop Publication Platforms Page object
 */
public class DesktopPublicationPlatformsPage extends BasePage {

    private static final String PAGE_URL = "objectsPage.htm";

    private static DesktopPublicationPlatformsPage dPublicationPlatform;

    @FindBy(xpath = "//div[@class='title admin-title' and (text()='Publication platforms' or text()='Publikationskanäle' or text()='Canaux de publication' or text()='Plataformas de publicación')]")
    private WebElement pageTitle;

    //TODO optimize for multi locales (.. OR ..)
    @FindBys(@FindBy(xpath = "//span[@class='textHover' and text()='Delete']"))
    private List<WebElement> buttonsDelete;

    //TODO optimize for multi locales (.. OR ..)
    @FindBy(xpath = "//span[@class='textHover' and text()='Delete']")
    private WebElement buttonDelete;

    @FindBy(className = "middleCenterInner")
    private WebElement dialogConfirmation;

    @FindBy(xpath = "//a[@class='button bold primary-button']")
    private WebElement buttonYes;

    @FindBy(className = "button bold secondary-button")
    private WebElement buttonNo;

    public static DesktopPublicationPlatformsPage getInstance() throws Exception {
        if (dPublicationPlatform   == null) dPublicationPlatform   = new DesktopPublicationPlatformsPage();
        return dPublicationPlatform;
    }

    public DesktopPublicationPlatformsPage() throws Exception {
        super();
    }

    public DesktopPublicationPlatformsPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open Publication Platforms page with default url for specified mobile.application.url
     */
    @Override
    protected void openPage() {
        String appUrl = TestsConfig.getConfig().getDesktopAppUrl();
        getDriver().get(appUrl + PAGE_URL);
    }

    /**
     * @return true when Publication Platforms page is opened (title exists and wait 5 sec(temporary solution))
     */
    @Override
    public boolean isPageOpened() {
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            return pageTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void deleteAllPlatforms() {
        for (int i = buttonsDelete.size() - 1; i >= 0; i--) {
            getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            try {
                buttonsDelete.get(i).click();
                dialogConfirmation.isDisplayed();
                buttonYes.click();
                getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            } catch (NoSuchElementException e) {
            }
        }
    }

}