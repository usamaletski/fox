package com.selenium.test.testng.tests.desktop;

import com.selenium.test.pages.desktop.DesktopLoginPage;
import com.selenium.test.pages.desktop.DesktopTimelinePage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import com.selenium.test.testng.tests.desktop.*;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;

/**
 * Created by a.maletsky
 * Uses TestNG test framework
 * Tests uses work with Page Object Pattern (https://code.google.com/p/selenium/wiki/PageObjects)
 */
@Listeners({ScreenShotOnFailListener.class})
public class DesktopTimelinePageExtractionPlatformTest extends BaseTest {


    @BeforeTest
    public void beforeTest() { }


//======================================  Timeline page - Extraction (platform) =========================================

    @Test(priority = 1)
    public void testNoEntities () throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("cross", "tomcattom");
        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2014-04-27&entries=4,3,2&fontSize=2&issueDate=2014-04-27&object_id=99005&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_ENG, "Step for check 'No Entities': Displayed and expected messages are not equal.");

        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void testFullEntries() throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");

//      Full entries
        String expectedFullEntries[] = {
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Fri, 20 Dec Deadline Element for extraction on Timeline",
                "Sun, 22 Dec 09:00 17:00  Event Element for extraction on Timeline",
                "Mon, 23 Dec 09:25 Event Element for extraction on Timeline",
                "Sat, 28 Dec 18:00 Event Element for extraction on Timeline",
                "Sat, 28 Dec 19:00 Deadline Element for extraction on Timeline",
                "Sun, 29 Dec Event Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Mon, 30 Dec Deadline Element for extraction on Timeline",
                "Tue, 31 Dec 11:00 Story publ. Element for extraction on Timeline",
                "Tue, 31 Dec 12:00 Story publ. Element for extraction on Timeline",
                "Tue, 31 Dec 12:00 Deadline Element for extraction on Timeline",
                "Tue, 31 Dec Issue Reuters published",
                "No date Deadline Element for extraction on Timeline"};


        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2019-12-31&entries=5,4,3,2,1&issueDate=2019-12-31&object_id=99007&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedFullEntries), "Step for check 'Full Entries': Elements are not equal.");

