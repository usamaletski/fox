package com.selenium.test.testng.tests.desktop;

import com.selenium.test.pages.desktop.DesktopLoginPage;
import com.selenium.test.pages.desktop.DesktopSettingsPage;
import com.selenium.test.pages.desktop.DesktopTimelinePage;
import com.selenium.test.pages.desktop.DesktopTopicsPage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
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
public class DesktopTimelinePageExtractionTopicTest extends BaseTest {


    @BeforeTest
    public void beforeTest() { }



    //======================================  Timeline page - Extraction (topic) =========================================
    @Test(priority = 1)
    public void testPrerequisites () throws Exception{
//        Step 2
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");
        dn.dTopic.openPage();
        dn.dTopic.setTopicDates("Topic N9","01.12.2019","20.04.2020");

//        TODO: Assertion for set values
//        TODO: LogOut and split with link steps (see manual test)
        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void testUserOrg1 () throws Exception {
//        Step#3

        String expectedElement_Step3[] = {
                "Mon, 11 Nov Event Element 13",
                "Mon, 11 Nov Deadline Element 13",
                "Tue, 12 Nov Story publ. Element 13",
                "Tue, 12 Nov Issue NY Times published",
                "Sat, 1 Mar Event Element 25",
                "Sat, 1 Mar Deadline Element 25",
                "Mon, 3 Mar Story publ. Element 25",
                "Mon, 3 Mar Issue NY Times published",
                "Sun, 30 Mar Event Element 20",
                "Sun, 30 Mar Deadline Element 20",
                "Mon, 31 Mar Story publ. Element 20",
                "Mon, 31 Mar Event Element 24",
                "Mon, 31 Mar Deadline Element 24",
                "Mon, 31 Mar Issue NY Times published",
                "Tue, 1 Apr Story publ. Element 24",
                "Tue, 1 Apr Issue NY Times published",
                "Thu, 3 Apr Event Element 18",
                "Thu, 3 Apr Deadline Element 18",
                "Fri, 4 Apr Story publ. Element 18",
                "Fri, 4 Apr Event Element 16",
                "Fri, 4 Apr Event Element 26",
                "Fri, 4 Apr Deadline Element 16",
                "Fri, 4 Apr Deadline Element 26",
                "Fri, 4 Apr Issue NY Times published",
                "Sat, 5 Apr Story publ. Element 16",
                "Sat, 5 Apr Event Element 17",
                "Sat, 5 Apr Deadline Element 17",
                "Sat, 5 Apr Issue NY Times published",
                "Sun, 6 Apr Event Element 19",
                "Sun, 6 Apr Deadline Element 19",
                "Mon, 7 Apr Story publ. Element 17",
                "Mon, 7 Apr Story publ. Element 19",
                "Mon, 7 Apr Issue NY Times published",
                "Wed, 9 Apr Event Element 21",
                "Wed, 9 Apr Deadline Element 21",
                "Thu, 10 Apr Story publ. Element 21",
                "Thu, 10 Apr Issue NY Times published",
                "Thu, 15 May Event Element 22",
                "Thu, 15 May Deadline Element 22",
                "Fri, 16 May Story publ. Element 22",
                "Fri, 16 May Issue NY Times published",
                "Sat, 31 May Event Element 23",
                "Sat, 31 May Deadline Element 23",
                "Mon, 2 Jun Story publ. Element 23",
                "Mon, 2 Jun Issue NY Times published",
                "Wed, 15 Apr Event Element 15",
                "Wed, 15 Apr Deadline Element 15",
                "Thu, 16 Apr Story publ. Element 15",
                "Thu, 16 Apr Issue NY Times published",
                "Fri, 15 Apr Event Element 14",
                "Fri, 15 Apr Deadline Element 14",
                "Sat, 16 Apr Story publ. Element 14",
                "Sat, 16 Apr Issue NY Times published",
                "Wed, 1 Jan Topic End of Topic N3"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTimeline.openPage();
//        desktopTimelinePage.setURLControlPanelValues("allFormats=1&date=2014-04-04&dates_range=1&entries=5,4,3,2,1&fontSize=2&topic_id=3&without_date=1");
        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&date=2014-04-04&dates_range=1&entries=5,4,3,2,1&topic_id=3&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement_Step3),"Step#3: Elements are not equals");


//      Pure event
        String notExpectedElement[] = {
                "Fri, 4 Apr Event Element 26",
                "Fri, 4 Apr Deadline Element 26"};

//        desktopTimelinePage.setURLControlPanelValues("allFormats=1&date=2014-04-04&dates_range=1&entries=5,4,3,2,1&fontSize=2&topic_id=3&without_date=1");
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99144&date=2014-04-04&dates_range=1&entries=5,4,3,2,1&topic_id=3&without_date=1");

        softAssert.assertFalse(dn.dTimeline.isExtractionContainsElements(notExpectedElement),"Step 'Pure Event': Elements are not equals");

        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void testUserTest () throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");
        dn.dTimeline.openPage();
//
//        Step#4 Topics only
        String expectedElement_Step4[] = {
                "Sun, 1 Dec Topic Start of Topic N9",
                "Mon, 20 Apr Topic End of Topic N9"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=4&fontSize=2&topic_id=9&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement_Step4),"Step#4 'Topics only': Elements are not equals");

//        Step#5 Topic and Events entries
        String expectedElement_Step5[] = {
                "Sun, 1 Dec Topic Start of Topic N9",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Sun, 22 Dec 09:00 17:00 Event Element for extraction on Timeline",
                "Mon, 23 Dec 09:25 Event Element for extraction on Timeline",
                "Sat, 28 Dec 18:00 Event Element for extraction on Timeline",
                "Sun, 29 Dec Event Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Mon, 20 Apr Topic End of Topic N9"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=4,2&fontSize=2&topic_id=9&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement_Step5),"Step#5 'Topic and Events entries ': Elements are not equals");

