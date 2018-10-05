package com.selenium.test.testng.tests;

import com.selenium.test.pages.MobileCalendarPage;
import com.selenium.test.pages.MobileDetailedEntryPage;
import com.selenium.test.pages.MobileLoginPage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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
public class MobileCalendarPageTest extends BaseTest{

    @BeforeTest
    public void beforeTest() { }

    /**
     * Check 'no content hint' occurrence, text, color
     */
    @Test(priority = 1)
    public void testNoContentHint() throws Exception {
        dn.mLoginPage.doLoginAs("org1", "tomcattom");
        dn.mCalendar.openPage();
        dn.mCalendar.navigateToDate("2016-12-13");

        softAssert.assertEquals(dn.mCalendar.getNoContentText(), dn.mCalendar.messageNoContentStories_ENG, "Displayed and expected messages are not equal");
        softAssert.assertAll();
    }


    @Test(priority = 1)
//  [CWAECG-9736]: Mobile application, Calendar page: Issue with "Failed to load data" for user without assignments to group
    public void testExternalUserWithoutAnyAssignmentToGroup() throws Exception {
        List<String> users = Arrays.asList("ext", "wiz2");

        for (String userName : users) {
            dn.mLoginPage.doLoginAs(userName, "tomcattom");
            dn.mCalendar.openPage();

            softAssert.assertEquals(dn.mCalendar.getNoContentText(), dn.mCalendar.messageNoContentStories_ENG, "Displayed and expected messages are not equal");
            softAssert.assertAll();
        }
    }

    @Test(priority = 2)
    public void testSimpleEvent() throws Exception {
        Map<String, String> dateTitle = new HashMap<String, String>() {{
            put("2016-08-03", "Mobile 018");
            put("2016-08-02", "Mobile 017");
            put("2016-08-01", "Mobile 016");
            put("2016-09-04", "Mobile 031");
        }};

        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        //All day: expectedTitle_01 [CWAECG-9695]
        for (Map.Entry<String, String> entry : dateTitle.entrySet()) {
            dn.mCalendar.navigateToDate(entry.getKey());
            softAssert.assertTrue(dn.mCalendar.compareTitles(new String[]{entry.getValue()}), "Title of elements not equal");
        }
        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void testPureEvent() throws Exception {
        String[] expectedTitle = {"Mobile 035"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");

        dn.mCalendar.openPage();
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        //All day: expectedTitle_01
        dn.mCalendar.navigateToDate("2016-09-08");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle), "Title of elements equal");

        //[CWAECG-9715]: Issue with pure event extraction independently format filter selection
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setFormatsFilterValues("Text");
        dn.mCalendar.saveControlPanelSelection();

        dn.mCalendar.navigateToDate("2016-09-08");
        softAssert.assertFalse(dn.mCalendar.compareTitles(expectedTitle), "Element is represented");

        //Back to default TODO: Common logic for back to default implementation
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setFormatsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        softAssert.assertAll();
    }


