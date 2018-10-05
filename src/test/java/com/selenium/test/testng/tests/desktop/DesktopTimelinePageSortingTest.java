package com.selenium.test.testng.tests.desktop;

import com.selenium.test.pages.desktop.DesktopLoginPage;
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
public class DesktopTimelinePageSortingTest extends BaseTest {


    @BeforeTest
    public void beforeTest() { }


//====================================== Timeline - Sorting =========================================


    @Test(priority = 1)
    public void testSortingPlatformMode () throws Exception{


//        Step#2
        String expectedElements_Step2[] = {
                "Sun, 25 May 12:00 Story publ. Extraction A",
                "Sun, 25 May 12:00 Story publ. Extraction B",
                "Sun, 25 May 12:00 12:00 Event Extraction A",
                "Sun, 25 May 12:00 12:00 Event Extraction B",
                "Sun, 25 May 12:00 Deadline Extraction A",
                "Sun, 25 May 12:00 Deadline Extraction B",
                "Sun, 25 May Issue Associated Press (Sunday) published",
                "No date Deadline Extraction A",
                "No date Deadline Extraction B"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("cross", "tomcattom");
        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allCategories=1&allFormats=1 &date=2014-05-25&entries=5,4,3,2,1&issueDate=2014-05-25&object_id=99005&without_date=1");
//        desktopTimelinePage.getElementsValues();
        softAssert.assertTrue(dn.dTimeline.isElementsOrderEquals(expectedElements_Step2), "Step#2: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testSortingAssignmentsByModificationDate() throws Exception{

//        Step#8
        String expectedElements_Step8[] = {
                "Tue, 2 Feb 13:00 Assignments_extraction_test_MODIFICATION_change_title",
                "Tue, 2 Feb 13:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 12:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 10:00 Assignments_extraction_Main_02",
                "Tue, 2 Feb 09:00 Assignments_extraction_Main_02",
                "Tue, 2 Feb 09:00 Assignments_extraction_Main_02",
                "Mon, 1 Feb 11:35 Assignments_extraction_stand_feature_OCCURRENCE_ONE\n" +
                        "Line\n" +
                        "\n" +
                        "break"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2016-02-02&fontSize=2&formats=18,1&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsOrderEquals(expectedElements_Step8), "Step#8: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void testSortingAssignmentsByDeadline() throws Exception{

//        Step#8
        String expectedElements_Step8[] = {
                "Tue, 2 Feb 18:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 21:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 22:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb Assignments_extraction_CROSS",
                "Tue, 2 Feb Assignments_extraction_Main_01",
                "Tue, 2 Feb Assignments_extraction_Main_01",
                "Tue, 2 Feb Assignments_extraction_Main_02",
                "Tue, 2 Feb Assignments_extraction_Main_02",
                "Tue, 2 Feb Assignments_extraction_Main_02"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2014-05-25&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsOrderEquals(expectedElements_Step8), "Step#8: Elements and order equals");

//        Step#9
        String expectedElements_Step9[] = {
                "Mon, 1 Feb Assignments_extraction_3",
                "Mon, 1 Feb Assignments_extraction_4",
                "Mon, 1 Feb Assignments_extraction_test_MODIFICATION_change_title",
                "Tue, 2 Feb 18:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 21:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 22:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb Assignments_extraction_CROSS",
                "Tue, 2 Feb Assignments_extraction_Main_01",
                "Tue, 2 Feb Assignments_extraction_Main_01",
                "Tue, 2 Feb Assignments_extraction_Main_02",
                "Tue, 2 Feb Assignments_extraction_Main_02",
                "Tue, 2 Feb Assignments_extraction_Main_02"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-02&time_range=7&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsOrderEquals(expectedElements_Step9), "Step#9: Elements and order equals");


//        Step#10
        String expectedElements_Step10[] = {
                "Mon, 1 Feb Assignments_extraction_3",
                "Mon, 1 Feb Assignments_extraction_4",
                "Mon, 1 Feb Assignments_extraction_test_MODIFICATION_change_title",
                "Sun, 3 Jan Assignments_extraction_30",
                "Sun, 3 Jan Assignments_extraction_31"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-02&time_range=15&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsOrderEquals(expectedElements_Step10), "Step#10: Elements and order equals");

        softAssert.assertAll();
    }


    @Test(priority = 4)
    public void testPrerequisitesTopic () throws Exception{
//        Step 2
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");
        dn.dTopic.openPage();
        dn.dTopic.setTopicDates("Topic N9","01.12.2019","20.04.2020");

//        TODO: Assertion for set values
//        TODO: LogOut and split with link steps (see manual test)
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void testSortingTopic () throws Exception{
//        Step#3

        String expectedElement_Step3[] = {
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

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");
        dn.dTimeline.openPage();
//        desktopTimelinePage.setURLControlPanelValues("allFormats=1&date=2014-04-04&dates_range=1&entries=5,4,3,2,1&fontSize=2&topic_id=3&without_date=1");
        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2019-12-05&dates_range=1&entries=5,4,3,2,1&topic_id=9&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isElementsOrderEquals(expectedElement_Step3),"Step#3: Elements are not equals");
        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() { }

}