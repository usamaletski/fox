package com.selenium.test.pages.desktop;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.pages.desktop.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.selenium.test.utils.TimeUtils.waitForSeconds;

/**
 * Desktop Topics Page object
 */
public class DesktopTopicsPage extends BasePage {

    private static final String PAGE_URL = "topicsPage.htm";
    private static DesktopTopicsPage dTopic;

//    TODO: add translations instead ...
    @FindBy(xpath = "//span[@class='controlHeader' and (text()='Topics' or text()='...' or text()='...' or text()='...')]")
    private WebElement pageTitle;

//    TODO: add translations instead ...
    @FindBy(xpath = "//div[@class='dialog-title']")
    private WebElement dialogTopicTitle;
//    @FindBy(xpath = "//div[@class='dialog-title']/span[.='Topic' or .='...']")
//    private WebElement dialogTopicTitle;

    @FindBy(xpath = "//input[@id='page-is-fully-loaded' and @value='true']")
    private WebElement locatorPageFullyLoaded;

    @FindBy(xpath = "//div[@class='topic-date-separator']/preceding-sibling::div[@id='date-box']")
    private WebElement fieldDateFrom;

    @FindBy(xpath = "//div[@class='topic-date-separator']/following-sibling::div[@id='date-box']")
    private WebElement fieldDateTo;

    @FindBy(xpath = "//div[@class='middleCenterInner']//a[@class='button bold primary-button']")
    private WebElement btnSaveTopicDialog;

    @FindBys(@FindBy(xpath = "//div[contains(@class,'topic-name')]"))
    private List<WebElement> topicNames;

    @FindBy(id = "departmentsFilter")
    private WebElement locatorGroupFilter;

    @FindBy(className = "plusIcon")
    private WebElement plusIcon;

    @FindBys(@FindBy(xpath = "//div[@class='plusIcon']"))
    private List<WebElement> allPlusIcons;

    @FindBy(xpath = "//div[@class='label']/parent::td/preceding-sibling::td//a[contains(@class,'aCheckBox')]")
    private WebElement parentCheckbox;

    @FindBys(@FindBy(xpath = "//table[@class='content']//a[contains(@class,'aCheckBox')]"))
    private List<WebElement> allFilterCheckboxes;

    @FindBy(xpath = "//div[@class='selectBlock']/a[@class='button bold primary-button']")
    private WebElement buttonSaveFilter;

    @FindBy(id = "applyButton")
    private WebElement buttonApplyControlPanel;


    public static DesktopTopicsPage getInstance() throws Exception {
        if (dTopic == null) dTopic  = new DesktopTopicsPage();
        return dTopic ;
    }

    public DesktopTopicsPage() throws Exception {
        super();
    }

    public DesktopTopicsPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open Topics page with default url for specified mobile.application.url
     */
    @Override
    public void openPage() throws Exception {
        openPage(PAGE_URL);
    }

    /**
     * @return true when Topics page is opened (title exists and wait 5 sec(temporary solution))
     */
    @Override
    public boolean isPageOpened() {
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            return pageTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isTopicDialogOpen (){
        try {
            return dialogTopicTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


// TODO: Add checking for negative cases
    public void setTopicDates(String topicName, String dateFrom, String dateTo) {
        topicNameDefault = topicName;
        for (int i = 0; i < topicNames.size(); i++) {
            if (topicName.equals(topicNames.get(i).getText())) {
                topicNames.get(i).click();
                if (isTopicDialogOpen()) {
                    dateFromDefault = fieldDateFrom.getText();
                    dateToDefault = fieldDateTo.getText();

                    fieldDateFrom.click();
                    fieldDateFrom.sendKeys(Keys.CONTROL + "a");
                    fieldDateFrom.sendKeys(Keys.DELETE);
                    fieldDateFrom.sendKeys(dateFrom);

                    fieldDateTo.click();
                    fieldDateTo.sendKeys(Keys.CONTROL + "a");
                    fieldDateTo.sendKeys(Keys.DELETE);
                    fieldDateTo.sendKeys(dateTo);
                    dialogTopicTitle.click(); // simulation click outside

                    btnSaveTopicDialog.click();
                }
            }
        }

    }

// Back to default
    String topicNameDefault;
    String dateFromDefault;
    String dateToDefault;

// TODO: Add checking for negative cases
    public void setTopicDatesDefault () {
        System.out.println("topicNameDefault =  " + topicNameDefault + " dateFromDefault = " + dateFromDefault + " dateToDefault =  " + dateToDefault);
        for (int i = 0; i < topicNames.size(); i++) {
            if (topicNameDefault.equals(topicNames.get(i).getText())) {
                topicNames.get(i).click();
                if (isTopicDialogOpen()) {
                    fieldDateFrom.click();
                    fieldDateFrom.sendKeys(Keys.CONTROL + "a");
                    fieldDateFrom.sendKeys(Keys.DELETE);
                    fieldDateFrom.sendKeys(dateFromDefault);
                    fieldDateTo.click();
                    fieldDateTo.sendKeys(Keys.CONTROL + "a");
                    fieldDateTo.sendKeys(Keys.DELETE);
                    fieldDateTo.sendKeys(dateToDefault);
                    dialogTopicTitle.click(); // simulation click outside
                    btnSaveTopicDialog.click();
                }
            }
        }
    }


    /**
     * Expand all plus icons
     */
    public void expandAllPlusIcons() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        while (!allPlusIcons.isEmpty()) {
            try {
                plusIcon.click();
            } catch (NoSuchElementException e) {
            }
        }
    }


    /**
     * Check: Is checkbox checked?
     * @param locatorCheckbox  it's WebElement for inspected checkbox
     * @return true or false
     */
    private boolean isCheckboxChecked(WebElement locatorCheckbox) {
        if ((locatorCheckbox.getAttribute("class")).contains("checked")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check global checkbox(first parent) with all child
     */
    public void checkGlobalCheckboxWithAllChild() {
        while (isCheckboxChecked(parentCheckbox)) {
            parentCheckbox.click();
        }
        parentCheckbox.click();
        Assert.assertTrue("All child chekboxes are not checked.", isAllCheckboxesChecked());

    }

    /**
     * Check: Is state of all checkboxes in filter layer checked?
     * @return true or false
     */
    private boolean isAllCheckboxesChecked(){
        int countOfCheckedCheckboxes = 0;
        for (int i = 0; i < allFilterCheckboxes.size(); i++) {
            if (isCheckboxChecked(allFilterCheckboxes.get(i))) {
                countOfCheckedCheckboxes++;
            }
        }
        if (countOfCheckedCheckboxes == allFilterCheckboxes.size()) {
            return true;
        } else {
            return false;
        }
    }


    public void checkAllCheckboxesGroupFilter() {
        locatorGroupFilter.click();
        expandAllPlusIcons();
        checkGlobalCheckboxWithAllChild();
        buttonSaveFilter.click();
        buttonApplyControlPanel.click();

        locatorPageFullyLoaded.isDisplayed();

    }

}