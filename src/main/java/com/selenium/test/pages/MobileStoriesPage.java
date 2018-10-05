package com.selenium.test.pages;

import org.junit.Assert;
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
 * Mobile Stories Page object
 */
public class MobileStoriesPage extends MobileControlPanelPage {

    private static MobileStoriesPage mStories;

    private static final String PAGE_URL = "mobile.htm#StoryLists:";

    @FindBy(xpath = "//div[text()='STORIES']") //TODO optimize for multi locales (.. OR ..)
    private WebElement pageTitle;

    @FindBy(className = "no-content-panel")
    private WebElement noContentPanelElement;

    @FindBys(@FindBy(xpath = "//span[@class='elementTitleContent']"))
    private List<WebElement> elementsTitles;

    //Properties and text resources TODO Move it to config.properties?
    public static String messageNoContentStories_ENG = "No stories.";

    public static MobileStoriesPage getInstance() throws Exception {
        if (mStories == null) mStories = new MobileStoriesPage();
        return mStories;
    }

    public MobileStoriesPage() throws Exception {
        super();
    }

    public MobileStoriesPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open Stories page with default url for specified mobile.application.url
     */
    @Override
    public void openPage() throws Exception {
        openPage(PAGE_URL);
    }

    /**
     * @return true when Stories page is opened (title exists and wait 5 sec(temporary solution))
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

    public int getExtractedTitlesCount() {
        return elementsTitles.size();
    }


    /**
     * Compare Titles elements without order checking
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
            System.out.println("ACTUAL LIST: " + getAllExtractedTitles());
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
            System.out.println("ACTUAL LIST: " + getAllExtractedTitles());
            return false;
        }
    }

}