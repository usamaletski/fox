package com.selenium.test.testng.tests.desktop;

import com.selenium.test.pages.desktop.DesktopLoginPage;
import com.selenium.test.pages.desktop.DesktopTimelinePage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import com.selenium.test.webtestsbase.WebDriverFactory;
//import org.apache.xpath.operations.String;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by a.maletsky
 * Uses TestNG test framework
 * Tests uses work with Page Object Pattern (https://code.google.com/p/selenium/wiki/PageObjects)
 */
@Listeners({ScreenShotOnFailListener.class})
public class DesktopTimelinePageExtractionAssignmentsByDeadlineTest extends BaseTest {


    @BeforeTest
    public void beforeTest() {
    }


//======================================  Timeline page - Extraction (assignments by modification) =========================================


    @Test(priority = 1)
    public void testExtractionTest() throws Exception {


//        Step#2
        String expectedElements_Step2[] = {
                "Mon, 1 Feb Assignments_extraction_PO_02"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");
        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-01&fontSize=2&statuses=5&time_range=4&timelineAssignmentsDate=2016-02-01");
//        desktopTimelinePage.getElementsValues();
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step2), "Step#2: Elements and order equals");
//        softAssert.assertTrue(desktopTimelinePage.isExtractionContainsElements(expectedElements),"Extraction contains elements");

//        Step#3
        String expectedElements_Step3[] = {
                "Mon, 1 Feb Assignments_extraction_PO_01",
                "Mon, 1 Feb Assignments_extraction_PO_02"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-01&fontSize=2&statuses=6&time_range=4&timelineAssignmentsDate=2016-02-01");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step3), "Step#3: Elements and order equals");

//        Step#4
        String expectedElements_Step4[] = {
                "Mon, 1 Feb Assignments_extraction_PO_01"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-01&fontSize=2&platforms=99007&statuses=6&time_range=4&timelineAssignmentsDate=2016-02-01");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step4), "Step#4: Elements and order equals");


//        Step#5
        String expectedElements_Step5[] = {
                "Mon, 1 Feb Assignments_extraction_PO_02"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2016-02-01&fontSize=2&formats=2&platforms=99010&statuses=6&time_range=4&timelineAssignmentsDate=2016-02-01");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step5), "Step#5: Elements and order equals");


//        Step#6
        String expectedElements_Step6[] = {
                "Tue, 2 Feb Assignments_extraction_CROSS"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2016-02-01&fontSize=2&formats=6&platforms=99010&statuses=4&time_range=5&timelineAssignmentsDate=2016-02-01");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step6), "Step#6: Elements and order equals");

        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void testExtractionCross() throws Exception {

//        Step#7
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("cross", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2017-01-26&formats=6&time_range=4&timelineAssignmentsDate=2016-02-03");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_ENG, "Step#7: Displayed and expected messages are not equal");


//        Step#8
        String expectedElements_Step8[] = {
                "Tue, 2 Feb Assignments_extraction_CROSS"};

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-03&formats=6&time_range=6&timelineAssignmentsDate=2016-02-03");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step8), "Step#8: Elements and order equals");


//        Step#9
        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-03&formats=6&statuses=6&time_range=6&timelineAssignmentsDate=2016-02-03");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_ENG, "Step#9: Displayed and expected messages are not equal");


//        Step#10
        String expectedElements_Step10[] = {
                "Tue, 2 Feb Assignments_extraction_CROSS"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2016-02-03&formats=6&platforms=99002,99010&statuses=6,4&time_range=6&timelineAssignmentsDate=2016-02-03");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step10), "Step#10: Elements and order equals");


//        Step#11
        String expectedElements_Step11[] = {
                "Tue, 2 Feb Assignments_extraction_Main_01"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2016-02-03&formats=2,4&platforms=99002,99010&statuses=6,4&time_range=6&timelineAssignmentsDate=2016-02-03");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step11), "Step#11: Elements and order equals");

//        Step#12
        String expectedElements_Step12[] = {
                "Tue, 2 Feb Assignments_extraction_Main_01",
                "Mon, 1 Feb Assignments_extraction_PO_01",
                "Mon, 1 Feb Assignments_extraction_PO_02"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2016-02-03&formats=2,4&platforms=99002,99010&statuses=6,4&time_range=15&timelineAssignmentsDate=2016-02-03");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step12), "Step#12: Elements and order equals");


//        Step#13
        String expectedElements_Step13[] = {
                "Tue, 2 Feb 22:00 Assignments_extraction_Main_01"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2016-02-03&formats=1&platforms=99003&time_range=6&timelineAssignmentsDate=2016-02-03");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step13), "Step#13: Elements and order equals");

        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void testExtractionCrossPAA() throws Exception {

//        Step#20
        String expectedElements_Step20[] = {
                "Tue, Feb 2 11:00 AM Assignments_extraction_Main_01"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("usa", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-02&statuses=1&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step20), "Step#20: Elements and order equals");

//        Step#21
        String expectedElements_Step21[] = {
                "Mon, Feb 1 Assignments_extraction_PO_01"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-02&statuses=1&time_range=6&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step21), "Step#21: Elements and order equals");


//        Step#22
        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-02&statuses=1&time_range=6&timelineAssignmentsDate=2030-01-05");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_ENG, "Step#22_01: Displayed and expected messages are not equal");

        String expectedElements_Step22[] = {
                "Sat, Jan 5 Scope 15 (Hidden publication)"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2030-01-05&statuses=1&time_range=4&timelineAssignmentsDate=2030-01-05\n");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step22), "Step#22_02: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testExtractionEd1() throws Exception {

//        Step#23
        String expectedElements_Step23[] = {
                "Tue, 2 Feb Assignments_extraction_Main_01",
                "Tue, 2 Feb Assignments_extraction_Main_01"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ed1", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allFormats=1&assignedType=0&assignments_mode_sorting=0&categories=99063&date=2017-01-26&fontSize=2&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step23), "Step#23: Elements and order equals");

        softAssert.assertAll();
    }


    @Test(priority = 5)
    public void testExtractionRead1() throws Exception {

//        Step#24
        String expectedElements_Step24[] = {
                "Tue, 2 Feb 21:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb Assignments_extraction_Main_02",
                "Tue, 2 Feb Assignments_extraction_Main_02"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("read1", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2017-01-26&formats=18&statuses=0,3,4&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step24), "Step#24: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void testExtractionOrg1() throws Exception {

//        Step#25
        String expectedElements_Step25[] = {
                "Tue, 2 Feb Assignments_extraction_Main_02"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("assignedType=1&assignments_mode_sorting=0&date=2017-01-26&fontSize=2&formats=18,6&platforms=99002&statuses=0&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step25), "Step#25: Elements and order equals");


//        Step#26
        String expectedElements_Step26[] = {
                "Tue, 2 Feb 21:00 Assignments_extraction_Main_01"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=2&assignments_mode_sorting=0&date=2016-02-02&fontSize=2&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step26), "Step#26: Elements and order equals");


//        Step#27
        String notExpectedElements_Step27[] = {
                "Assignments_extraction_W/O_task",
                "Assignments_extraction_PURE_event",
                "Assignments_extraction_PURE_task",
                "Assignments_extraction_repeat_event",
                "Assignments_extraction_repeat_event_OCCURRENCE_ONE",
                "Assignments_extraction_CONFIDENTIONAL"};


        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-02&fontSize=2&time_range=16&timelineAssignmentsDate=2016-01-20");
        softAssert.assertFalse(dn.dTimeline.isExtractionContainsTitles(notExpectedElements_Step27), "Step#27: Extraction does not contains elements");

        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void testExtractionConfidentialMus() throws Exception {

//        Step#28
        String expectedElements_Step28[] = {
                "Tue, 2 Feb Assignments_extraction_CONFIDENTIONAL"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("mus", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2017-01-26&fontSize=2&formats=18&statuses=1&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step28), "Step#28: Elements equals");

        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void testExtractionConfidentialOrg1() throws Exception {

//        Step#28_Additional
        String unExpectedElements_Step28[] = {
                "Assignments_extraction_CONFIDENTIONAL"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2016-02-02&statuses=1&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertFalse(dn.dTimeline.isExtractionContainsTitles(unExpectedElements_Step28), "Step#28_Additional: Unexpected element is represented");

        softAssert.assertAll();
    }

    @Test(priority = 8)
    public void testExtractionScope() throws Exception {

//        Step#29
        String expectedElements_Step29[] = {
                "Tue, 2 Feb Assignments_extraction_SCOPE"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org3", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2017-01-26&fontSize=2&time_range=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedElements_Step29), "Step#29: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 9)
    public void testExtractionTimeRangeThisWeek() throws Exception {

//        Step#30
        String expectedElements_Step30[] = {
                "Sun, 3 Jan Assignments_extraction_30",
                "Sun, 3 Jan Assignments_extraction_31"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("cross", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=0&date=2017-01-27&time_range=7&timelineAssignmentsDate=2016-01-01");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedElements_Step30), "Step#30: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 11)
    public void testExtractionDefinitionDeadline() throws Exception {

//        Step#42
        Map<String, String> elementsExpectation = new HashMap<String, String>() {{
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-04",
                    "Thu, 4 May Deadline definition 01");
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-05",
                    "Fri, 5 May Deadline definition 02");
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-11",
                    "Thu, 11 May 15:00 Deadline definition 03");
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-12",
                    "Fri, 12 May Deadline definition 04");
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-18",
                    "Thu, 18 May Deadline definition 05");
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-19",
                    "Fri, 19 May 15:00 Deadline definition 06");
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-24",
                    "Wed, 24 May Deadline definition 07");
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-25",
                    "Thu, 25 May 16:00 Deadline definition 07");
            put("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-27",
                    "Sat, 27 May 15:00 Deadline definition 08"); //CWAECG-10413: Timeline page, Monthly Availability page: Issue with incorrect deadline definition logic for element with hidden publication
        }};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTimeline.openPage();
        for (Map.Entry<String, String> entry : elementsExpectation.entrySet()) {
            dn.dTimeline.setURLControlPanelValues(entry.getKey());
            softAssert.assertTrue(dn.dTimeline.isElementsEquals(new String[]{entry.getValue()}), "Element are not equals");
        }

        softAssert.assertAll();
                }

@Test(priority = 12)
public void testExtractionDefinitionDeadlineCross() throws Exception {
        String expectedElement[] = {
        "Sat, 27 May 15:00  Deadline definition 08"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("cross", "tomcattom");
        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&assignedType=0&assignments_mode_sorting=0&date=2017-04-17&time_range=4&timelineAssignmentsDate=2017-05-27");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElement), "Element are not equals");

        softAssert.assertAll();


        }

@AfterTest
public void afterTest() {
        }

        }