//        Step#6 Issues only
        String expectedElement_Step6[] = {
                "Tue, 31 Dec Issue Reuters published",
                "Wed, 1 Jan Issue Manual (Manual schedule) published"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=5&fontSize=2&topic_id=9&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement_Step6),"Step#6 'Issues only': Elements are not equals");

//        Step#7 Issues + stories
        String expectedElement_Step7[] = {
                "Tue, 31 Dec 11:00 Story publ. Element for extraction on Timeline",
                "Tue, 31 Dec 12:00 Story publ. Element for extraction on Timeline",
                "Tue, 31 Dec Issue Reuters published",
                "Wed, 1 Jan Story publ. Element for extraction on Timeline",
                "Wed, 1 Jan Issue Manual (Manual schedule) published",
                "No date Story publ. Element for extraction on Timeline"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=5,1&fontSize=2&topic_id=9&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement_Step7),"Step#7 'Issues + stories': Elements are not equals");


//        Step#8 Deadlines only
        String expectedElement_Step8[] = {
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec Deadline Element for extraction on Timeline",
                "Sat, 28 Dec 19:00 Deadline Element for extraction on Timeline",
                "Mon, 30 Dec Deadline Element for extraction on Timeline",
                "Tue, 31 Dec 12:00 Deadline Element for extraction on Timeline",
                "No date Deadline Element for extraction on Timeline"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=3&fontSize=2&topic_id=9&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement_Step8),"Step#8 'Deadlines only': Elements are not equals");


//        Step#9 All entries
        String expectedElement_Step9[] = {
                "Sun, 1 Dec Topic Start of Topic N9",
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Fri, 20 Dec Deadline Element for extraction on Timeline",
                "Sun, 22 Dec 09:00 17:00 Event Element for extraction on Timeline",
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
                "Wed, 1 Jan Story publ. Element for extraction on Timeline",
                "Wed, 1 Jan Issue Manual (Manual schedule) published",
                "Mon, 20 Apr Topic End of Topic N9",
                "No date Story publ. Element for extraction on Timeline",
                "No date Deadline Element for extraction on Timeline"};

//        desktopTimelinePage.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=3,1,2,4,5&fontSize=2&topic_id=9&without_date=1");
        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&date=2019-12-05&dates_range=1&entries=3,1,2,4,5&topic_id=9&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement_Step9),"Step#9 'All entries': Elements are not equals");


//        Step#10 No date filter
        String expectedElement_Step10[] = {
                "Sun, 1 Dec Topic Start of Topic N9",
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Fri, 20 Dec Deadline Element for extraction on Timeline",
                "Sun, 22 Dec 09:00 17:00 Event Element for extraction on Timeline",
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
                "Wed, 1 Jan Story publ. Element for extraction on Timeline",
                "Wed, 1 Jan Issue Manual (Manual schedule) published",
                "Mon, 20 Apr Topic End of Topic N9"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=3,1,2,4,5&fontSize=2&topic_id=9&without_date=0");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement_Step10),"Step#10 'No date filter': Elements are not equals");

        softAssert.assertAll();
    }


    @Test(priority = 4)
    public void testPlatformFilter () throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");
        dn.dTimeline.openPage();


// === Platform filters: no matches ===
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=3,1,2,4,5&platforms=99010&topic_id=9&without_date=1");

        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_ENG, "Step 'Platform filters: no matches':Displayed and expected messages are not equal");