//      Manual
        String expectedManual[] = {
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Wed, 1 Jan Story publ. Element for extraction on Timeline",
                "Wed, 1 Jan Issue Manual (Manual schedule) published"};

        dn.dTimeline.setURLControlPanelValues("allCategories=1&date=2019-12-31&entries=5,4,3,2,1&formats=18&issueDate=2020-01-01&object_id=99008&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedManual), "Step for check 'Manual pub date': Elements are not equal");


//      St. feature (Never)
        String expectedStFeatureNever[] = {
                "Fri, 3 Jan Story publ. REC Manual - Never",
                "Fri, 3 Jan Issue Manual published"};

        dn.dTimeline.setURLControlPanelValues("allCategories=1&date=2019-12-31&entries=5,4,3,2,1&formats=18&issueDate=2020-01-03&object_id=99008&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedStFeatureNever), "Step for check 'St. feature (Never)': Elements are not equal.");


//      St. feature (2 occurenses)
        String expectedStFeatureOccurenses[] = {
                "Thu, 2 Jan Story publ. REC Manual - 2 occurrences",
                "Thu, 2 Jan Issue Manual published"};

        dn.dTimeline.setURLControlPanelValues("allCategories=1&date=2019-12-31&entries=5,4,3,2,1&formats=18&issueDate=2020-01-02&object_id=99008&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedStFeatureOccurenses), "Step for check 'St. feature (2 occurenses)': Elements are not equal.");


//      St. feature (End)
        String expectedStFeatureEnd[] = {
                "Thu, 2 Jan Story publ. REC Manual - End",
                "Thu, 2 Jan Issue Manual published"};

        dn.dTimeline.setURLControlPanelValues("allCategories=1&date=2019-12-31&entries=5,4,3,2,1&formats=18&issueDate=2020-01-02&object_id=99008&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedStFeatureEnd), "Step for check 'St. feature (End)': Elements are not equal.");


//      Exclude of St. feature
        String expectedExcludeStFeature[] = {
                "Thu, 2 Jan Story publ. REC Manual - Exclude",
                "Thu, 2 Jan Issue Manual published",
                "No date Deadline REC Manual - Exclude"};

        dn.dTimeline.setURLControlPanelValues("allCategories=1&date=2019-12-31&entries=5,4,3,2,1&formats=18&issueDate=2020-01-02&object_id=99008&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedExcludeStFeature), "Step for check 'Exclude of St. feature': Elements are not equal.");

        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void testSorting () throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("cross", "tomcattom");

//      Sorting
        String expectedSorting[] = {
                "Sun, 25 May 12:00 Story publ. Extraction A",
                "Sun, 25 May 12:00 Story publ. Extraction B",
                "Sun, 25 May 12:00 12:00 Event Extraction A",
                "Sun, 25 May 12:00 12:00 Event Extraction B",
                "Sun, 25 May 12:00 Deadline Extraction A",
                "Sun, 25 May 12:00 Deadline Extraction B",
                "Sun, 25 May Issue Associated Press (Sunday) published",
                "No date Deadline Extraction A",
                "No date Deadline Extraction B"};

        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1 &date=2014-05-25&entries=5,4,3,2,1&issueDate=2014-05-25&object_id=99005&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isElementsOrderEquals(expectedSorting), "Step for check 'Sorting': Elements are not equal.");

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testCategoryFilter() throws Exception {

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");

//      Category filter
        String notExpectedEntry[] = {
                "Tue, 31 Dec 12:00 Story publ. Element for extraction on Timeline"};


        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99179&date=2019-12-31&entries=5,4,3,2,1&fontSize=2&issueDate=2019-12-31&object_id=99007&without_date=1#");
        softAssert.assertFalse(dn.dTimeline.isExtractionContainsTitles(notExpectedEntry), "Step for check 'Category filter': Element is represented.");

        softAssert.assertAll();
    }

        @Test(priority = 5)
    public void testMoreFiltersEntries () throws Exception{

            dn.dLoginPage.openPage();
            dn.dLoginPage.doLoginAs("cross", "tomcattom");

//      No date
        String notExpectedNoDate [] = {
                "No date Deadline Extraction A",
                "No date Deadline Extraction B"};

        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2014-05-25&entries=5,4,3,2,1&fontSize=2&issueDate=2014-05-25&object_id=99005&without_date=0");

        softAssert.assertFalse(    dn.dTimeline.isExtractionContainsElements(notExpectedNoDate), "Step for check 'No Date': Elements are represented.");


//      Entries:  Stories
        String expectedEntriesStories [] = {
                "Sun, 25 May 12:00 Story publ. Extraction A",
                "Sun, 25 May 12:00 Story publ. Extraction B"};

            dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2014-05-25&entries=4,1&fontSize=2&issueDate=2014-05-25&object_id=99005&without_date=0");
        softAssert.assertTrue(    dn.dTimeline.isElementsEquals(expectedEntriesStories), "Step for check 'Entries: Stories': Elements are not equal");


//      Entries:  Events
        String expectedEntriesEvents [] = {
                "Sun, 25 May 12:00 12:00 Event Extraction A",
                "Sun, 25 May 12:00 12:00 Event Extraction B"};

            dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2014-05-25&entries=4,2&fontSize=2&issueDate=2014-05-25&object_id=99005&without_date=0");
        softAssert.assertTrue(    dn.dTimeline.isElementsEquals(expectedEntriesEvents), "Step for check 'Entries: Events': Elements are not equal.");


//      Entries:  Deadlines
        String expectedEntriesDeadlines [] = {
                "Sun, 25 May 12:00 Deadline Extraction A",
                "Sun, 25 May 12:00 Deadline Extraction B"};

            dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2014-05-25&entries=4,3&fontSize=2&issueDate=2014-05-25&object_id=99005&without_date=0");
        softAssert.assertTrue(    dn.dTimeline.isElementsEquals(expectedEntriesDeadlines), "Step for check 'Entries: Deadlines': Elements are not equal.");


//      Entries:  Issues
        String expectedEntriesIssues  [] = {
                "Sun, 25 May Issue Associated Press (Sunday) published"};

            dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2014-05-25&entries=4,5&fontSize=2&issueDate=2014-05-25&object_id=99005&without_date=0");
        softAssert.assertTrue(    dn.dTimeline.isElementsEquals(expectedEntriesIssues), "Step for check 'Entries: Issues': Elements are not equal.");

        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void testMoreFilterFormats () throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("cross", "tomcattom");

//      Format filter: One format (Photo Gallery)
        String notExpectedOneFormat[] = {
                "Sun, 25 May 12:00 Story publ. Extraction B",
                "Sun, 25 May 12:00 12:00 Event Extraction B",
                "Sun, 25 May Issue Associated Press (Sunday) published",
                "No date Deadline Extraction B"};

        DesktopTimelinePage desktopTimelinePage = new DesktopTimelinePage();
        desktopTimelinePage.setURLControlPanelValues("allCategories=1&date=2014-05-25&entries=4,5,1,2,3&fontSize=2&formats=6&issueDate=2014-05-25&object_id=99005&without_date=1");

        softAssert.assertTrue(desktopTimelinePage.isElementsEquals(notExpectedOneFormat), "Step for check 'One format (Photo Gallery)': Elements are represented.");


//      Format filter: Two formats (Photo Gallery + Text)
        String expectedTwoFormats[] = {
                "Sun, 25 May 12:00 Story publ. Extraction A",
                "Sun, 25 May 12:00 Story publ. Extraction B",
                "Sun, 25 May 12:00 12:00 Event Extraction A",
                "Sun, 25 May 12:00 12:00 Event Extraction B",
                "Sun, 25 May 12:00 Deadline Extraction A",
                "Sun, 25 May 12:00 Deadline Extraction B",
                "Sun, 25 May Issue Associated Press (Sunday) published",
                "No date Deadline Extraction B"};

        desktopTimelinePage.setURLControlPanelValues("allCategories=1&date=2014-05-25&entries=4,5,1,2,3&fontSize=2&formats=18,6&issueDate=2014-05-25&object_id=99005&without_date=1");
        softAssert.assertTrue(desktopTimelinePage.isElementsEquals(expectedTwoFormats), "Step for check 'Two formats (Photo Gallery + Text)': Elements are not equal.");


//      Element without task
        String expectedElementWithoutTask [] = {
                "Fri, 1 Jan Story publ. Assignments_extraction_W/O_task",
                "Fri, 1 Jan Issue NY Times published"};

        desktopTimelinePage.setURLControlPanelValues("allCategories=1&allFormats=1&date=2016-01-01&entries=4,3,2,1,5&fontSize=2&issueDate=2016-01-01&object_id=99002&without_date=1");
        softAssert.assertTrue(desktopTimelinePage.isExtractionContainsElements(expectedElementWithoutTask), "Step for check 'Element without task': Elements are not equal.");


        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void testConfidentialOrg1() throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");

//      Confidential (visibility)
        String expectedConfidentialOrg1[] = {
                "Personal Assignments"};

        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2222-02-25&entries=4,3,1,2,5&fontSize=2&issueDate=2222-02-25&object_id=99002&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsTitles(expectedConfidentialOrg1), "Step for check 'Confidential (visibility)': Element is not represented.");

        softAssert.assertAll();
    }


    @Test(priority = 8)
    public void testConfidentialEd1() throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ed1", "tomcattom");

//      Confidential (invisibility)
        String notExpectedConfidentialEd1[] = {
                "Personal Assignments"};

        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2222-02-25&entries=4,3,1,2,5&fontSize=2&issueDate=2222-02-25&object_id=99002&without_date=1#");
        softAssert.assertFalse(dn.dTimeline.isExtractionContainsTitles(notExpectedConfidentialEd1), "Step for check ' Confidential (invisibility)': Element is represented.");

        softAssert.assertAll();
    }

    @Test(priority = 9)
    public void testTitles() throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");

//      Line break
        String expectedTitleWithLineBreak[] = {
                "Assignments_extraction_stand_feature_OCCURRENCE_ONE\n" +
                        "Line\n" +
                        "\n" +
                        "break"};

        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2016-02-03&entries=4,3,2,1,5&fontSize=2&issueDate=2016-02-02&object_id=99002&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsTitles(expectedTitleWithLineBreak), "Step for check 'Line break': Element is not equal.");


//      Topic (Hashtag in title)
        String expectedTitleWithHashtag [] = {
                "Element 03 #topicN2"};

        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1&date=2014-04-04&entries=4,3,2,1,5&fontSize=2&issueDate=2014-04-04&object_id=99003&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsTitles(expectedTitleWithHashtag), "Step for check 'Topic (Hashtag in title)': Element is not equal.");


        softAssert.assertAll();
    }

    @Test(priority = 10)
    public void testTitlesTaskNote() throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");
        dn.dTimeline.openPage();

        //      Task note (with umlauts)
        String expectedTitleWithTaskNote[] = {
                "Element for extraction on Timeline | Note with umlauts È Ü"};

        dn.dTimeline.setURLControlPanelValues("allCategories=1&date=2019-12-31&entries=5,4,3,2,1&formats=1&issueDate=2020-01-01&object_id=99008&without_date=1#");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsTitles(expectedTitleWithTaskNote), "Step for check 'Task note (with umlauts)': Element is not equal.");

//
////      Dots at the end
//        String expectedTitleWithDots [] = {
//                "Assignments_extraction_stand_feature_OCCURRENCE_ONE..."};
//
//        desktopTimelinePage.setURLControlPanelValues("allCategories=1&allFormats=1&date=2016-02-03&entries=4,3,2,1,5&fontSize=2&issueDate=2016-02-02&object_id=99002&without_date=1#");
//        softAssert.assertTrue(desktopTimelinePage.isExtractionContainsTitles(expectedTitleWithDots), "Step for check 'Dots at the end of title': Element is not equal");

        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() { }

}