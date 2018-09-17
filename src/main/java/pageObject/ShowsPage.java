package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShowsPage extends BasePage {
    public static WebElement category(String categoryName) {
        return driver.findElement(By.xpath("//div[contains(@class,'PageHeaderBrowse_tabContainer_3htBb')]/a[text()='" + categoryName + "']"));
    }

    public static List<WebElement> items() {
        return driver.findElements(By.xpath("//div[contains(@class,'TileGrid_container')]//div[contains(@class,'Tile_tile')]"));
    }
}