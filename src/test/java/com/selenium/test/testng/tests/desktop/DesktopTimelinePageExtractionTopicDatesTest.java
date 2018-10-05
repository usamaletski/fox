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
public class DesktopTimelinePageExtractionTopicDatesTest extends BaseTest {


    @BeforeTest
    public void beforeTest() {
    }


    //================================  07. Timeline page - Extraction (topic) - Dates =====================================
    @Test(priority = 1)
    public void testPrerequisites() throws Exception{

        //        Step#2
        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("org1", "tomcattom");
        dn.dTopic.openPage();
        dn.dTopic.checkAllCheckboxesGroupFilter ();
        dn.dTopic.setTopicDates("Topic N3", "01.03.2014", "20.04.2016");

//        TODO: Assertion for set values

//        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testEd1() throws Exception{

        //        Step#2
        String expectedElement_Step2[] = {
                "Element 13",
                "Element 13",
                "Element 25",
                "Element 25",
                "Start of Topic N3",
                "Element 20",
                "Element 20",
                "Element 24",
                "Element 24",
                "Element 18",
                "Element 18",
                "Element 16",
                "Element 16",
                "Element 26",
                "Element 26",
                "Element 19",
                "Element 19",
                "Element 21",
                "Element 21",
                "Element 22",
                "Element 22",
                "Element 23",
                "Element 23",
                "Element 15",
                "Element 15",
                "Element 14",
                "Element 14",
                "End of Topic N3"
        };


        dn.dLoginPage.openPage();
        dn.dLoginPage.doLoginAs("ed1", "tomcattom");
        dn.dTimeline.openPage();
        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=1&entries=4,3,2&formats=18,6,2,3,4,11,12,5,7,8,13,10,14,15,16,17,0&topic_id=3&without_date=1");

        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step2), "Step#2: Titles are not equals");


        //        Step#3
        String expectedElement_Step3[] = {
                "Start of Topic N3",
                "Element 17",
                "Element 17",
                "End of Topic N3"
        };

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=1&entries=4,3,2&formats=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step3), "Step#2: Titles are not equals");

        //        Step#4
        String expectedElement_Step4[] = {
                "Element 16",
                "Element 16",
                "Element 26",
                "Element 26",
                "Element 17",
                "Element 17",
                "Element 19",
                "Element 19",
                "Element 21",
                "Element 21",
                "Element 22",
                "Element 22",
                "Element 23",
                "Element 23",
                "Element 15",
                "Element 15",
                "Element 14",
                "Element 14",
                "End of Topic N3"
        };

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=2&entries=4,3,2&formats=18,1,6,2,3,4,11,12,5,7,8,13,10,14,15,16,17,0&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step4), "Step#4: Titles are not equals");

        //        Step#5
        String expectedElement_Step5[] = {
                "Element 13",
                "Element 13",
                "Element 25",
                "Element 25",
                "Start of Topic N3",
                "Element 20",
                "Element 20",
                "Element 24",
                "Element 24",
                "Element 18",
                "Element 18"
        };

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=3&entries=4,3,2&formats=18,6,2,3,4,11,12,5,7,8,13,10,14,15,16,17,0&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step5), "Step#5: Titles are not equals");

        //        Step#6
        String expectedElement_Step6_today[] = {
                "Element 16",
                "Element 16",
                "Element 26",
                "Element 26"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=4&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step6_today), "Step#6 today: today: Titles are not equals");

        String expectedElement_Step6_tomorrow[] = {
                "Element 17",
                "Element 17"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=5&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step6_tomorrow), "Step#6 tomorrow: today: Titles are not equals");

        String expectedElement_Step6_yesterday[] = {
                "Element 18",
                "Element 18"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=6&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step6_yesterday), "Step#6 yesterday: yesterday: Titles are not equals");

        String expectedElement_Step7_this_week[] = {
                "Element 24",
                "Element 24",
                "Element 18",
                "Element 18",
                "Element 16",
                "Element 16",
                "Element 26",
                "Element 26",
                "Element 17",
                "Element 17",
                "Element 19",
                "Element 19"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=7&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step7_this_week), "Step#7 this week: Titles are not equals");

        String expectedElement_Step8_next_week[] = {
                "Element 21",
                "Element 21"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=8&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step8_next_week), "Step#8 next_week: Titles are not equals");

        String expectedElement_Step8_last_week[] = {
                "Element 20",
                "Element 20"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=9&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step8_last_week), "Step#8 last_week: Titles are not equals");

        String expectedElement_Step9_this_month[] = {
                "Element 18",
                "Element 18",
                "Element 16",
                "Element 16",
                "Element 26",
                "Element 26",
                "Element 17",
                "Element 17",
                "Element 19",
                "Element 19",
                "Element 21",
                "Element 21"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=10&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step9_this_month), "Step#9 this_month: Titles are not equals");

        String expectedElement_Step10_next_month[] = {
                "Element 22",
                "Element 22",
                "Element 23",
                "Element 23"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=11&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step10_next_month), "Step#10 next_month: Titles are not equals");

        String expectedElement_Step10_last_month[] = {
                "Element 25",
                "Element 20",
                "Element 24",
                "Element 25",
                "Element 20",
                "Element 24",
                "Start of Topic N3"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=12&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step10_last_month), "Step#10 last_month : Titles are not equals");

        String expectedElement_Step11_last_year[] = {
                "Element 12",
                "Element 13",
                "Element 12",
                "Element 13"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-04&dates_range=13&entries=4,3,2&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step11_last_year), "Step#11 last_year: Titles are not equals");

        String expectedElement_Step13_Stories_only[] = {
                "Element 13",
                "Element 25",
                "Element 20",
                "Element 24",
                "Element 18",
                "Element 16",
                "Element 17",
                "Element 19",
                "Element 21",
                "Element 22",
                "Element 23",
                "Element 15",
                "Element 14"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=1&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step13_Stories_only), "Step#13 Stories_only: Titles are not equals");

        String expectedElement_Step14_Stories_only[] = {
                "Element 17"
        };

        dn.dTimeline.setURLControlPanelValues("allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=1&entries=1&formats=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step14_Stories_only), "Step#14 Stories_only/one format: Titles are not equals");



        String expectedElement_Step15_Stories_only_Future[] = {
                "Element 16",
                "Element 17",
                "Element 19",
                "Element 21",
                "Element 22",
                "Element 23",
                "Element 15",
                "Element 14"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=2&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step15_Stories_only_Future), "Step#15 Stories_only/future: Titles are not equals");


        String expectedElement_Step16_Stories_only_Past[] = {
                "Element 13",
                "Element 25",
                "Element 20",
                "Element 24",
                "Element 18"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=3&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step16_Stories_only_Past), "Step#16 Stories_only/past: Titles are not equals");


        String expectedElement_Step17_Stories_only_today[] = {
                "Element 16"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=4&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step17_Stories_only_today), "Step#17Stories_only/today: Titles are not equals");


        String expectedElement_Step17_Stories_only_yesterday[] = {
                "Element 18"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=6&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step17_Stories_only_yesterday), "Step#17 Stories_only/yesterday: Titles are not equals");


        String expectedElement_Step18_Stories_only_this_week[] = {
                "Element 20",
                "Element 24",
                "Element 18",
                "Element 16"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=7&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step18_Stories_only_this_week), "Step#18 Stories_only/this week: Titles are not equals");

        String expectedElement_Step19_Stories_only_next_week[] = {
                "Element 17",
                "Element 19",
                "Element 21"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=8&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step19_Stories_only_next_week), "Step#19 Stories_only/next week: Titles are not equals");

        String expectedElement_Step19_Stories_only_this_month [] = {
                "Element 24",
                "Element 18",
                "Element 16",
                "Element 17",
                "Element 19",
                "Element 21"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=10&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step19_Stories_only_this_month), "Step#19 Stories_only/this month: Titles are not equals");

        String expectedElement_Step20_Stories_only_next_month [] = {
                "Element 22"
                        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=11&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step20_Stories_only_next_month), "Step#20 Stories_only/next month: Titles are not equals");


        String expectedElement_Step20_Stories_only_last_month [] = {
                "Element 25",
                "Element 20"
        };

        dn.dTimeline.setURLControlPanelValues("allFormats=1&allPlatformOrganizations=1&allStatuses=1&date=2014-04-05&dates_range=12&entries=1&topic_id=3&without_date=1");
        softAssert.assertTrue(dn.dTimeline.isTitleEquals(expectedElement_Step20_Stories_only_last_month), "Step#20 Stories_only/last month: Titles are not equals");

        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() {
    }

}