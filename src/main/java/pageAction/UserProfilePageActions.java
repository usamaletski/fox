package pageAction;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.UserProfilePage;

public class UserProfilePageActions extends BasePageActions {
    public static String firstName;

    public static boolean isOpened() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(UserProfilePage.treatPage()));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void setFirstName(String fName) {
        UserProfilePage.firstName().sendKeys(fName);
        firstName = fName;
    }

    public static void setLastName(String lastName) {
        UserProfilePage.lastName().sendKeys(lastName);
    }

    public static void setEmail(String email) {
        UserProfilePage.emailAddress().sendKeys(email);
    }

    public static void setPass(String pwd) {
        UserProfilePage.password().sendKeys(pwd);
    }

    public static void setGender(String gender) {
        DropDown.open(UserProfilePage.gender());
        DropDown.set(gender);
    }

    public static void setBirthDay(String pwd) {
        UserProfilePage.birthDate().sendKeys(pwd);
    }

    public static String getProfileHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(UserProfilePage.profileHeader()));
        return UserProfilePage.profileHeader().getText();
    }

    public static boolean isSignUpSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOf(UserProfilePage.successfulSignUp()));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static class Buttons {
        public static void clickSignUp() {
            UserProfilePage.Buttons.signUp().click();
        }

        public static void clickSignIn() {
            UserProfilePage.Buttons.signIn().click();
        }

        public static void clickFacebook() {
            UserProfilePage.Buttons.facebook().click();
        }

        public static void clickCreateProfile() {
            wait.until(ExpectedConditions.elementToBeClickable(UserProfilePage.Buttons.createProfile()));
            UserProfilePage.Buttons.createProfile().click();
        }

        public static void clickDone() throws InterruptedException {
            wait.until(ExpectedConditions.elementToBeClickable(UserProfilePage.Buttons.done()));
            UserProfilePage.Buttons.done().click();
            sleep();
        }
    }
}