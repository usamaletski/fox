package testDefinition;

import org.testng.annotations.Test;

import pageAction.BasePageActions;
import pageAction.ShowsPageActions;
import pageAction.UserProfilePageActions;
import supportMethods.Excel;

import java.util.*;

public class FoxTest extends BaseTest {

    @Test(priority = 1)
    public void createAccount() throws Exception {
        BasePageActions.open();
        BasePageActions.openUserProfile();
        UserProfilePageActions.Buttons.clickSignUp();
        UserProfilePageActions.setFirstName(genData.generateRandomString(7));
        UserProfilePageActions.setLastName(genData.generateRandomString(7));
//TODO: Please, check (probably a bug!): I tried to use different email generators with a different domain (like @trashmail.com) but system declined all of them.
//TODO: In this example, I have simplified the generator as much as possible to pass the registration procedure
        UserProfilePageActions.setEmail(genData.generateEmail(4));
        UserProfilePageActions.setPass(genData.generateRandomAlphaNumeric(7));
        UserProfilePageActions.setGender("Male");
        UserProfilePageActions.setBirthDay("01/01/1950");
        UserProfilePageActions.Buttons.clickCreateProfile();
        UserProfilePageActions.Buttons.clickDone();
        BasePageActions.openUserProfile();

        hardAssert.assertTrue((UserProfilePageActions.getProfileHeader()).contains(UserProfilePageActions.firstName));
    }

    @Test(priority = 2)
    public void captureDuplicateRecordsPerTab() throws Exception {
        List<String> tabs = Arrays.asList("FX", "National Geographic", "FOX Sports", "All Shows");

        Excel.clear(1);
        BasePageActions.open();
        BasePageActions.openTopCategory("Shows");
        ShowsPageActions.openSubCategory("FOX");
        ShowsPageActions.getLastTitles(4);
        ShowsPageActions.writeLastTitlesToShowsExcel();
        for (String tab : tabs) {
            softAssert.assertTrue(ShowsPageActions.isDuplicateRecord(tab, "Duplicate Record"),
                    "Shows " + ShowsPageActions.lastTitles + " are displayed for tab: " + tab + " ->");
        }

        softAssert.assertAll();
    }
}
