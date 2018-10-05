package com.selenium.test.testng.tests.desktop;

import com.selenium.test.pages.desktop.DesktopLoginPage;
import com.selenium.test.pages.desktop.DesktopPublicationPlatformsPage;
import com.selenium.test.pages.desktop.DesktopSettingsPage;
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
import java.util.List;

/**
 * Created by a.maletsky
 * Uses TestNG test framework
 * Tests uses work with Page Object Pattern (https://code.google.com/p/selenium/wiki/PageObjects)
 */
@Listeners({ScreenShotOnFailListener.class})
public class DesktopTimelinePageNoContentHintTest extends BaseTest {


    @BeforeTest
    public void beforeTest() { }


//    TODO: change user wo availability to page
    @Test(priority = 1)
    public void testTimelinePageAvailability() throws Exception {
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("new", "tomcattom");
        dn.dTimeline.openPage();
        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void testNoContentHint_EN () throws Exception {
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ed1", "tomcattom");
        dn.dTimeline.openPage();
        //Platform mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99151,99075,99076&date=2017-01-23&entries=5,4,3,2,1&fontSize=2&issueDate=2017-01-23&object_id=99003&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_ENG, "Displayed and expected messages are not equal");
        //Topic mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2017-01-23&dates_range=4&entries=5,4,3,2,1&fontSize=2&topic_id=8&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_ENG, "Displayed and expected messages are not equal");
        //Assignments mode: by deadline
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&categories=99151,99075,99076&date=2017-01-23&fontSize=2&formats=17&statuses=0&time_range=4");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_ENG, "Displayed and expected messages are not equal");
        //Assignments mode: by last modification date
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&categories=99151,99075,99076&date=2017-01-23&fontSize=2&formats=17&statuses=0");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_ENG, "Displayed and expected messages are not equal");

        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void testNoContentHint_DE() throws Exception {
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("de1", "tomcattom");
        dn.dTimeline.openPage();
        //Platform mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99151,99075,99076&date=2017-01-23&entries=5,4,3,2,1&issueDate=2017-01-23&object_id=99003&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_DE, "Displayed and expected messages are not equal");
        //Topic mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2017-01-23&dates_range=4&entries=5,4,3,2,1&topic_id=8&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_DE, "Displayed and expected messages are not equal");
        //Assignments mode: by deadline
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&categories=99151,99075,99076&date=2017-01-23&formats=17&statuses=0&time_range=4");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_DE, "Displayed and expected messages are not equal");
        //Assignments mode: by last modification date
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&categories=99151,99075,99076&date=2017-01-23&formats=17&statuses=0");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_DE, "Displayed and expected messages are not equal");

        softAssert.assertAll();
    }


    @Test(priority = 4)
    public void testNoContentHint_ES() throws Exception{
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.ES);
        dn.dTimeline.openPage();
        //Platform mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99183&date=2017-01-23&entries=1,2,3,4,5&issueDate=2017-01-23&object_id=99009&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_ES, "Displayed and expected messages are not equal");
        //Topic mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2017-01-23&dates_range=4&entries=5,4,3,2,1&topic_id=8&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_ES, "Displayed and expected messages are not equal");
        //Assignments mode: by deadline
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2017-01-23&formats=61&platforms=99009&statuses=0&time_range=4");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_ES, "Displayed and expected messages are not equal");
        //Assignments mode: by last modification date
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2017-01-23&formats=61&platforms=99009&statuses=0");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_ES, "Displayed and expected messages are not equal");

        // Back to default
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.EN_UK);

        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void testNoContentHint_FR() throws Exception {
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.FR);
        dn.dTimeline.openPage();
        //Platform mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99183&date=2017-01-23&entries=1,2,3,4,5&issueDate=2017-01-23&object_id=99009&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_FR, "Displayed and expected messages are not equal");
        //Topic mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2017-01-23&dates_range=4&entries=5,4,3,2,1&topic_id=8&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_FR, "Displayed and expected messages are not equal");
        //Assignments mode: by deadline
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2017-01-23&formats=61&platforms=99009&statuses=0&time_range=4");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_FR, "Displayed and expected messages are not equal");
        //Assignments mode: by last modification date
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2017-01-23&formats=61&platforms=99009&statuses=0");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_FR, "Displayed and expected messages are not equal");

        // Back to default
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.EN_UK);

        softAssert.assertAll();
    }

    @Test(priority = 6)
