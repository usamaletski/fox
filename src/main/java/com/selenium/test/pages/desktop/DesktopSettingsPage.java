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

/**
 * Desktop Settings Page object
 */
public class DesktopSettingsPage extends BasePage {

    private static final String PAGE_URL = "mySettingsPage.htm";

    private static DesktopSettingsPage dSettings;

    private static int EN_UK = 0;
    private static int EN_US = 1;
    private static int DE = 2;
    private static int FR = 3;
    private static int ES = 4;

    Locales locale;

    @FindBy(xpath = "//div[@class='title' and (text()='My Settings' or text()='Meine Einstellungen' or text()='Mes Paramétrages' or text()='Mi Configuración')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='middleCenterInner']")
    private WebElement layerSelectBox;

    @FindBy(xpath = "//span[.='Language' or .='Sprache' or .='Langue' or .='Idioma']/parent::td[@class='fields-table-label']/following-sibling::td/div[@class='inline-list-box selected']")
    private WebElement elementsSelectBoxLanguage;

    @FindBy(xpath = "//a[@class='button bold primary-button']")
    private WebElement btnSave;

    @FindBys(@FindBy(className = "list-box-item"))
    private List<WebElement> elementsSelectBox;

    String locatorSelectBox = "//span[text()='%s']/parent::td[@class='fields-table-label']/following-sibling::td/div[@class='inline-list-box selected']";

    private WebElement getSelectBoxByLabelName(String nameSelectBox) {
        return getDriver().findElement(By.xpath(String.format(locatorSelectBox, nameSelectBox)));
    }

    public static DesktopSettingsPage getInstance() throws Exception {
        if (dSettings    == null) dSettings    = new DesktopSettingsPage();
        return dSettings;
    }

    public DesktopSettingsPage() throws Exception {
        super();
    }

    public DesktopSettingsPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open Settings page with default url for specified mobile.application.url
     */
    @Override
    public void openPage() throws Exception {
        openPage(PAGE_URL);
    }

    /**
     * @return true when Settings page is opened (title exists and wait 5 sec(temporary solution))
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

    private void openSelectBox(String nameSelectBox) {
        getSelectBoxByLabelName(nameSelectBox).click();
    }

    public enum Locales {EN_UK, EN_US, DE, FR, ES}

//    TODO: add check that value is selected or select box is not empty. Catch NoSuchElementException
    public void setLanguage (Locales locale){
        this.locale = locale;
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        elementsSelectBoxLanguage.click();
        switch (locale) {
            case EN_UK: elementsSelectBox.get(EN_UK).click();
                break;
            case EN_US: elementsSelectBox.get(EN_US).click();
                break;
            case DE: elementsSelectBox.get(DE).click();
                break;
            case FR: elementsSelectBox.get(FR).click();
                break;
            case ES: elementsSelectBox.get(ES).click();
                break;
            default: elementsSelectBox.get(0).click();
                break;
        }
        btnSave.click();
    }




}