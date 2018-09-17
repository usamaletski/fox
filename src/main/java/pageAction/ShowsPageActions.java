package pageAction;

import org.openqa.selenium.WebElement;
import pageObject.ShowsPage;
import supportMethods.Excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowsPageActions extends BasePageActions {
    public static List<String> allTitles;
    public static List<String> lastTitles;

    public static void openSubCategory(String categoryName) {
        ShowsPage.category(categoryName).click();
    }

    public static void loadAllElements() throws InterruptedException {
        List<WebElement> visibleElements = ShowsPage.items();
        List<WebElement> allElements = new ArrayList<>();
        while (visibleElements.size() != allElements.size()) {
            visibleElements = ShowsPage.items();
            scrollDown();
            sleep();
            allElements = ShowsPage.items();
        }
    }

    /**
     * @return all titles of Shows
     * @throws InterruptedException
     */
    public static List<String> getTitles() throws InterruptedException {
        List<String> expected = new ArrayList<>();
        if (ShowsPage.items().size() > 0) {
            try {
                loadAllElements();
                List<WebElement> temp = ShowsPage.items();
                for (WebElement ele : temp) {
                    scrollToElement(ele);
                    expected.add(ele.getText());
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else {
            System.out.println("\nPage is empty");
            return allTitles;
        }
        return allTitles = expected;
    }

    /**
     * @param count = capture last "count" Shows
     * @return
     * @throws InterruptedException
     */
    public static List<String> getLastTitles(int count) throws InterruptedException {
        List<String> titles = getTitles();
        List<String> expected = new ArrayList<>();
        if (titles.size() >= count) {
            for (int i = titles.size() - 1; i >= titles.size() - count; i--) {
                //clean up info about apisodes
                expected.add(titles.get(i).replaceAll("\\(.*?\\) ?", ""));
            }
            return lastTitles = expected;
        } else {
            return lastTitles = titles;
        }
    }

    public static void writeLastTitlesToShowsExcel() throws InterruptedException {
        int rownum = 1;
        for (String item : lastTitles) {
            Excel.write(rownum, 0, item);
            rownum++;
        }
    }

    /**
     * verification that all the above "count" shows displayed for tab
     * @param pageTab
     * @param expectedText
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public static boolean isDuplicateRecord(String pageTab, String expectedText) throws InterruptedException, IOException {
        List<String> currentTitles;
        int rowOfShow;
        int columnOfTab;
        openSubCategory(pageTab);
        loadAllElements();
        currentTitles = getTitles();
        for (String expTitle : lastTitles) {
            if (currentTitles.contains(expTitle)) {
                //if duplicate Shows write Text to Excel according to expected tab
                try {
                    rowOfShow = Excel.searchStringInXslx(expTitle).getRow();
                    columnOfTab = Excel.searchStringInXslx(pageTab).getColumn();
                    Excel.write(rowOfShow, columnOfTab, expectedText);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return currentTitles.containsAll(lastTitles);
    }
}