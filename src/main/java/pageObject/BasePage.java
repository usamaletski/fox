package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webDriver.Driver;

public class BasePage {
    protected static WebDriver driver = Driver.getCurrentDriver();

    public static WebElement topCategory(String topCategory) {
        return driver.findElement(By.xpath("//div[contains(@class,'Header_navLinks')]/a[text()='" + topCategory + "']"));
    }

    public static WebElement userProfile() {
        return driver.findElement(By.xpath(" //div[@data-test='profile-icon']"));
    }

    public static class DropDown {
        public static WebElement layer() {
            return driver.findElement(By.xpath("//div[contains(@class,'Dropdown_itemContainer')]"));
        }

        public static WebElement item(String item) {
            return driver.findElement(By.xpath("//div[contains(@class,'Dropdown_itemContainer')]//div[contains(@class,'itemContent') and text()='" + item + "']"));
        }
    }
}
