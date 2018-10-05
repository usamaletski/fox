package com.selenium.test.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.selenium.test.utils.TimeUtils.waitForSeconds;

/**
 * Mobile Calendar Page object
 */
public class MobileCalendarPage extends MobileControlPanelPage {

    private static MobileCalendarPage mCalendar;

    private static final String PAGE_URL = "mobile.htm#Events:";

    @FindBy(xpath = "//div[text()='CALENDAR']") //TODO optimize for multi locales (.. OR ..)
    private WebElement pageTitle;

    @FindBy(className = "no-content-panel")
    private WebElement noContentPanelElement;

    @FindBys(@FindBy(xpath = "//span[@class='elementTitleContent']"))
    private List<WebElement> elementsTitles;

    String locatorElementTitle = "//span[@class='elementTitleContent']/span[text()='%s']";

//  locators for details in element panel (for further use in tests)
    String typeElement= "//div[contains(@class,'page-type-item')]";
    String statusElement= "//div[contains(@class,'status-item')]";
    String producerElement= "//div[contains(@class,'producers-item')]";
//    locator for type according element (for further use in tests)
    String typeByElement = "//span[text()='%s']/parent::span[@class='elementTitleContent']/parent::div/following-sibling::div//div[contains(@class,'page-type-item')]";

    public static MobileCalendarPage getInstance() throws Exception {
        if (mCalendar == null) mCalendar = new MobileCalendarPage();
        return mCalendar;
    }

    private WebElement getLocatorTypeElement(String elementTitle) {
        return getDriver().findElement(By.xpath(String.format(typeByElement, elementTitle)));
    }

    public static String messageNoContentStories_ENG = "No events/appointments.";

    public MobileCalendarPage() throws Exception {
        super();
    }

    public MobileCalendarPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open Calendar page with default url for specified mobile.application.url
     */
    @Override
    public void openPage() throws Exception {
        openPage(PAGE_URL);
    }

    /**
     * @return true when Calendar page is opened (title exists and wait 5 sec(temporary solution))
     */
    @Override
    public boolean isPageOpened() {
        try {
            //TODO Here we must use similar logic as in Desktop: "is-page-fully-loaded" element's state
            waitForSeconds(5);
            return pageTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @param date in YYYY-MM-DD format
     */
    public void navigateToDate(String date) {
        navigateToDate(PAGE_URL, date);
    }

    /**
     * @return displayed state of no content hint
     */
    public boolean isNoContentHintDisplayed() {
        try {
            return noContentPanelElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @return text for no content hint
     */
    //TODO: After review optimize the same method for My Schedule page
    public String getNoContentText() {
        Assert.assertTrue("No content hint is not displayed",isNoContentHintDisplayed());
        return noContentPanelElement.getText();
    }


    /**
     * @return all titles elements as List<String>
     */
    private List<String> getAllExtractedTitles() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        ArrayList<String> TitlesValues = new ArrayList<String>();
        for (int i = 0; i < elementsTitles.size(); i++) {
            js.executeScript(" arguments[0].scrollIntoView(true);",elementsTitles.get(i));
            TitlesValues.add(elementsTitles.get(i).getText()); // add in List<String> TitlesValues all values from getAllWebElementsByTitle
        }
        return TitlesValues;
    }

    /**
     * Get count of extracted titles
     * @return
     */
    private int getExtractedTitlesCount() {
        return elementsTitles.size();
    }


    /**
     * Compare element titles without order checking but with count checking
     *
     * @param expectedTitles Array with expected titles result
     * @return boolean
     */
    //TODO: optimize using Assert.assertEquals
    public boolean compareTitles(String[] expectedTitles) {
        ArrayList<String> expectedTitlesList = new ArrayList<String>(Arrays.asList(expectedTitles)); // conversion Array expectedTitles into List<String>
        if ((expectedTitlesList.size() == getExtractedTitlesCount()) && (getAllExtractedTitles().containsAll(expectedTitlesList))) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedTitlesList);
            System.out.println("ACTUAL LIST  : " + getAllExtractedTitles());
            return false;
        }
    }


    /**
     * Check extraction contains expected titles
     *
     * @param expectedTitles elements without order checking
     * @return
     */
    //TODO: pass method into Stories page
    //TODO: optimize using Assert.assertEquals
    public boolean isExtractionContainsExpectedTitles(String[] expectedTitles) {
        ArrayList<String> expectedTitlesList = new ArrayList<String>(Arrays.asList(expectedTitles)); // conversion Array expectedTitles into List<String>
        if (getAllExtractedTitles().containsAll(expectedTitlesList)) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedTitlesList);
            System.out.println("ACTUAL LIST:   " + getAllExtractedTitles());
            return false;
        }
    }

    /**
     * Compare correct order elements
     *
     * @param expectedElementsOrder Array with expected result in correct order
     * @return
     */
    //TODO: optimize using Assert.assertEquals
    public boolean compareElementsOrder(String[] expectedElementsOrder) {
        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedElementsOrder)); // conversion Array expectedTitles into List<String>
        if ((expectedElementsList.size() == getAllExtractedTitles().size()) && (getAllExtractedTitles().equals(expectedElementsList))) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedElementsList);
            System.out.println("ACTUAL LIST:   " + getAllExtractedTitles());
            return false;
        }
    }

    //TODO: pass method into Stories page
    public WebElement getLocatorElementByTitle (String elementTitle) {
        return getDriver().findElement(By.xpath(String.format(locatorElementTitle, elementTitle)));
    }


    //TODO: pass method into Stories page
    public void openElementByTitle(String elementTitle) {
        Assert.assertTrue("Element is represented", getAllExtractedTitles().contains(elementTitle));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", getLocatorElementByTitle(elementTitle));
    }

// Element panel's details checking (just for Type now)
    public String getElementType(String elementTitle) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getLocatorTypeElement(elementTitle));
        return getLocatorTypeElement(elementTitle).getText();
    }

    public boolean compareElementType (String elementTitle, String elementType){
        if (elementType.equals(getElementType(elementTitle))) {
            return true;
        } else {
            System.out.println("EXPECTED ELEMENT Type: " + elementType);
            System.out.println("ACTUAL  ELEMENT Type: " + getElementType(elementTitle));
            return false;
        }
    }


}