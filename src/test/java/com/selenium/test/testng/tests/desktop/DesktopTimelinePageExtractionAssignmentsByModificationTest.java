package com.selenium.test.testng.tests.desktop;

import com.selenium.test.pages.desktop.DesktopLoginPage;
import com.selenium.test.pages.desktop.DesktopTimelinePage;
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
public class DesktopTimelinePageExtractionAssignmentsByModificationTest extends BaseTest {


    @BeforeTest
    public void beforeTest() { }


//======================================  Timeline page - Extraction (assignments by modification) =========================================


    @Test(priority = 1)
    public void testExtractionTest() throws Exception{


//        Step#2
        String expectedElements_Step2[] = {
                "Mon, 1 Feb 15:00 Assignments_extraction_PO_01",
                "Mon, 1 Feb 12:00 Assignments_extraction_PO_02"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("test", "tomcattom");
        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2017-01-26&fontSize=2&statuses=5&timelineAssignmentsDate=2016-02-02");
//        desktopTimelinePage.getElementsValues();
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step2), "Step#2: Elements and order equals");
//        softAssert.assertTrue(desktopTimelinePage.isExtractionContainsElements(expectedElements),"Extraction contains elements");

//        Step#3
        String expectedElements_Step3[] = {
                "Mon, 1 Feb 12:00 Assignments_extraction_PO_02"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&assignedType=0&assignments_mode_sorting=1&date=2016-02-02&fontSize=2&platforms=99007&statuses=5&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step3), "Step#3: Elements and order equals");

//        Step#4
        String expectedElements_Step4[] = {
                "Mon, 1 Feb 16:00 Assignments_extraction_PO_01"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&assignedType=0&assignments_mode_sorting=1&date=2016-02-02&fontSize=2&platforms=99007&statuses=6&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step4), "Step#4: Elements and order equals");


//        Step#6
        String expectedElements_Step6[] = {
                "Mon, 1 Feb 13:00 Assignments_extraction_PO_02"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2016-02-02&fontSize=2&formats=2&platforms=99010&statuses=6&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step6), "Step#6: Elements and order equals");


//        Step#7
        String expectedElements_Step7[] = {
                "Mon, 1 Feb 12:35 Assignments_extraction_CROSS"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2016-02-02&fontSize=2&formats=6&platforms=99010&statuses=4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step7), "Step#7: Elements and order equals");

        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void testExtractionCross() throws Exception {

//        Step#8
        String expectedElements_Step8[] = {
                "Mon, 1 Feb 12:35 Assignments_extraction_CROSS"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("cross", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2017-01-26&formats=6&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step8), "Step#8: Elements and order equals");


//        Step#9
        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2016-02-02&formats=6&statuses=6&timelineAssignmentsDate=2016-02-02");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_ENG, "Step#9: Displayed and expected messages are not equal");


//        Step#10
        String expectedElements_Step10[] = {
                "Mon, 1 Feb 12:35 Assignments_extraction_CROSS"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2016-02-02&formats=6&platforms=99002,99010&statuses=6,4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step10), "Step#10: Elements and order equals");