    @Test(priority = 4)
    public void testRepeatingEvent() throws Exception {

//        String[] expectedTitle_01 = {"REP Yearly #1 - Every year on the 1st of January - Starts 01.01.2020, Ends never"};
//        String[] expectedTitle_02 = {"Mobile 042"};
//        String[] expectedTitle_03 = {"Assignments_extraction_repeat_event"};
//        String[] expectedTitle_04 = {"REP Daily API #1 - Separate occurrence (02.06.2015)"};

        Map<String, String> dateTitle01 = new HashMap<String, String>() {{
            put("2020-01-01", "REP Yearly #1 - Every year on the 1st of January - Starts 01.01.2020, Ends never"); //End never : expectedTitle_01
            put("2016-09-15", "Mobile 042");  //Occurrences : expectedTitle_02
        }};

        Map<String, String> dateTitle02 = new HashMap<String, String>() {{
            put("2016-01-01", "Assignments_extraction_repeat_event");  //End date : expectedTitle_03
            put("2015-06-02", "REP Daily API #1 - Separate occurrence (02.06.2015)");  //Separate occurrence: expectedTitle_04
        }};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");

        dn.mCalendar.openPage();
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

//        //End never : expectedTitle_01
//        dn.mCalendar.navigateToDate("2020-01-01");
//        softAssert.assertTrue(dn.mCalendar.isExtractionContainsExpectedTitles(expectedTitle_01), "Title of element not equal");
//        //Occurrences : expectedTitle_02
//        dn.mCalendar.navigateToDate("2016-09-15");
//        softAssert.assertTrue(dn.mCalendar.isExtractionContainsExpectedTitles(expectedTitle_02), "Title of elements not equal");
//        //End date : expectedTitle_03
//        dn.mCalendar.navigateToDate("2016-01-01");
//        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle_03), "Title of elements not equal");
//        //Separate occurrence: expectedTitle_04
//        dn.mCalendar.navigateToDate("2015-06-02");
//        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle_04), "Title of elements not equal");

        for (Map.Entry<String, String> entry : dateTitle01.entrySet()) {
            dn.mCalendar.navigateToDate(entry.getKey());
            softAssert.assertTrue(dn.mCalendar.isExtractionContainsExpectedTitles(new String[]{entry.getValue()}), "Title of elements not equal");
        }

        for (Map.Entry<String, String> entry : dateTitle02.entrySet()) {
            dn.mCalendar.navigateToDate(entry.getKey());
            softAssert.assertTrue(dn.mCalendar.isExtractionContainsExpectedTitles(new String[]{entry.getValue()}), "Title of elements not equal");
        }

        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void testCrossEvent() throws Exception {

        String[] expectedTitle = {"Mobile 062"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.setFormatsFilterValues("Photo");
        dn.mCalendar.saveControlPanelSelection();

        dn.mCalendar.navigateToDate("2016-10-16");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle), "Title of elements not equal");

        //Back to default TODO: Common logic for back to default implementation
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setFormatsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void testEventWithMoreThanOnePlatform() throws Exception {

        String[] expectedTitle = {"Mobile 023 Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.\n" +
                "Ä Ö Ü ß ä ö ü\n" +
                "Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.\n" +
                "\n" +
                "\n" +
                "A..."};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mCalendar.openPage();
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();
        dn.mCalendar.navigateToDate("2016-08-08");
        //[CWAECG-9695]
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle), "Title of elements not equal");