//    CWAECG-9934: Timeline page, automation testing: Issue with "page-is-fully-loaded" indicator which is not triggered in "true"
    public void testNoContentHintWithoutGroup_EN() throws Exception {
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ext", "tomcattom");
        dn.dTimeline.openPage();
        //Platform mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99151,99075,99076&date=2017-01-23&entries=5,4,3,2,1&fontSize=2&issueDate=2017-01-23&object_id=99003&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_WO_GROUP_ENG, "Displayed and expected messages are not equal");
        //Topic mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2017-01-23&dates_range=4&entries=5,4,3,2,1&fontSize=2&topic_id=8&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_WO_GROUP_ENG, "Displayed and expected messages are not equal");
        //Assignments mode: by deadline
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&categories=99151,99075,99076&date=2017-01-23&fontSize=2&formats=17&statuses=0&time_range=4");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_WO_GROUP_ENG, "Displayed and expected messages are not equal");
        //Assignments mode: by last modification date
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&categories=99151,99075,99076&date=2017-01-23&fontSize=2&formats=17&statuses=0");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_WO_GROUP_ENG, "Displayed and expected messages are not equal");

        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void testNoContentHintWithoutGroup_DE() throws Exception {
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ext", "tomcattom");
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.DE);
        dn.dTimeline.openPage();
        //Platform mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99183&date=2017-01-23&entries=1,2,3,4,5&issueDate=2017-01-23&object_id=99009&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_WO_GROUP_DE, "Displayed and expected messages are not equal");
        //Topic mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2017-01-23&dates_range=4&entries=5,4,3,2,1&topic_id=8&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_WO_GROUP_DE, "Displayed and expected messages are not equal");
        //Assignments mode: by deadline
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2017-01-23&formats=61&platforms=99009&statuses=0&time_range=4");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_WO_GROUP_DE, "Displayed and expected messages are not equal");
        //Assignments mode: by last modification date
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2017-01-23&formats=61&platforms=99009&statuses=0");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_WO_GROUP_DE, "Displayed and expected messages are not equal");

        // Back to default
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.EN_UK);

        softAssert.assertAll();
    }

    @Test(priority = 8)
    public void testNoContentHintWithoutGroup_FR() throws Exception{
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ext", "tomcattom");
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.FR);
        dn.dTimeline.openPage();
        //Platform mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99183&date=2017-01-23&entries=1,2,3,4,5&issueDate=2017-01-23&object_id=99009&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_WO_GROUP_FR, "Displayed and expected messages are not equal");
        //Topic mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2017-01-23&dates_range=4&entries=5,4,3,2,1&topic_id=8&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_WO_GROUP_FR, "Displayed and expected messages are not equal");
        //Assignments mode: by deadline
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2017-01-23&formats=61&platforms=99009&statuses=0&time_range=4");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_WO_GROUP_FR, "Displayed and expected messages are not equal");
        //Assignments mode: by last modification date
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2017-01-23&formats=61&platforms=99009&statuses=0");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_WO_GROUP_FR, "Displayed and expected messages are not equal");

        // Back to default
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.EN_UK);

        softAssert.assertAll();
    }

    @Test(priority = 9)
    public void testNoContentHintWithoutGroup_ES() throws Exception{
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ext", "tomcattom");
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.ES);
        dn.dTimeline.openPage();
        //Platform mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&categories=99183&date=2017-01-23&entries=1,2,3,4,5&issueDate=2017-01-23&object_id=99009&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_WO_GROUP_ES, "Displayed and expected messages are not equal");
        //Topic mode
        dn.dTimeline.setURLControlPanelValues("allFormats=1&date=2017-01-23&dates_range=4&entries=5,4,3,2,1&topic_id=8&without_date=1");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_WO_GROUP_ES, "Displayed and expected messages are not equal");
        //Assignments mode: by deadline
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=0&date=2017-01-23&formats=61&platforms=99009&statuses=0&time_range=4");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_WO_GROUP_ES, "Displayed and expected messages are not equal");
        //Assignments mode: by last modification date
        dn.dTimeline.setURLControlPanelValues("assignedType=0&assignments_mode_sorting=1&date=2017-01-23&formats=61&platforms=99009&statuses=0");
        softAssert.assertEquals(dn.dTimeline.getNoContentText(), DesktopTimelinePage.MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_WO_GROUP_ES, "Displayed and expected messages are not equal");

        // Back to default
        dn.dSettings.openPage();
        dn.dSettings.setLanguage(DesktopSettingsPage.Locales.EN_UK);

        softAssert.assertAll();
    }

    @AfterTest
    public void afterTest() { }

}