package com.selenium.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

import static com.selenium.test.utils.TimeUtils.waitForSeconds;

/**
 * Mobile My Schedule Page object
 */
public class MobileMySchedulePage extends MobileControlPanelPage {

    private static MobileMySchedulePage mMySchedule;

    private static final String PAGE_URL = "mobile.htm#MySchedule:";

    @FindBy(xpath = "//div[text()='MY SCHEDULE']") //TODO optimize for multi locales (.. OR ..)
    private WebElement pageTitle;

    @FindBy(className = "no-content-panel")
    private WebElement noContentPanelElement;

    @FindBys(@FindBy(xpath = "//div[@id='element-container-wrapper']//*"))
    private List<WebElement> elementsContainer;

    @FindBys(@FindBy(xpath = "//span[@class='elementTitleContent']"))
    private List<WebElement> elementsTitles;

    private String dayPanelXPath = "//div[@id='gwt-debug-DayPanel_";

    //@FindBys(@FindBy(css = "div[class*='yt-lockup-tile yt-lockup-video']"))
    //private List<WebElement> videoElements;

    //Properties and text resources TODO Move it to config.properties?
    public static String messageNoContentMySchedule_ENG = "There are no entries for today and the next two weeks.";
    public static String cssNoContentHintColor = "rgba(139, 139, 139, 1)";
    public static String cssDateSeparatorTextStyle = "bold";
    public static String cssDateSeparatorTextColor = "rgba(1, 1, 1, 1)";
    public static String cssEntityPeriodTextStyle = "bold";
    public static String cssEntityPeriodTextStyleColor = "rgba(1, 1, 1, 1)";
    public static String cssEntityNameStyle = "normal";
    public static String cssEntityNameColor = "rgba(139, 139, 139, 1)";
    public static String cssEntityTitleStyle = "normal";
    public static String cssEntityTitleColor = "rgba(1, 1, 1, 1)";
    public static String cssDetailsLabelStyle = "normal";
    public static String cssDetailsLabelColor = "rgba(139, 139, 139, 1)";
    public static String cssDetailsMyTasksTableStyle = "normal";
    public static String cssDetailsMyTasksTableColor = "rgba(1, 1, 1, 1)";

    public static MobileMySchedulePage getInstance() throws Exception {
        if (mMySchedule == null) mMySchedule = new MobileMySchedulePage();
        return mMySchedule;
    }

    public MobileMySchedulePage() throws Exception {
        super();
    }

    public MobileMySchedulePage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open My Schedule page with default url for specified mobile.application.url
     */
    @Override
    public void openPage() throws Exception {
        openPage(PAGE_URL);
    }

    /**
     * @return true when My Schedule page is opened (title exists and wait 5 sec(temporary solution))
     */
    @Override
    public boolean isPageOpened() {
        try {
            //TODO Here we must use similar logic as in Desktop: "is-page-fully-loaded" element's state
            waitForSeconds(5);

            return pageTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @param date in YYYY-MM-DD format
     */
    public void navigateToDate(String date) {
        navigateToDate(PAGE_URL, date);
    }

    /**
     * @return displayed state of no content hint
     */
    public boolean isNoContentHintDisplayed() {
        try {
            return noContentPanelElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @return text for no content hint
     */
    public String getNoContentText() {
        String noContentText = "No content hint is not displayed";

        try {
            if (noContentPanelElement.isDisplayed()) {
                noContentText = noContentPanelElement.getText();
            }
        } catch (NoSuchElementException e) {
            return noContentText;
        }
        return noContentText;
    }


    public int getExtractedTitlesCount() {
        return elementsTitles.size();
    }

    /**
     * @return date separator for specified date with format like "13.10.2015"
     */
    public WebElement getDateSeparatorByDate(String date) {
        return getDriver().findElement(By.xpath(dayPanelXPath + date + "']/*[1]"));
    }

    /**
     * @return extracted entity for specified date with format like "13.10.2015" and specified order
     */
    public WebElement getEntityContent(String date, int order) {
        return getDriver().findElement(By.xpath(dayPanelXPath + date + "_Content']/*[" + order + "]"));
    }

    /**
     * @return expected text for property by variable name ("Properties and text resources" block)
     */
    public String getExpectedPropertyText(String propertyName) {
        try {
            return (String) (String) this.getClass().getDeclaredField(propertyName).get(this);
        } catch (Exception e) {
            return "Property is not found";
        }
    }


}