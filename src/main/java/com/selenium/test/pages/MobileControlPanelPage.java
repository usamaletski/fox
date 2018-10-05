package com.selenium.test.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Mobile Control Panel Page object
 */
public class MobileControlPanelPage extends BasePage {

    private static MobileControlPanelPage mControlPanel;

    @FindBy(id = "gwt-debug-expand-menu-icons-container")
    private WebElement controlPanelExpandCollapseButton;

    //TODO:common naming locators (Naming convention)
    @FindBy(xpath = "//a[@class='button bold primary-button' and text()='Save']")
    private WebElement btnSaveControlPanel;

    @FindBy(xpath = "//div[@class='buttons noWrap']/a[@class='link-third-button' and text()='Cancel']")
    private WebElement btnCancelControlPanel;

    @FindBy(xpath = "//a[@class='button bold primary-button' and text()='Enter']")
    private WebElement btnEnterFilter;

    @FindBy(xpath = "//div[@class='selectBlock']/a[@class='link-third-button' and text()='Cancel']")
    private WebElement btnCancelFilter;

    //TODO: exact element type using in such cases (label, div, container, link, button, etc)
    @FindBy(xpath = "//div[@class='filter-combo-box departmentsFilter']")
    private WebElement groupsFilter;

    @FindBy(id = "objectPagesFilter")
    private WebElement platformsFilter;

    @FindBy(xpath = "//div[@class='filter-combo-box status-filter']")
    private WebElement pubStatusFilter;

    @FindBy(xpath = "//div[@class='filter-combo-box format-filter']")
    private WebElement formatsFilter;

    @FindBy(className = "plusIcon")
    private WebElement plusIcon;

    @FindBys(@FindBy(xpath = "//div[@class='plusIcon']"))
    private List<WebElement> allPlusIcons;

    @FindBy(className = "minusIcon")
    private WebElement minusIcon;

    @FindBys(@FindBy(xpath = "//div[@class='minusIcon']"))
    private List<WebElement> allMinusIcons;

    @FindBy(xpath = "//div[@class='label']/parent::td/preceding-sibling::td//a[contains(@class,'aCheckBox')]")
    private WebElement parentCheckbox;

    @FindBys(@FindBy(xpath = "//table[@class='content']//a[contains(@class,'aCheckBox')]"))
    private List<WebElement> allFilterCheckboxes;

    @FindBy(xpath = "//div[@class='gwt-PopupPanel modal-dialog expanded-popup-panel' and contains(@style,'overflow: visible')]")
    private WebElement locatorOpenedControlPanelLayer;

    @FindBy(xpath = "//div[contains(@class,'gwt-PopupPanel modal-dialog dialogBox comboContentHolder objectPagesPopup') and contains(@style,'overflow: visible')]")
    private WebElement locatorOpenedFilterLayer;

    //For getting checkboxes by its labels
    private String locatorCheckedParentCheckboxByLabel = "//div[@class='label' and text()='%s']/parent::td/preceding-sibling::td//a[@class='aCheckBox checked']";
    private String locatorUncheckedParentCheckboxByLabel = "//div[@class='label' and text()='%s']/parent::td/preceding-sibling::td//a[@class='aCheckBox']";

    private String locatorAllChildsCheckboxesByParentLabel = "//div[@class='label' and text()='%s']/../parent::tr/following-sibling::tr//a[contains(@class,'aCheckBox')]";
    private String locatorCheckboxByLabel = "//table[@class='content']//div[@class='label' and text()='%1s' or @class='checkBoxLabel' and span[.='%2s']]/../..//a[contains(@class,'aCheckBox')]";
    private String locatorCheckboxByLabelForOrganization = "//table[@class='content']//div[@class='gwt-Label' and text()='%1s']/../parent::tr/following-sibling::tr//div[@class='label' and text()='%2s' or @class='checkBoxLabel' and span[.='%3s']]/../..//a[contains(@class,'aCheckBox')]";

    //For getting states of checkboxes
    private String locatorParentCheckboxByLabel = "//div[@class='label' and text()='%s']/parent::td/preceding-sibling::td//a[contains(@class,'aCheckBox')]";