//        Step#11
        String expectedElements_Step11[] = {
                "Tue, 2 Feb 16:00 Assignments_extraction_Main_01",
                "Mon, 1 Feb 16:00 Assignments_extraction_PO_01",
                "Mon, 1 Feb 13:00 Assignments_extraction_PO_02"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2016-02-02&formats=2,4&platforms=99002,99010&statuses=6,4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step11), "Step#11: Elements and order equals");

        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void testExtractionCrossPAA() throws Exception {

//        Step#14
        String expectedElements_Step14[] = {
                "Tue, Feb 2 02:00 AM Assignments_extraction_Main_01",
                "Mon, Feb 1 03:00 AM Assignments_extraction_PO_01",
                "Mon, Jan 25 10:31 PM Scope 15 (Hidden publication)"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("usa", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2017-01-26&statuses=1&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step14), "Step#14: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testExtractionEd1() throws Exception{

//        Step#15
        String expectedElements_Step15[] = {
                "Tue, 2 Feb 16:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 15:00 Assignments_extraction_Main_01"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ed1", "tomcattom");
        DesktopTimelinePage desktopTimelinePage = new DesktopTimelinePage();

        desktopTimelinePage.setURLControlPanelValues("allFormats=1&assignedType=0&assignments_mode_sorting=1&categories=99063&date=2017-01-26&fontSize=2&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(desktopTimelinePage.isElementsEquals(expectedElements_Step15), "Step#15: Elements and order equals");

        softAssert.assertAll();
    }


    @Test(priority = 5)
    public void testExtractionRead1() throws Exception{

//        Step#16
        String expectedElements_Step16[] = {
                "Tue, 2 Feb 12:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 09:00 Assignments_extraction_Main_02",
                "Tue, 2 Feb 09:00 Assignments_extraction_Main_02"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("read1", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2017-01-26&formats=18&statuses=0,3,4&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step16), "Step#16: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void testExtractionOrg1() throws Exception{

//        Step#17
        String expectedElements_Step17[] = {
                "Tue, 2 Feb 09:00 Assignments_extraction_Main_02"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("\n" +
                "assignedType=1&assignments_mode_sorting=1&date=2017-01-26&formats=18,6&platforms=99002&statuses=0&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step17), "Step#17: Elements and order equals");


//        Step#18
        String expectedElements_Step18[] = {
                "Tue, 2 Feb 12:00 Assignments_extraction_Main_01"};

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=2&assignments_mode_sorting=1&date=2017-01-26&timelineAssignmentsDate=2016-02-02&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step18), "Step#18: Elements and order equals");


//        Step#19
        String expectedElements_Step19[] = {
                "Tue, 2 Feb 13:00 Assignments_extraction_test_MODIFICATION_change_title",
                "Tue, 2 Feb 13:00 Assignments_extraction_Main_01",
                "Tue, 2 Feb 10:00 Assignments_extraction_Main_02"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&categories=99155&date=2016-02-02&fontSize=2&formats=1&platforms=99002&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step19), "Step#19: Elements and order equals");

//        Step#20
        String expectedElements_Step20[] = {
                "Mon, 1 Feb 11:35 Assignments_extraction_stand_feature_OCCURRENCE_ONE\n" +
                        "Line\n" +
                        "\n" +
                        "break"};

        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2016-02-02&fontSize=2&formats=18&platforms=99002&statuses=1&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step20), "Step#20: Elements and order equals");

//        Step#21
        String expectedElements_Step21[] = {
                "Tue, 2 Feb 16:00 Assignments_extraction_Main_01",
                "Mon, 4 Jan 11:00 Assignments_extraction_30"};

        String notExpectedElements_Step21[] = {
                "Assignments_extraction_W/O_task",
                "Assignments_extraction_PURE_event",
                "Assignments_extraction_PURE_task",
                "Assignments_extraction_31",
                "Assignments_extraction_stand_feature",
                "Assignments_extraction_repeat_event",
                "Assignments_extraction_repeat_event_OCCURRENCE_ONE",
                "Assignments_extraction_CONFIDENTIONAL"};


        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2016-02-02&fontSize=2&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedElements_Step21), "Step#21: Extraction contains elements");
        softAssert.assertFalse(dn.dTimeline.isExtractionContainsTitles(notExpectedElements_Step21), "Step#21: Extraction does not contains elements");

        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void testExtractionConfidential() throws Exception{

//        Step#22
        String expectedElements_Step22[] = {
                "Mon, 1 Feb 18:00 Assignments_extraction_CONFIDENTIONAL"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("mus", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2017-01-26&fontSize=2&formats=18&statuses=1&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isElementsEquals(expectedElements_Step22), "Step#22: Elements and order equals");

        softAssert.assertAll();
    }

    @Test(priority = 8)
    public void testExtractionScope() throws Exception{

//        Step#23
        String expectedElements_Step23[] = {
                "Sun, 31 Jan 11:00 Assignments_extraction_SCOPE"};

        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org3", "tomcattom");
        dn.dTimeline.openPage();

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&assignedType=0&assignments_mode_sorting=1&date=2017-01-26&timelineAssignmentsDate=2016-02-02");
        softAssert.assertTrue(dn.dTimeline.isExtractionContainsElements(expectedElements_Step23), "Step#23: Elements and order equals");

        softAssert.assertAll();
    }




    @AfterTest
    public void afterTest() { }

}