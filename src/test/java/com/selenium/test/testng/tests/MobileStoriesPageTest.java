package com.selenium.test.testng.tests;

import com.selenium.test.pages.MobileLoginPage;
import com.selenium.test.pages.MobileStoriesPage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by a.maletsky
 * Uses TestNG test framework
 * Tests uses work with Page Object Pattern (https://code.google.com/p/selenium/wiki/PageObjects)
 */
@Listeners({ScreenShotOnFailListener.class})
public class MobileStoriesPageTest extends BaseTest{

    @BeforeTest
    public void beforeTest() { }

    /**
     * Check 'no content hint' occurrence, text, color
     */
    @Test(priority = 1)
    public void testNoContentHint() throws Exception {
        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("new", "tomcattom");
        dn.mStories.openPage();
        dn.mStories.navigateToDate("2016-11-30");

        //Check no content hint's text
        softAssert.assertEquals(dn.mStories.getNoContentText(), MobileStoriesPage.messageNoContentStories_ENG, "Displayed and expected messages are not equal");
        softAssert.assertAll();
    }


    /**
     * Check the extraction's integrity on the Stories page
     * TODO: make full content extraction using control panel. Analyze: Is content full?
     */
    @Test(priority = 2)
    public void testExtractionIntegrity() throws Exception {
        //Expectations
        int expectedElementsCount = 4;

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mStories.openPage();

        dn.mStories.navigateToDate("2016-10-16");
        //Assertion for checking the number of extracted elements
        softAssert.assertEquals(dn.mStories.getExtractedTitlesCount(), expectedElementsCount, "Number of elements");
        softAssert.assertAll();
    }


    /**
     * Check extracted titles on the Stories page
     * TODO: make full content extraction using control panel. Analyze: Is content full?
     */
    @Test(priority = 3)
    public void testExtractedTitlesContent() throws Exception {
        //Expectations
        String expectedTitles[] = {"Mobile 063", "Mobile 062", "Mobile 064", "Mobile 065"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mStories.openPage();
        dn.mStories.navigateToDate("2016-10-16");

        //Assertion for checking the titles of extracted elements
        softAssert.assertTrue(dn.mStories.compareTitles(expectedTitles), "Titles of elements not equal");
        softAssert.assertAll();
    }


    /**
     * Check the order for the extracted content on the Stories page
     *
     */
    @Test(priority = 4)
    public void testContentOrder() throws Exception {
        //Expectations
        String expectedElementsOrder[] = {"Top5 C", "Top5 A", "Top5 B", "Top1", "Top3", "Top2", "Top4"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("org1", "tomcattom");
        dn.mStories.openPage();
        dn.mStories.navigateToDate("2012-12-12");

        //Assertion for checking the order of extracted elements
        softAssert.assertTrue(dn.mStories.compareElementsOrder(expectedElementsOrder), "Sorting of elements not equals");
        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() { }

}