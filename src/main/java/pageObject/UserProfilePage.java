package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserProfilePage extends BasePage {

    public static WebElement treatPage() {
        return driver.findElement(By.xpath("//div[contains(@class,'Account_innerContainer')]"));
    }

    public static WebElement firstName() {
        return driver.findElement(By.xpath("//input[@placeholder='First Name']"));
    }

    public static WebElement lastName() {
        return driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
    }

    public static WebElement emailAddress() {
        return driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
    }

    public static WebElement password() {
        return driver.findElement(By.xpath("//input[@placeholder='Password']"));
    }

    public static WebElement gender() {
        return driver.findElement(By.xpath("//div[contains(@class,'AccountSignupDropdown')]"));
    }

    public static WebElement birthDate() {
        return driver.findElement(By.xpath("//input[@placeholder='Birthdate']"));
    }

    public static WebElement successfulSignUp() {
        return driver.findElement(By.xpath("//div[contains(@class,'Account_signupSuccessHeaderText')]"));
    }

    public static WebElement profileHeader() {
        return driver.findElement(By.xpath("//div[contains(@class,'Account_headerText')]"));
    }

    public static class Buttons {
        public static WebElement signUp() {
            return driver.findElement(By.xpath("//button[contains(@class,'Account_signUp')]"));
        }

        public static WebElement signIn() {
            return driver.findElement(By.xpath("//button[contains(@class,'Account_signIn')]"));
        }

        public static WebElement facebook() {
            return driver.findElement(By.xpath("//button[contains(@class,'Account_socialFacebookButton')]"));
        }

        public static WebElement createProfile() {
            return driver.findElement(By.xpath("//button[contains(@class,'Account_defaultButton')]"));
        }

        public static WebElement done() {
            return driver.findElement(By.xpath("//button[contains(@class,'Account_defaultButton') and text()='Done']"));
        }
    }
}