        softAssert.assertAll();
    }


    @Test(priority = 7)
    public void testEventWithStoryIdea() throws Exception {

        String[] expectedTitle = {"Mobile 027"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.setPubStatusesFilterValues("Accepted");
        dn.mCalendar.saveControlPanelSelection();

        dn.mCalendar.navigateToDate("2016-08-13");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle), "Title of elements not equal");

        //Back to default TODO: Common logic for back to default implementation
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setPubStatusesFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        softAssert.assertAll();
    }


    @Test(priority = 8)
    public void testEventWithEmptyDeadline() throws Exception {

        String[] expectedTitle = {"Mobile 030"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();
        dn.mCalendar.navigateToDate("2016-09-03");
        //[CWAECG-9695]
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle), "Title of elements not equal");

        softAssert.assertAll();
    }


    @Test(priority = 9)
    public void testEventWithEmptyProducerAndNRU  () throws Exception {

        Map<String, String> dateTitle = new HashMap<String, String>() {{
            put("2013-10-05", "User statistics: top is unassigned");   //Event with empty producer
            put("2009-08-07", "Story for unregistered user");   //Event with NRU producer
        }};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        for (Map.Entry<String, String> entry : dateTitle.entrySet()) {
            dn.mCalendar.navigateToDate(entry.getKey());
            softAssert.assertTrue(dn.mCalendar.compareTitles(new String[]{entry.getValue()}), "Title of elements not equal");
        }

        softAssert.assertAll();
    }

    @Test(priority = 10)
    public void testEventWithoutTask () throws Exception {

        String[] expectedTitle = {"WS no format"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mCalendar.openPage();
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        dn.mCalendar.navigateToDate("2222-02-28");
        softAssert.assertTrue(dn.mCalendar.isExtractionContainsExpectedTitles(expectedTitle), "Title of elements not equal");

        softAssert.assertAll();
    }


    @Test(priority = 11)
    public void testConfidEventByCreator () throws Exception {

        String[] notExpectedTitle = {"Mobile 059 - CWAECG-8392"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("org1", "tomcattom");
        dn.mCalendar.openPage();
        dn.mCalendar.navigateToDate("2016-10-12");
        softAssert.assertFalse(dn.mCalendar.compareTitles(notExpectedTitle), "Confidential element is visible for not allowed user");

        softAssert.assertAll();
    }


    @Test(priority = 11)
    public void testConfidEventByProducerOrg1 () throws Exception {

        String expectedTitles_org1[] = {"Story - org1", "Story - empty", "Story - no format", "Story - unreg", "Pure - empty", "Pure - no format", "Pure - org1", "Pure - unreg"};

        dn.mLoginPage.openPage();

        //Check confidentiality for user 'org1' [CWAECG-9705]
        dn.mLoginPage.doLoginAs("org1", "tomcattom");
        dn.mCalendar.openPage();
        dn.mCalendar.navigateToDate("2011-02-25");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitles_org1), "Titles of elements not equal");

        softAssert.assertAll();
    }


    @Test(priority = 11)
    public void testConfidEventByProducerEnt () throws Exception {

        String expectedTitles_ent[] = {"Story - ent", "Pure - ent"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("ent", "tomcattom");
        dn.mCalendar.openPage();
        dn.mCalendar.navigateToDate("2011-02-25");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitles_ent), "Titles of elements not equal");

        softAssert.assertAll();
    }


    @Test(priority = 12)
    public void testEventWithCrossPAAUser () throws Exception {

        String[] expectedTitle = {"Mobile 061 - CWAECG-8405"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("usa", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.navigateToDate("2016-10-13");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle), "Title of elements not equal");

        softAssert.assertAll();
    }


    @Test(priority = 12)
    public void testPureEventWithCrossPAAUser() throws Exception {
        String[] notExpectedTitle = {"Assignments_extraction_PURE_event", "Assignments_extraction_repeat_event_OCCURRENCE_ONE"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("usa", "tomcattom");
        dn.mCalendar.openPage();

//       [CWAECG-9731] : Mobile application, Calendar page, Pure event: Issue with representation not available elements for cross-PAA user
        dn.mCalendar.navigateToDate("2016-02-01");
        softAssert.assertFalse(dn.mCalendar.isExtractionContainsExpectedTitles(notExpectedTitle), "Element is represented");

        softAssert.assertAll();
    }


    @Test(priority = 13)
    public void testEventCameFromFeed () throws Exception {

        Map<String, String> dateTitle = new HashMap<String, String>() {{
            put("2016-09-11", "München: Mobile 038 (dpa - mit Berichterst.)");     //Feed event: expectedTitle_01
            put("2016-09-20", "Mobile 047");   //Feed event: expectedTitle_02
        }};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("org1", "tomcattom");
        dn.mCalendar.openPage();
        for (Map.Entry<String, String> entry : dateTitle.entrySet()) {
            dn.mCalendar.navigateToDate(entry.getKey());
            softAssert.assertTrue(dn.mCalendar.compareTitles(new String[]{entry.getValue()}), "Title of elements not equal");
        }
        softAssert.assertAll();
    }


    @Test(priority = 14)
    public void testEventWithUsaTZShifting () throws Exception {

        String[] expectedTitle = {"Mobile 056"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("usa", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.navigateToDate("2016-10-06");
        softAssert.assertTrue(dn.mCalendar.isExtractionContainsExpectedTitles(expectedTitle), "Title is represented");
        dn.mCalendar.navigateToDate("2016-10-07");
        softAssert.assertFalse(dn.mCalendar.isExtractionContainsExpectedTitles(expectedTitle), "Title is not represented");

        softAssert.assertAll();
    }

    @Test(priority = 14)
    public void testEventWithGermanyTZShifting () throws Exception {

        String[] expectedTitle = {"Mobile 056"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("org1", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.navigateToDate("2016-10-06");
        softAssert.assertFalse( dn.mCalendar.isExtractionContainsExpectedTitles(expectedTitle), "Title is not represented");
        dn.mCalendar.navigateToDate("2016-10-07");
        softAssert.assertTrue( dn.mCalendar.isExtractionContainsExpectedTitles(expectedTitle), "Title is represented");

        softAssert.assertAll();
    }

    @Test(priority = 15)
    public void testStoryWithSeparateAppointment () throws Exception {

        String[] expectedTitle_01 = {"Mobile 012"};
        String[] expectedTitle_02 = {"Mobile 020a"};
        String[] expectedTitle_03 = {"Mobile 034"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("org1", "tomcattom");

        dn.mCalendar.openPage();

        //All day:
        dn.mCalendar.navigateToDate("2016-03-01");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle_01), "Title of elements equal");
        //Start - …:
        dn.mCalendar.navigateToDate("2016-08-05");
        softAssert.assertTrue(dn.mCalendar.isExtractionContainsExpectedTitles(expectedTitle_02), "Title of elements equal");
        //Period:
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setPlatformsFilterValues("NY Times (Online)");
        dn.mCalendar.saveControlPanelSelection();

        dn.mCalendar.navigateToDate("2016-09-07");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle_03), "Title of elements equal");

//        Back to default
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setPlatformsFilterValues("NY Times");
        dn.mCalendar.saveControlPanelSelection();

        softAssert.assertAll();
    }


    @Test(priority = 16)
    public void testEventWithSeveralSeparateAppointments () throws Exception {

        String[] expectedTitle = {"Mobile 057"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("cross", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.openControlPanel();
        dn.mCalendar.setGroupsFilterValues("All");
        dn.mCalendar.setPlatformsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        dn.mCalendar.navigateToDate("2016-10-08");
        softAssert.assertTrue(dn.mCalendar.compareTitles(expectedTitle), "Title of elements not equal");

        softAssert.assertAll();
    }

    /**
     *  ============================FUNCTIONALITY==========================================
     */

    @Test(priority = 17)
    public void testEventWithROUser () throws Exception {

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("read1", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.navigateToDate("2016-08-03");
        dn.mCalendar.openElementByTitle("Mobile 018");
        MobileDetailedEntryPage mobileDetailedEntryPage = new MobileDetailedEntryPage(false);

        //[CWAECG-9695]
//        softAssert.assertEquals(mobileDetailedEntryPage.getErrorMessageText(), MobileDetailedEntryPage.MESSAGE_RO_MODE_ENG, "Error messages are not equal");
        softAssert.assertTrue(mobileDetailedEntryPage.isDEPageOpenedInROMode(), "DE-form is opened in RO mode");

        softAssert.assertAll();

}


    @Test(priority = 18)
    public void testMainEventsSorting () throws Exception {

        String expectedTitles[] = {"Event1", "Event2", "Event4", "Event5", "Event7", "Event6", "AEvent3", "Event3"};

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("ed1", "tomcattom");
        dn.mCalendar.openPage();

        dn.mCalendar.openControlPanel();
        dn.mCalendar.setPlatformsFilterValues("ent");
        dn.mCalendar.setFormatsFilterValues("All");
        dn.mCalendar.saveControlPanelSelection();

        dn.mCalendar.navigateToDate("2011-11-11");
        softAssert.assertTrue(dn.mCalendar.compareElementsOrder(expectedTitles), "Titles of elements not equal");

        softAssert.assertAll();

//      Back to default
        dn.mCalendar.openControlPanel();
        dn.mCalendar.setPlatformsFilterValues("NY Times");
        dn.mCalendar.setFormatsFilterValues("Photo");
        dn.mCalendar.saveControlPanelSelection();

        softAssert.assertAll();
    }

    @AfterTest
    public void afterTest() { }

}