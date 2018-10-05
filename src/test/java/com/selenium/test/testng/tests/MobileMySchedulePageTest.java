package com.selenium.test.testng.tests;

import com.selenium.test.pages.MobileLoginPage;
import com.selenium.test.pages.MobileMySchedulePage;
import com.selenium.test.testng.listeners.ScreenShotOnFailListener;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by svo
 * Uses TestNG test framework
 * Tests uses work with Page Object Pattern (https://code.google.com/p/selenium/wiki/PageObjects)
 */
@Listeners({ScreenShotOnFailListener.class})
public class MobileMySchedulePageTest extends BaseTest{

    @BeforeTest
    public void beforeTest() { }

    /**
     * Check 'no content hint' occurrence, text, color
     */
    @Test(priority = 1)
    public void testNoContentHint() throws Exception {
        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("new", "tomcattom");
        dn.mMySchedule.openPage();
        //Check no content hint's state
        softAssert.assertTrue(dn.mMySchedule.isNoContentHintDisplayed(), "No content hint is not displayed");
        //Check no content hint's text
        softAssert.assertEquals(dn.mMySchedule.getNoContentText(), MobileMySchedulePage.messageNoContentMySchedule_ENG, "Displayed and expected messages are not equal");
        //Check no content hint's color
        WebElement content = WebDriverFactory.getDriver().findElement(By.className("no-content-panel"));
        String noContentHintColor = content.getCssValue("color");
        softAssert.assertEquals(noContentHintColor, MobileMySchedulePage.cssNoContentHintColor, "Displayed and expected colors are not equal");
        softAssert.assertAll();
    }


    /**
     * Check the extraction on the My Schedule page (aggregated, simple)
     */
    @Test(priority = 2)
    public void testExtractionIntegrity() throws Exception {
        //Expectations
        int expectedElementsCount = 19;

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("ed1", "tomcattom");
        dn.mMySchedule.openPage();

        //Assertion for checking the number of extracted elements
        dn.mMySchedule.navigateToDate("2015-10-13");
        softAssert.assertEquals(dn.mMySchedule.getExtractedTitlesCount(), expectedElementsCount, "Number of elements");
        softAssert.assertAll();
    }


    /**
     * Check the design aspects for the extracted data
     */
    @Test(priority = 3)
    public void testExtractedDataDesign() throws Exception {
        //Expectations:
        //For date separator:
        String dateSeparatorText = "Tue, 13 Oct";
        //For absences:
        int firstAbsenceOrder = 1;
        //For appointments:
        int firstAppointmentOrder = 2;
        String firstAppointmentDetailsText = "Tasks\n" + "Text, due Wed, 14 Oct";

        dn.mLoginPage.openPage();
        dn.mLoginPage.doLoginAs("ed1", "tomcattom");
        dn.mMySchedule.openPage();
        dn.mMySchedule.navigateToDate("2015-10-13");


        //TODO move element finding and needed value getting to Page Object
        //Assertions for Date separator
        WebElement dateSeparator = dn.mMySchedule.getDateSeparatorByDate("13.10.2015");
        softAssert.assertEquals(dateSeparator.getText(), dateSeparatorText, "Text for date separator");
        softAssert.assertEquals(dateSeparator.getCssValue("font-weight"), MobileMySchedulePage.cssDateSeparatorTextStyle, "Style for date separator text");
        softAssert.assertEquals(dateSeparator.getCssValue("color"), MobileMySchedulePage.cssDateSeparatorTextColor, "Color for date separator text");


        //Assertions for the first Absence
        WebElement firstAbsence = dn.mMySchedule.getEntityContent("13.10.2015", firstAbsenceOrder);

        //All day label
        try {
            softAssert.assertEquals(firstAbsence.findElement(By.xpath(".//div[text()='All day']")).getCssValue("font-weight"), MobileMySchedulePage.cssEntityPeriodTextStyle, "Text or style for absence period");
            softAssert.assertEquals(firstAbsence.findElement(By.xpath(".//div[text()='All day']")).getCssValue("color"), MobileMySchedulePage.cssEntityPeriodTextStyleColor, "Color for absence period");
        } catch (NoSuchElementException e) {
            softAssert.fail("All day label was not found");
        }

        //Absence label
        try {
            softAssert.assertEquals(firstAbsence.findElement(By.xpath(".//div[text()='Absence']")).getCssValue("font-weight"), MobileMySchedulePage.cssEntityNameStyle, "Text or style for absence entity name");
            softAssert.assertEquals(firstAbsence.findElement(By.xpath(".//div[text()='Absence']")).getCssValue("color"), MobileMySchedulePage.cssEntityNameColor, "Color for absence entity name");
        } catch (NoSuchElementException e) {
            softAssert.fail("Absence label was not found");
        }

        //Absence note
        try {
            softAssert.assertEquals(firstAbsence.findElement(By.xpath(".//span[text()='13']")).getCssValue("font-weight"), MobileMySchedulePage.cssEntityTitleStyle, "Text or style for absence note");
            softAssert.assertEquals(firstAbsence.findElement(By.xpath(".//span[text()='13']")).getCssValue("color"), MobileMySchedulePage.cssEntityTitleColor, "Color for absence note");
        } catch (NoSuchElementException e) {
            softAssert.fail("Absence note was not found");
        }

        //Absence details
        softAssert.assertTrue(firstAbsence.findElements(By.xpath(".//table[@class='panel-details']/tbody/tr")).isEmpty(), "Absence details");


        //Assertions for the first Appointment
        WebElement firstAppointment = dn.mMySchedule.getEntityContent("13.10.2015", firstAppointmentOrder);

        //All day label
        try {
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//div[text()='All day']")).getCssValue("font-weight"), MobileMySchedulePage.cssEntityPeriodTextStyle, "Text or style for appointment period");
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//div[text()='All day']")).getCssValue("color"), MobileMySchedulePage.cssEntityPeriodTextStyleColor, "Color for appointment period");
        } catch (NoSuchElementException e) {
            softAssert.fail("All day label was not found");
        }

        //Appointment label
        try {
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//div[text()='Appointment']")).getCssValue("font-weight"), MobileMySchedulePage.cssEntityNameStyle, "Text or style for appointment entity name");
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//div[text()='Appointment']")).getCssValue("color"), MobileMySchedulePage.cssEntityNameColor, "Color for appointment entity name");
        } catch (NoSuchElementException e) {
            softAssert.fail("Appointment label was not found");
        }

        //Appointment note
        try {
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//span[text()='Pure event - 13, deadline - 14']")).getCssValue("font-weight"), MobileMySchedulePage.cssEntityTitleStyle, "Text or style for appointment note");
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//span[text()='Pure event - 13, deadline - 14']")).getCssValue("color"), MobileMySchedulePage.cssEntityTitleColor, "Color for appointment note");
        } catch (NoSuchElementException e) {
            softAssert.fail("Appointment note was not found");
        }

        //Appointment details
        try {
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//table[@class='panel-details']/tbody")).getText(), firstAppointmentDetailsText, "Text for appointment details");
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//table[@class='panel-details']/tbody/tr/td[@class='noteInfo infoTitle']")).getCssValue("font-weight"), MobileMySchedulePage.cssDetailsLabelStyle, "Style for label of appointment details");
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//table[@class='panel-details']/tbody/tr/td[@class='noteInfo infoTitle']")).getCssValue("color"), MobileMySchedulePage.cssDetailsLabelColor, "Color for label of appointment details");
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//table[@class='panel-details']/tbody/tr/td[@class='myTasksTable width100']")).getCssValue("font-weight"), MobileMySchedulePage.cssDetailsMyTasksTableStyle, "Style for appointment tasks");
            softAssert.assertEquals(firstAppointment.findElement(By.xpath(".//table[@class='panel-details']/tbody/tr/td[@class='myTasksTable width100']")).getCssValue("color"), MobileMySchedulePage.cssDetailsMyTasksTableColor, "Color for appointment tasks");
        } catch (NoSuchElementException e) {
            softAssert.fail("Appointment details are not found");
        }

/*        for (int i=0; i<firstAbsence.size(); i++ ) {
            //String value = fullContent.get(i).getText();}
            System.out.println(firstAbsence.get(i).getText());
        }*/

        softAssert.assertAll();
    }


    @AfterTest
    public void afterTest() { }

}