    //For getting checkboxes by its organization's label
    private String allCheckboxesByOranization = "//div[@class='gwt-Label' and text()='%s']/../parent::tr/following-sibling::tr//a[contains(@class,'aCheckBox')]";

    public static MobileControlPanelPage getInstance() throws Exception {
        if (mControlPanel == null) mControlPanel = new MobileControlPanelPage();
        return mControlPanel;
    }

    public MobileControlPanelPage() throws Exception {
        super();
    }

    public MobileControlPanelPage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    @Override
    protected void openPage() throws Exception {    }

    @Override
    public boolean isPageOpened() { return false; }

//======================== Getting elements locators =============================


    /**
     * Get list of locators(WebElements) all childs checkboxes by parent label
     * @param checkboxLabel
     * @return = list of locators all childs checkboxes
     */
    private List<WebElement> getListOfAllChildsCheckboxesByParentLabel(String checkboxLabel) {
        return getDriver().findElements(By.xpath(String.format(locatorAllChildsCheckboxesByParentLabel, checkboxLabel)));
    }

    /**
     *  Get locator(webelement) of checkbox by its label
     * @param checkboxLabel
     * @return = locator of checkbox
     */
    private WebElement getLocatorCheckboxByLabel(String checkboxLabel) {
        return getDriver().findElement(By.xpath(String.format(locatorCheckboxByLabel, checkboxLabel,checkboxLabel)));
    }

    /**
     * Get locator(webelement) of checkbox by label for Organization
     * @param checkboxLabel
     * @param organizationLabel
     * @return = locator of checkbox
     */
    private WebElement getLocatorCheckboxByLabelForOrganization(String checkboxLabel, String organizationLabel) {
        return getDriver().findElement(By.xpath(String.format(locatorCheckboxByLabelForOrganization, organizationLabel, checkboxLabel,checkboxLabel)));
    }

    /**
     *Get locator(webelement) of unchecked parent checkbox by label
     * @param checkboxLabel
     * @return
     */
    private WebElement getUncheckedParentCheckboxByLabel(String checkboxLabel) {
        return getDriver().findElement(By.xpath(String.format(locatorUncheckedParentCheckboxByLabel, checkboxLabel)));
    }

    /**
     * Get locator(webelement) of checked parent checkbox by label
     * @param checkboxLabel
     * @return
     */
    private WebElement getCheckedParentCheckboxByLabel(String checkboxLabel) {
        return getDriver().findElement(By.xpath(String.format(locatorCheckedParentCheckboxByLabel, checkboxLabel)));
    }

    /**
     * Get locator(webelement) of parent checkbox by label
     * @param checkboxLabel
     * @return
     */
    private WebElement getParentCheckboxByLabel(String checkboxLabel) {
        return getDriver().findElement(By.xpath(String.format(locatorParentCheckboxByLabel, checkboxLabel)));
    }

    /**
     * Get locators(webelements) all checkboxes by Organization
     * @param organizationLabel
     * @return
     */
    private List<WebElement> getListOfAllCheckboxesByOranization(String organizationLabel) {
        return getDriver().findElements(By.xpath(String.format(allCheckboxesByOranization, organizationLabel)));
    }

//========================== Check states layers =================================