//      Platform filters: Manual platform
        String expectedElementManualPlatform[] = {
                "Sun, 1 Dec Topic Start of Topic N9",
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Fri, 20 Dec Deadline Element for extraction on Timeline",
                "Sun, 22 Dec 09:00 17:00 Event Element for extraction on Timeline",
                "Mon, 23 Dec 09:25 Event Element for extraction on Timeline",
                "Sat, 28 Dec 18:00 Event Element for extraction on Timeline",
                "Sat, 28 Dec 19:00 Deadline Element for extraction on Timeline",
                "Sun, 29 Dec Event Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Mon, 30 Dec Deadline Element for extraction on Timeline",
                "Tue, 31 Dec 12:00 Deadline Element for extraction on Timeline",
                "Wed, 1 Jan Story publ. Element for extraction on Timeline",
                "Wed, 1 Jan Issue Manual (Manual schedule) published",
                "Mon, 20 Apr Topic End of Topic N9",
                "No date Deadline Element for extraction on Timeline"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2019-12-05&dates_range=1&entries=3,1,2,4,5&platforms=99008&topic_id=9&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedElementManualPlatform),"Step 'Platform filters: Manual platform ': Elements are not equals");


//      Platform filters: categories with child
        String expectedElementСategoriesWithСhild[] = {
                "Sun, 1 Dec Topic Start of Topic N9",
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Fri, 20 Dec Deadline Element for extraction on Timeline",
                "Sun, 22 Dec 09:00 17:00 Event Element for extraction on Timeline",
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
                "Mon, 20 Apr Topic End of Topic N9",
                "No date Deadline Element for extraction on Timeline"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99179,99177,99178&date=2019-12-05&dates_range=1&entries=3,1,2,4,5&topic_id=9&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedElementСategoriesWithСhild),"Step 'Platform filters: categories with child': Elements are not equals");


//      Platform filters: first child category
        String expectedElementFirstChildCategory[] = {
                "Sun, 1 Dec Topic Start of Topic N9",
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Fri, 20 Dec Deadline Element for extraction on Timeline",
                "Sun, 22 Dec 09:00 17:00 Event Element for extraction on Timeline",
                "Mon, 23 Dec 09:25 Event Element for extraction on Timeline",
                "Sat, 28 Dec 18:00 Event Element for extraction on Timeline",
                "Sat, 28 Dec 19:00 Deadline Element for extraction on Timeline",
                "Sun, 29 Dec Event Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Mon, 30 Dec Deadline Element for extraction on Timeline",
                "Tue, 31 Dec 12:00 Story publ. Element for extraction on Timeline",
                "Tue, 31 Dec 12:00 Deadline Element for extraction on Timeline",
                "Tue, 31 Dec Issue Reuters published",
                "Mon, 20 Apr Topic End of Topic N9",
                "No date Deadline Element for extraction on Timeline"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99177&date=2019-12-05&dates_range=1&entries=3,1,2,4,5&topic_id=9&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedElementFirstChildCategory),"Step 'Platform filters: first child category': Elements are not equals");


//      Platform filters: second child category
        String expectedElementSecondChildCategory[] = {
                "Sun, 1 Dec Topic Start of Topic N9",
                "Thu, 19 Dec 11:15 Deadline Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Fri, 20 Dec Deadline Element for extraction on Timeline",
                "Sun, 22 Dec 09:00 17:00 Event Element for extraction on Timeline",
                "Mon, 23 Dec 09:25 Event Element for extraction on Timeline",
                "Sat, 28 Dec 18:00 Event Element for extraction on Timeline",
                "Sat, 28 Dec 19:00 Deadline Element for extraction on Timeline",
                "Sun, 29 Dec Event Element for extraction on Timeline",
                "Fri, 20 Dec 10:00 Mon, 30 Dec 20:00 Event Element for extraction on Timeline",
                "Mon, 30 Dec Deadline Element for extraction on Timeline",
                "Tue, 31 Dec 12:00 Deadline Element for extraction on Timeline",
                "Mon, 20 Apr Topic End of Topic N9",
                "No date Story publ. Element for extraction on Timeline"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99178&date=2019-12-05&dates_range=1&entries=3,1,2,4,5&topic_id=9&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedElementSecondChildCategory),"Step 'Platform filters: second child category': Elements are not equals");


        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void testPlatformFilterPureEvent () throws Exception{

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTimeline.openPage();


//      Pure event
        String notExpectedElement[] = {
                "Fri, 4 Apr Event Element 26",
                "Fri, 4 Apr Deadline Element 26"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99144&date=2014-04-04&dates_range=1&entries=5,4,3,2,1&topic_id=3&without_date=1");

        softAssert.assertFalse(dn.dTimeline.isExtractionContainsElements(notExpectedElement),"Step 'Pure Event': Elements are not equals");

        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() { }

}