    /**
     * Check: Is control panel opened by waiting control panel layer representation
     * @return = true or false
     */
    private boolean isControlPanelOpened() {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            return locatorOpenedControlPanelLayer.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    /**
     * Check: Is filter opened by waiting filter layer representation
     * @return = true or false
     */
    private boolean isFilterOpened() {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            return locatorOpenedFilterLayer.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

//========================== Actions with layers =================================


    /**
     * Open control panel by click on Save button with check control panel layer representation
     */
    public void openControlPanel() {
        controlPanelExpandCollapseButton.click();
        Assert.assertTrue("Control panel was not opened", isControlPanelOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Save and close control panel by click on Save button
     */
    //TODO: "page-is-fully-loaded" element waiting should be implemented
    public void saveControlPanelSelection() {
        btnSaveControlPanel.click();
        Assert.assertTrue("Control panel was not closed", !isControlPanelOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Close control panel by click on Cancel button
     */
    public void cancelControlPanelSelection() {
        btnCancelControlPanel.click();
        Assert.assertTrue("Control panel was not closed", !isControlPanelOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Save filter selection by click on Enter button
     */
    public void saveFilterSelection() {
        btnEnterFilter.click();
        Assert.assertTrue("Filter was not closed", !isFilterOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

    }

    /**
     * Close filter by click on Cancel button
     */
    public void cancelFilterSelection() {
        btnCancelFilter.click();
        Assert.assertTrue("Filter was not closed", !isFilterOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Open groups filter with check filter layer representation
     */
    public void openGroupsFilter() {
        groupsFilter.click();
        Assert.assertTrue("Groups filter was not opened", isFilterOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Open platform filter with check filter layer representation
     */
    public void openPlatformsFilter() {
        platformsFilter.click();
        Assert.assertTrue("Platforms filter was not opened", isFilterOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Open publication status filter with check filter layer representation
     */
    public void openPubStatusFilter() {
        pubStatusFilter.click();
        Assert.assertTrue("Publication status filter was not opened", isFilterOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Open format filter with check filter layer representation
     */
    public void openFormatsFilter() {
        formatsFilter.click();
        Assert.assertTrue("Formats filter was not opened", isFilterOpened());
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

//======================== Actions with plus icons ===============================


    /**
     * Expand all plus icons
     */
    public void expandAllPlusIcons() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        while (!allPlusIcons.isEmpty()) {
            try {
                js.executeScript("arguments[0].click();", plusIcon);
            } catch (NoSuchElementException e) {
            }
        }
    }

    /**
     * Collapse all plus icons
     */
    public void collapseAllPlusIcons() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        while (!allMinusIcons.isEmpty()) {
            try {
                js.executeScript("arguments[0].click();", minusIcon);
            } catch (NoSuchElementException e) {
            }
        }
    }

//==========================  Check states checkboxes ============================


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
     * Check: Is parent checkbox checked?
     * @param checkboxLabel  = checkbox label
     * @return true or false
     */
    private boolean isParentCheckboxChecked(String checkboxLabel) {
        if ((getParentCheckboxByLabel(checkboxLabel).getAttribute("class")).contains("checked")) {
            return true;
        } else {
            return false;
        }
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

    /**
     * Check: Is state of all checkboxes in filter layer unchecked?
     * @return true or false
     */
    private boolean isAllCheckboxesUnchecked(){
        int countOfUncheckedCheckboxes = 0;
        for (int i = 0; i < allFilterCheckboxes.size(); i++) {
            if (!isCheckboxChecked(allFilterCheckboxes.get(i))) {
                countOfUncheckedCheckboxes++;
            }
        }
        if (countOfUncheckedCheckboxes == allFilterCheckboxes.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check: Is state of all childs checkboxes checked?
     * @param checkboxLabel
     * @return = true or false
     */
    private boolean isAllChildsCheckboxesChecked(String checkboxLabel) {
        int countOfCheckedCheckboxes = 0;
        for (int i = 0; i < getListOfAllChildsCheckboxesByParentLabel(checkboxLabel).size(); i++) {
            if (isCheckboxChecked(getListOfAllChildsCheckboxesByParentLabel(checkboxLabel).get(i))) {
                countOfCheckedCheckboxes++;
            }
        }
        if (countOfCheckedCheckboxes == getListOfAllChildsCheckboxesByParentLabel(checkboxLabel).size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check: Is state of all childs checkboxes unchecked?
     * @param checkboxLabel
     * @return = true or false
     */
    private boolean isAllChildsCheckboxesUnchecked(String checkboxLabel) {
        int countOfUncheckedCheckboxes = 0;
        for (int i = 0; i < getListOfAllChildsCheckboxesByParentLabel(checkboxLabel).size(); i++) {
            if (!isCheckboxChecked(getListOfAllChildsCheckboxesByParentLabel(checkboxLabel).get(i))) {
                countOfUncheckedCheckboxes++;
            }
        }
        if (countOfUncheckedCheckboxes == getListOfAllChildsCheckboxesByParentLabel(checkboxLabel).size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check: Is checkbox has any child checkboxes?
     * @param checkboxLabel = checkbox name
     * @return = true or false
     */
    private boolean isChildCheckboxForParentPresent(String checkboxLabel) {
        if (getListOfAllChildsCheckboxesByParentLabel(checkboxLabel).size() == 0) return false;
        else return true;
    }

    /**
     * Check: Is state of all checkboxes by Organization in filter layer checked?
     * @param organizationLabel = name Organization
     * @return true or false
     */
    private boolean isAllCheckboxesByOrganizationChecked(String organizationLabel){
        int countOfCheckedCheckboxes = 0;
        for (int i = 0; i < getListOfAllCheckboxesByOranization(organizationLabel).size(); i++) {
            if (isCheckboxChecked(getListOfAllCheckboxesByOranization(organizationLabel).get(i))) {
                countOfCheckedCheckboxes++;
            }
        }
        if (countOfCheckedCheckboxes == getListOfAllCheckboxesByOranization(organizationLabel).size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check: Is state of all checkboxes by Organization in filter layer unchecked?
     * @param organizationLabel = name Organization
     * @return true or false
     */
    private boolean isAllCheckboxesByOrganizationUnchecked(String organizationLabel){
        int countOfUncheckedCheckboxes = 0;
        for (int i = 0; i < getListOfAllCheckboxesByOranization(organizationLabel).size(); i++) {
            if (!isCheckboxChecked(getListOfAllCheckboxesByOranization(organizationLabel).get(i))) {
                countOfUncheckedCheckboxes++;
            }
        }
        if (countOfUncheckedCheckboxes == getListOfAllCheckboxesByOranization(organizationLabel).size()) {
            return true;
        } else {
            return false;
        }
    }

//=========================== Actions with checkboxes ============================


    /**
     * Uncheck global checkbox(first parent) with all child
     */
    public void uncheckGlobalCheckboxWithAllChild() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        if (!isCheckboxChecked(parentCheckbox)) {
            js.executeScript("arguments[0].click();", parentCheckbox);

        }
        while (isCheckboxChecked(parentCheckbox)) {
            js.executeScript("arguments[0].click();",parentCheckbox);
        }
        Assert.assertTrue("All child chekboxes are not unchecked.", isAllCheckboxesUnchecked());
    }

    /**
     * Check global checkbox(first parent) with all child
     */
    public void checkGlobalCheckboxWithAllChild(String checkboxLabel) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        while (isCheckboxChecked(parentCheckbox)) {
            js.executeScript("arguments[0].click();", parentCheckbox);
        }
        js.executeScript("arguments[0].click();", parentCheckbox);
        Assert.assertTrue("All child chekboxes are not checked.", isAllCheckboxesChecked());

    }

    /**
     * Check several checkboxes
     * @param checkboxesLabels = names of checkboxes
     */
    public void checkSeveralCheckboxes (String[] checkboxesLabels) {
        ArrayList<String> expectedCheckboxesLabels = new ArrayList<String>(Arrays.asList(checkboxesLabels));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        for (int i=0; i<expectedCheckboxesLabels.size();i++){
            js.executeScript("arguments[0].click();", getLocatorCheckboxByLabel(expectedCheckboxesLabels.get(i)));
        }
    }

    /**
     * Check checkbox with all child (if present)
     * @param checkboxLabel = name checkbox to check
     */
    public void checkCheckboxWithAllChild(String checkboxLabel) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        while (isCheckboxChecked(getLocatorCheckboxByLabel(checkboxLabel))) {
            js.executeScript("arguments[0].click();", getLocatorCheckboxByLabel(checkboxLabel));
        }
        js.executeScript("arguments[0].click();", getLocatorCheckboxByLabel(checkboxLabel));
        Assert.assertTrue("Checkbox is not checked.", isCheckboxChecked(getLocatorCheckboxByLabel(checkboxLabel)));
        if (isChildCheckboxForParentPresent(checkboxLabel)) {
            Assert.assertTrue("Child checkboxes are not checked.", isAllChildsCheckboxesChecked(checkboxLabel));
        }
    }

    /**
     * Check checkboxes with all child (if present)
     * @param checkboxesLabels = names checkboxes to check
     */
    public void checkSeveralCheckboxesWithAllChild (String[] checkboxesLabels) {
        ArrayList<String> expectedCheckboxesLabels = new ArrayList<String>(Arrays.asList(checkboxesLabels));
        for (int i=0; i<expectedCheckboxesLabels.size();i++){
            checkCheckboxWithAllChild(expectedCheckboxesLabels.get(i));
        }
    }


    /**
     * Check checkbox without child
     * @param checkboxLabel = name checkbox to check
     */
    public void checkParentCheckboxWithoutChild(String checkboxLabel) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        while (isCheckboxChecked(getLocatorCheckboxByLabel(checkboxLabel))) {
            js.executeScript("arguments[0].click();", getLocatorCheckboxByLabel(checkboxLabel));
        }
        js.executeScript("arguments[0].click();", getLocatorCheckboxByLabel(checkboxLabel));
        if (isCheckboxChecked(getLocatorCheckboxByLabel(checkboxLabel)) && isChildCheckboxForParentPresent(checkboxLabel)) {
            js.executeScript("arguments[0].click();", getLocatorCheckboxByLabel(checkboxLabel));
        }
        Assert.assertTrue("Expected: parent checkbox is checked, Actual: " + isCheckboxChecked(getLocatorCheckboxByLabel(checkboxLabel)) + "\n" +
                        "Expected: child checkboxes are unchecked, Actual: " + isAllChildsCheckboxesUnchecked(checkboxLabel),
                (isCheckboxChecked(getLocatorCheckboxByLabel(checkboxLabel)) && (isAllChildsCheckboxesUnchecked(checkboxLabel))));
    }

    /**
     * Check checkboxes without child
     * @param checkboxesLabels = names checkboxes to check
     */
    public void checkSeveralParentCheckboxWithoutChild (String[] checkboxesLabels) {
        ArrayList<String> expectedCheckboxesLabels = new ArrayList<String>(Arrays.asList(checkboxesLabels));
        for (int i=0; i<expectedCheckboxesLabels.size();i++){
            checkParentCheckboxWithoutChild(expectedCheckboxesLabels.get(i));
        }
    }

//================== Actions with checkboxes by Organization =====================


    /**
     * Check checkbox with all child by Organization
     * @param checkboxLabel = name of checkbox
     * @param organizationLabel = name of Organization
     */
    public void checkCheckboxWithAllChildByOrganization(String checkboxLabel, String organizationLabel) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        while (isCheckboxChecked(getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel))) {
            js.executeScript("arguments[0].click();", (getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel)));
        }
        js.executeScript("arguments[0].click();", (getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel)));
        Assert.assertTrue("Checkbox is not checked!", isCheckboxChecked((getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel))));
        if (isChildCheckboxForParentPresent(checkboxLabel)) {
            Assert.assertTrue("All child chekboxes are not checked", isAllChildsCheckboxesChecked(checkboxLabel));
        }
    }

    /**
     * Check several checkboxes with all child by Organization
     * @param checkboxesLabels = names of checkboxes
     * @param organizationLabel = name of Organization
     */
    public void checkSeveralCheckboxesWithAllChildByOrganization (String[] checkboxesLabels, String organizationLabel) {
        ArrayList<String> expectedCheckboxesLabels = new ArrayList<String>(Arrays.asList(checkboxesLabels));
        for (int i=0; i<expectedCheckboxesLabels.size();i++){
            checkCheckboxWithAllChildByOrganization(expectedCheckboxesLabels.get(i),organizationLabel);
        }
    }

    /**
     * Check parent checkbox without child by Organization
     * @param checkboxLabel = name of checkbox
     * @param organizationLabel = name of Organization
     */
    public void checkParentCheckboxWithoutChildByOrganization (String checkboxLabel, String organizationLabel) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        while (isCheckboxChecked(getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel))) {
            js.executeScript("arguments[0].click();", (getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel)));
        }
        js.executeScript("arguments[0].click();", (getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel)));
        if (isCheckboxChecked(getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel)) && isChildCheckboxForParentPresent(checkboxLabel)) {
            js.executeScript("arguments[0].click();", (getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel)));
        }
        Assert.assertTrue("Expected: parent checkbox is checked, Actual: " + isCheckboxChecked(getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel)) + "\n" +
                        "Expected: child checkboxes are unchecked, Actual: " + isAllChildsCheckboxesUnchecked(checkboxLabel),
                (isCheckboxChecked(getLocatorCheckboxByLabelForOrganization(checkboxLabel, organizationLabel)) && (isAllChildsCheckboxesUnchecked(checkboxLabel))));
    }

    /**
     * Check several parent checkboxes without child by Organization
     * @param checkboxesLabels = names of checkboxes
     * @param organizationLabel = name of Organization
     */
    public void checkSeveralParentCheckboxesWithoutChildByOrganization(String[] checkboxesLabels, String organizationLabel)  {
        ArrayList<String> expectedCheckboxesLabels = new ArrayList<String>(Arrays.asList(checkboxesLabels));
        for (int i=0; i<expectedCheckboxesLabels.size();i++){
            checkParentCheckboxWithoutChildByOrganization (expectedCheckboxesLabels.get(i),organizationLabel);
        }
    }

    /**
     * Check all checkboxes by Organization
     * @param organizationLabel = name of Organization
     */
    public void checkAllCheckboxesByOrganization (String organizationLabel) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        while (isCheckboxChecked(getListOfAllCheckboxesByOranization(organizationLabel).get(0))) {
            js.executeScript("arguments[0].click();", getListOfAllCheckboxesByOranization(organizationLabel).get(0));
        }
        js.executeScript("arguments[0].click();", getListOfAllCheckboxesByOranization(organizationLabel).get(0));
        Assert.assertTrue("All child checkboxes are not checked", isAllCheckboxesByOrganizationChecked(organizationLabel));
    }

    /**
     * Uncheck all checkboxes by Organization
     * @param organizationLabel = name of Organization
     */
    public void uncheckAllCheckboxesByOrganization(String organizationLabel) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        if (!isCheckboxChecked(getListOfAllCheckboxesByOranization(organizationLabel).get(0))) {
            js.executeScript("arguments[0].click();", getListOfAllCheckboxesByOranization(organizationLabel).get(0));
        }
        while (isCheckboxChecked(getListOfAllCheckboxesByOranization(organizationLabel).get(0))) {
            js.executeScript("arguments[0].click();",getListOfAllCheckboxesByOranization(organizationLabel).get(0));
        }
        Assert.assertTrue("All child's chekboxes are not unchecked!", isAllCheckboxesByOrganizationUnchecked(organizationLabel));
    }

/*================================= Set values ===================================
  ================================ GroupsFilter ==================================*/


    /**
     * Open filter - > Uncheck all checheckboxes -> Save
     */
    public void resetGroupsFilter() {
        openGroupsFilter();
        uncheckGlobalCheckboxWithAllChild();
        saveFilterSelection();
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child -> Save
     * @param checkboxesLabels = names of checkboxes
     */
    public void setGroupsFilterValues(String... checkboxesLabels) {
        setGroupsFilterValuesWithoutSave(checkboxesLabels);
        saveFilterSelection();
    }

    /** TODO: List with checkboxes for selection from bottom to the top?
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child or without child -> Save
     * @param checkboxesLabels = names of checkboxes to check with child
     * @param checkboxesLabelsToCheckWithoutCheckChild = names of checkboxes to check without child
     */
    public void setGroupsFilterValues(String[] checkboxesLabels, String[] checkboxesLabelsToCheckWithoutCheckChild) {
        setGroupsFilterValuesWithoutSave(checkboxesLabels);
        if (checkboxesLabelsToCheckWithoutCheckChild != null) {
            checkSeveralParentCheckboxWithoutChild(checkboxesLabelsToCheckWithoutCheckChild);
        }
        saveFilterSelection();
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child by Organization -> Save
     * @param checkboxesLabels = names of checkboxes
     * @param organizationLabel = name of Organization
     */
    public void setGroupsFilterValues(String[] checkboxesLabels, String organizationLabel) {
        setGroupsFilterValuesByOrganization(checkboxesLabels, organizationLabel);
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child or without child by Organization -> Save
     * @param checkboxesLabels =  names of checkboxes to check with child
     * @param checkboxesLabelsToCheckWithoutCheckChild = names of checkboxes to check without child
     * @param organizationLabel = name of Organization
     */
    public void setGroupsFilterValues(String[] checkboxesLabels, String[] checkboxesLabelsToCheckWithoutCheckChild, String organizationLabel) {
        setGroupsFilterValuesByOrganization(checkboxesLabels, checkboxesLabelsToCheckWithoutCheckChild, organizationLabel);
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child
     * @param checkboxesLabels = names of checkboxes
     */
    private void setGroupsFilterValuesWithoutSave(String[] checkboxesLabels) {
        openGroupsFilter();
        expandAllPlusIcons();
        uncheckGlobalCheckboxWithAllChild();
        if (checkboxesLabels != null) {
            checkSeveralCheckboxesWithAllChild(checkboxesLabels);
        }
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child by Organization
     * @param checkboxesLabels = names of checkboxes
     * @param organizationLabel = names of Organization
     */
    private void setGroupsFilterValuesByOrganizationWithoutSave(String[] checkboxesLabels, String organizationLabel) {
        openGroupsFilter();
        expandAllPlusIcons();
        uncheckAllCheckboxesByOrganization(organizationLabel);
        if (checkboxesLabels != null) {
            checkSeveralCheckboxesWithAllChildByOrganization(checkboxesLabels, organizationLabel);
        }
    }

    /**
     * Uncheck all checkboxes and select needed checkboxes with all child by Organization -> Save
     * @param checkboxesLabels
     * @param organizationLabel
     */
    private void setGroupsFilterValuesByOrganization(String[] checkboxesLabels, String organizationLabel) {
        setGroupsFilterValuesByOrganizationWithoutSave(checkboxesLabels, organizationLabel);
        saveFilterSelection();
    }

    /**
     * Uncheck all checkboxes and select needed checkboxes with all child or without child by Organization -> Save
     * @param checkboxesLabels
     * @param checkboxesLabelsToCheckWithoutCheckChild
     * @param organizationLabel
     */
    private void setGroupsFilterValuesByOrganization(String[] checkboxesLabels, String[] checkboxesLabelsToCheckWithoutCheckChild, String organizationLabel) {
        setGroupsFilterValuesByOrganizationWithoutSave(checkboxesLabels, organizationLabel);
        if (checkboxesLabelsToCheckWithoutCheckChild != null) {
            checkSeveralParentCheckboxesWithoutChildByOrganization(checkboxesLabelsToCheckWithoutCheckChild, organizationLabel);
        }
        saveFilterSelection();
    }


/*================================= Set values ===================================
  =============================== PlatformsFilter ================================*/


    /**
     * Open filter - > Uncheck all checheckboxes -> Save
     */
    public void resetPlatformsFilter() {
        openPlatformsFilter();
        uncheckGlobalCheckboxWithAllChild();
        saveFilterSelection();
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child -> Save
     * @param checkboxesLabels = names of checkboxes
     */
    public void setPlatformsFilterValues(String... checkboxesLabels) {
        setPlatformsFilterValuesWithoutSave(checkboxesLabels);
        saveFilterSelection();
    }

    /** TODO: List with checkboxes for selection from bottom to the top? Remove categories duplication from sample data
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child or without child -> Save
     * @param checkboxesLabels = names of checkboxes to check with child
     * @param checkboxesLabelsToCheckWithoutCheckChild = names of checkboxes to check without child
     */
    public void setPlatformsFilterValues(String[] checkboxesLabels, String[] checkboxesLabelsToCheckWithoutCheckChild) {
        setPlatformsFilterValuesWithoutSave(checkboxesLabels);
        if (checkboxesLabelsToCheckWithoutCheckChild != null) {
            checkSeveralParentCheckboxWithoutChild(checkboxesLabelsToCheckWithoutCheckChild);
        }
        saveFilterSelection();
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child by Organization -> Save
     * @param checkboxesLabels = names of checkboxes
     * @param organizationLabel = name of Organization
     */
    public void setPlatformsFilterValues(String[] checkboxesLabels, String organizationLabel) {
        setPlatformsFilterValuesByOrganization(checkboxesLabels, organizationLabel);
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child or without child by Organization -> Save
     * @param checkboxesLabels =  names of checkboxes to check with child
     * @param checkboxesLabelsToCheckWithoutCheckChild = names of checkboxes to check without child
     * @param organizationLabel = name of Organization
     *
     *  Usage example (values should have correct order from TOP to BOTTOM):
     *  mobileCalendarPage.setPlatformsFilterValues(new String[]{"mus"},new String[]{"sum"}, "QA");
     *  mobileCalendarPage.setPlatformsFilterValues(new String[]{},new String[]{"mus","mus1"}, "QA");
     */
    public void setPlatformsFilterValues(String[] checkboxesLabels, String[] checkboxesLabelsToCheckWithoutCheckChild, String organizationLabel) {
        setPlatformsFilterValuesByOrganization(checkboxesLabels, checkboxesLabelsToCheckWithoutCheckChild, organizationLabel);
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child
     * @param checkboxesLabels = names of checkboxes
     */
    private void setPlatformsFilterValuesWithoutSave(String[] checkboxesLabels) {
        openPlatformsFilter();
        expandAllPlusIcons();
        uncheckGlobalCheckboxWithAllChild();
        if (checkboxesLabels != null) {
            checkSeveralCheckboxesWithAllChild(checkboxesLabels);
        }
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes with all child by Organization
     * @param checkboxesLabels = names of checkboxes
     * @param organizationLabel = names of Organization
     */
    private void setPlatformsFilterValuesByOrganizationWithoutSave(String[] checkboxesLabels, String organizationLabel) {
        openPlatformsFilter();
        expandAllPlusIcons();
        uncheckAllCheckboxesByOrganization(organizationLabel);
        if (checkboxesLabels != null) {
            checkSeveralCheckboxesWithAllChildByOrganization(checkboxesLabels, organizationLabel);
        }
    }

    /**
     * Uncheck all checkboxes and select needed checkboxes with all child by Organization -> Save
     * @param checkboxesLabels
     * @param organizationLabel
     */
    private void setPlatformsFilterValuesByOrganization(String[] checkboxesLabels, String organizationLabel) {
        setPlatformsFilterValuesByOrganizationWithoutSave(checkboxesLabels, organizationLabel);
        saveFilterSelection();
    }

    /**
     * Uncheck all checkboxes and select needed checkboxes with all child or without child by Organization -> Save
     * @param checkboxesLabels
     * @param checkboxesLabelsToCheckWithoutCheckChild
     * @param organizationLabel
     */
    private void setPlatformsFilterValuesByOrganization(String[] checkboxesLabels, String[] checkboxesLabelsToCheckWithoutCheckChild, String organizationLabel) {
        setPlatformsFilterValuesByOrganizationWithoutSave(checkboxesLabels, organizationLabel);
        if (checkboxesLabelsToCheckWithoutCheckChild != null) {
            checkSeveralParentCheckboxesWithoutChildByOrganization(checkboxesLabelsToCheckWithoutCheckChild, organizationLabel);
        }
        saveFilterSelection();
    }

/*================================= Set values ===================================
  =============================== StatusesFilter =================================*/


    /**
     * Open filter - > Uncheck all checkboxes -> Save
     */
    public void resetPubStatusesFilter() {
        openPubStatusFilter();
        uncheckGlobalCheckboxWithAllChild();
        saveFilterSelection();
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes -> Save
     * @param checkboxesLabels = names of checkboxes
     */
    public void setPubStatusesFilterValues(String... checkboxesLabels) {
        openPubStatusFilter();
        uncheckGlobalCheckboxWithAllChild();
        expandAllPlusIcons();
        if (checkboxesLabels != null) {
            checkSeveralCheckboxesWithAllChild(checkboxesLabels);
        }
        saveFilterSelection();
    }

/*================================= Set values ===================================
  =============================== FormatsFilter ==================================*/

    /**
     * Open filter - > Uncheck all checkboxes -> Save
     */
    public void resetFormatsFilter() {
        openFormatsFilter();
        uncheckGlobalCheckboxWithAllChild();
        saveFilterSelection();
    }

    /**
     * Open filter - > Uncheck all checkboxes and select needed checkboxes -> Save
     * @param checkboxesLabels = names of checkboxes
     */
    public void setFormatsFilterValues (String... checkboxesLabels){
        openFormatsFilter();
        uncheckGlobalCheckboxWithAllChild();
        expandAllPlusIcons();
        if (checkboxesLabels != null) {
            checkSeveralCheckboxesWithAllChild(checkboxesLabels);
        }
        saveFilterSelection();
    }

}



