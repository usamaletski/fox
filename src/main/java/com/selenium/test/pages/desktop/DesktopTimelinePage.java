package com.selenium.test.pages.desktop;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.pages.desktop.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.selenium.test.utils.TimeUtils.waitForSeconds;

/**
 * Mobile Timeline Page object
 */
public class DesktopTimelinePage extends BasePage {

    private static final String PAGE_URL = "timelinePage.htm";
    private static DesktopTimelinePage dTimeline;
    //    TODO: optimize duplicate values
    //EN
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_ENG = "No entries to be displayed. Remove the filter and/or add stories or events to this issue.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_ENG = "No entries to be displayed. Remove the filter and/or add stories or events to this topic.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_ENG = "No entries matching your criteria. Add a task";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_ENG = "No entries matching your criteria. Add a task";
    //DE
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_DE = "Keine Einträge vorhanden. Entfernen Sie den Filter und/oder geben Sie Themen oder Termine für die Ausgabe ein.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_DE = "Keine Einträge. Entfernen Sie ggf. die Filter und/oder geben Sie ein Thema oder einen Termin für dieses Oberthema ein.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_DE = "Es sind keine Aufgaben für diese Filtereinstellungen vorhanden. Aufgabe hinzufügen";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_DE = "Es sind keine Aufgaben für diese Filtereinstellungen vorhanden. Aufgabe hinzufügen";
    //FR
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_FR = "Pas de saisies disponibles. Supprimez le filtre et/ou saisissez des contenus ou des rendez-vous pour cette édition.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_FR = "Pas de saisies. Supprimez les filtres et/ou un contenu ou un rendez-vous pour cet contenu général";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_FR = "Il y'a aucune des tâches. Ajouter une tâche";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_FR = "Il y'a aucune des tâches. Ajouter une tâche";
    //ES
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_ES = "No hay entradas de datos disponibles. Eliminar el filtro y/o añadir historias o eventos para esta edición.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_ES = "No hay entradas de datos para mostrar. Eliminar el filtro y/o añadir historias o eventos para este tema.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_ES = "No hay tareas que coincidan con los criterios de filtro. Añadir una tarea";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_ES = "No hay tareas que coincidan con los criterios de filtro. Añadir una tarea";

    //EN WITHOUT GROUP ASSIGNMENTS
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_WO_GROUP_ENG = "No platform available. Ask an admin to enter a platform in the admin area of Desk-Net.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_WO_GROUP_ENG = "No topic has been entered. Go to the Topics page and enter a topic.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_WO_GROUP_ENG = "No entries matching your criteria. Add a task";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_WO_GROUP_ENG = "No entries matching your criteria. Add a task";
    //DE WITHOUT GROUP ASSIGNMENTS
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_WO_GROUP_DE = "Kein Publikationskanal vorhanden. Bitten Sie einen Admin, einen Publikationskanal im Admin-Bereich anzulegen.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_WO_GROUP_DE = "Kein Oberthema vorhanden. Gehen Sie auf die Oberthemen-Seite und geben Sie ein Oberthema ein.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_WO_GROUP_DE = "Es sind keine Aufgaben für diese Filtereinstellungen vorhanden. Aufgabe hinzufügen";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_WO_GROUP_DE = "Es sind keine Aufgaben für diese Filtereinstellungen vorhanden. Aufgabe hinzufügen";
    //FR WITHOUT GROUP ASSIGNMENTS
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_WO_GROUP_FR = "Pas de canal de publication disponible Demandez à un admin de créer un canal de publication pour le domaine Administration.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_WO_GROUP_FR = "Pas de contenus général disponible. Rendez-vous à la page des contenus généraux et saisissez un contenus général.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_WO_GROUP_FR = "Il y'a aucune des tâches. Ajouter une tâche";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_WO_GROUP_FR = "Il y'a aucune des tâches. Ajouter une tâche";
    //ES WITHOUT GROUP ASSIGNMENTS
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_WO_GROUP_ES = "Plataforma no disponible.Pide a un Administradorintroducir una plataforma en el área de administración (Admin) de Desk-Net.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_TOPIC_MODE_WO_GROUP_ES = "No se ha introducido ningún tema. Ir a la página de Temas para introducir un tema.";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_DEADLINE_WO_GROUP_ES = "No hay tareas que coincidan con los criterios de filtro. Añadir una tarea";
    public static String MESSAGE_NO_CONTENT_TIMELINE_ASSIGNMENTS_MODE_BY_MODIFICATION_WO_GROUP_ES = "No hay tareas que coincidan con los criterios de filtro. Añadir una tarea";

    //    TODO: NO CONTENT HINT for admin without platforms
    public static String MESSAGE_NO_CONTENT_TIMELINE_PLATFORM_MODE_ADMIN_WO_PLATFORM_EN = "No platform available. Go to the admin area and add a platform.";


    @FindBy(xpath = "//span[@class='controlHeader' and (text()='Timeline' or text()='Zeitleiste' or text()='Chronologie' or text()='Cronograma')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@id='page-is-fully-loaded' and @value='true']")
    private WebElement locatorPageFullyLoaded;

    @FindBy(xpath = "//input[@id='page-is-fully-loaded' and @value='false']")
    private WebElement locatorPageNotFullyLoaded;

    @FindBy(xpath = "//div[contains(@class,'no-items-panel')]")
    private WebElement noContentPanel;

    @FindBys(@FindBy(xpath = "//div[contains(@class,'no-items-panel')]/div/*"))
    private List<WebElement> noContentPanelText;

    @FindBys(@FindBy(xpath = "//div[contains(@class,'timeline-panel')]"))
    private List<WebElement> elementsBorder;

    @FindBys(@FindBy(xpath = "//td[@class='appointment-cell']"))
    private List<WebElement> elementsAppointmentsDateTime;

    @FindBys(@FindBy(xpath = "//td[@class='timeSpace']/preceding-sibling::td/div[@class='contentText actual-date' or @class='contentText']"))
    private List<WebElement> elementsAppointmentsDate;

    @FindBys(@FindBy(xpath = "//td[@class='timeSpace']"))
    private List<WebElement> elementsAppointmentsTime;

    @FindBy(id = "logoutLink")
    private WebElement buttonLogout;

    @FindBy(xpath = " //a[@class='select-item']")
    private WebElement buttonCAB;

    @FindBys(@FindBy(xpath = "//td[@class='type-cell']"))
    private List<WebElement> elementsTypes;

    @FindBys(@FindBy(xpath = "//td[@class='title-cell']"))
    private List<WebElement> elementsTitles;

    @FindBys(@FindBy(xpath = "//td[@class='details-cell']"))
    private List<WebElement> elementsDetails;


    String locatorElementBorder = "//div[%1s][contains(@class,'timeline-panel')]%2s";
    String locatorElementDate = "//td[@class='timeSpace']/preceding-sibling::td/div[@class='contentText actual-date' or @class='contentText']";
    String locatorElementTime = "//td[@class='timeSpace']";
//    String locatorElementType = "//td[@class='type-cell']";
    String locatorElementType = "//td[@class='type-cell']/span";
    String locatorElementTitle = "//td[@class='title-cell']";

    String locatorElementDateFinal;
    String locatorElementTimeFinal;
    String locatorElementTypeFinal;
    String locatorElementTitleFinal;

    public static DesktopTimelinePage getInstance() throws Exception {
        if (dTimeline == null) dTimeline = new DesktopTimelinePage();
        return dTimeline;
    }

    public DesktopTimelinePage() throws Exception {
        super();
    }

    public DesktopTimelinePage(boolean openPageByUrl) throws Exception {
        super(openPageByUrl);
    }

    /**
     * Open Timeline page with default url for specified mobile.application.url
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
        getDriver().manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        locatorPageFullyLoaded.isDisplayed();
        try {
            return pageTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @param parameterURL additional part of URl (after ?) with specific data for control panel selection
     */
    public void setURLControlPanelValues(String parameterURL) {
        String appUrl = TestsConfig.getConfig().getDesktopAppUrl();
        getDriver().get(appUrl + PAGE_URL + "?" + parameterURL);
        isPageOpened();
    }


    /**
     * @return displayed state of no content hint
     */
    public boolean isNoContentPanelDisplayed() {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            return noContentPanel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @return text for no content hint
     */
    public String getNoContentText() {
        Assert.assertTrue("No content panel is not displayed", isNoContentPanelDisplayed());
        String noContentHintText = "";
        for (int i = 0; i < noContentPanelText.size(); i++) {
            noContentHintText += String.format("%s ", noContentPanelText.get(i).getText());
        }
        return noContentHintText.trim();
    }


    /**
     * Get [Date, Time, Type, Title] elements
     * @return = list of [Date, Time, Type, Title] elements
     */
    public List<String> getElementsValues() {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String resultDateTime;
        ArrayList<String> elementsValues = new ArrayList<String>();
        for (int i = 1; i <= elementsBorder.size(); i++) {
            resultDateTime = "";
            locatorElementDateFinal = String.format(locatorElementBorder, i, locatorElementDate);
            locatorElementTimeFinal = String.format(locatorElementBorder, i, locatorElementTime);
            locatorElementTypeFinal = String.format(locatorElementBorder, i, locatorElementType);
            locatorElementTitleFinal = String.format(locatorElementBorder, i, locatorElementTitle);
            for (int d = 0, t = 0; d < (getDriver().findElements(By.xpath(locatorElementDateFinal))).size() && t < (getDriver().findElements(By.xpath(locatorElementTimeFinal))).size(); d++, t++) {
                resultDateTime += getDriver().findElements(By.xpath(locatorElementDateFinal)).get(d).getText() + " " + getDriver().findElements(By.xpath(locatorElementTimeFinal)).get(t).getText() + " ";
            }
//            System.out.println((resultDateTime + getDriver().findElement(By.xpath(locatorElementTypeFinal)).getText() + " " + getDriver().findElement(By.xpath(locatorElementTitleFinal)).getText()).replaceAll(" +", " ").trim());
            try {
                elementsValues.add((resultDateTime + getDriver().findElement(By.xpath(locatorElementTypeFinal)).getText() + " " + getDriver().findElement(By.xpath(locatorElementTitleFinal)).getText()).replaceAll(" +", " ").trim());
            } catch (NoSuchElementException e) {
                elementsValues.add((resultDateTime + " " + getDriver().findElement(By.xpath(locatorElementTitleFinal)).getText()).replaceAll(" +", " ").trim());
            }
        }
        return elementsValues;
    }

    /**
     * Get all elements date
     * @return = all elements Dates
     */
    public List<String> getElementsAppointmentsDate() {
        ArrayList<String> titlesValues = new ArrayList<String>();
        for (int i = 0; i < elementsAppointmentsDate.size(); i++) {
            titlesValues.add(elementsAppointmentsDate.get(i).getText());
        }
        return titlesValues;
    }

    /**
     * Get all elements times
     * @return = all elements Times
     */
    public List<String> getElementsAppointmentsTime() {
        ArrayList<String> titlesValues = new ArrayList<String>();
        for (int i = 0; i < elementsAppointmentsTime.size(); i++) {
            titlesValues.add(elementsAppointmentsTime.get(i).getText());
        }
        return titlesValues;
    }

    /**
     * Get all elements types
     * @return = all elements Types
     */
    public List<String> getElementsType() {
        ArrayList<String> titlesValues = new ArrayList<String>();
        for (int i = 0; i < elementsTypes.size(); i++) {
            titlesValues.add(elementsTypes.get(i).getText());
        }
        return titlesValues;
    }

    /**
     * Get all elements titles
     * @return = all elements Titles
     */
    public List<String> getElementsTitles() {
        ArrayList<String> titlesValues = new ArrayList<String>();
        for (int i = 0; i < elementsTitles.size(); i++) {
            titlesValues.add(elementsTitles.get(i).getText());
        }
        return titlesValues;
    }


    /**
     * Get all elements details
     * @return = all elements Details
     */
    public List<String> getElementsDetails() {
        ArrayList<String> titlesValues = new ArrayList<String>();
        for (int i = 0; i < elementsDetails.size(); i++) {
            titlesValues.add(elementsDetails.get(i).getText());
            System.out.println(elementsDetails.get(i).getText());
        }
        return titlesValues;
    }

    /**
     * Check: Do elements times order equals?
     * @param expectedTimesOrder
     * @return
     */
    public boolean isTimeEquals(String[] expectedTimesOrder) {
        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedTimesOrder)); // conversion Array expectedTitles into List<String>
        if ((getElementsAppointmentsTime().equals(expectedElementsList))) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedElementsList);
            System.out.println("ACTUAL LIST:   " + getElementsAppointmentsTime());
            return false;
        }
    }

    /**
     * Check: Do elements dates order equals?
     * @param expectedDatesOrder
     * @return
     */
    public boolean isDateEquals(String[] expectedDatesOrder) {
        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedDatesOrder)); // conversion Array expectedTitles into List<String>
        if ((getElementsAppointmentsDate().equals(expectedElementsList))) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedElementsList);
            System.out.println("ACTUAL LIST:   " + getElementsAppointmentsDate());
            return false;
        }
    }

    /**
     * Check: Do elements types order equals?
     * @param expectedTypesOrder
     * @return
     */
    public boolean isTypeEquals(String[] expectedTypesOrder) {
        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedTypesOrder)); // conversion Array expectedTitles into List<String>
        if ((getElementsType().equals(expectedElementsList))) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedElementsList);
            System.out.println("ACTUAL LIST:   " + getElementsType());
            return false;
        }
    }

    /**
     * Check: Do elements titles order equals?
     * @param expectedTitlesOrder
     * @return
     */
    public boolean isTitleOrderEquals(String[] expectedTitlesOrder) {
        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedTitlesOrder)); // conversion Array expectedTitles into List<String>
        if ((getElementsTitles().equals(expectedElementsList))) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedElementsList);
            System.out.println("ACTUAL LIST:   " + getElementsTitles());
            return false;
        }
    }

    /**
     * Check: Does extraction contains titles?
     * @param expectedTitles
     * @return
     */
    public boolean isExtractionContainsTitles(String[] expectedTitles) {
        ArrayList<String> expectedTitlesList = new ArrayList<String>(Arrays.asList(expectedTitles)); // conversion Array expectedTitles into List<String>
        if (getElementsTitles().containsAll(expectedTitlesList)) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedTitlesList);
            System.out.println("ACTUAL LIST:   " + getElementsTitles());
            return false;
        }
    }

    /**
     * Check: Does titles equals?
     * @param expectedTitles
     * @return
     */
    public boolean isTitleEquals(String[] expectedTitles) {
        ArrayList<String> expectedTitlesList = new ArrayList<String>(Arrays.asList(expectedTitles)); // conversion Array expectedTitles into List<String>
        if ((getElementsTitles().containsAll(expectedTitlesList)) && (expectedTitlesList.size() == elementsBorder.size())) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedTitlesList);
            System.out.println("ACTUAL LIST:   " + getElementsTitles());
            return false;
        }
    }

    /**
     * Check: Does elements order equals?
     * @param expectedElementsOrder
     * @return
     */
    public boolean isElementsOrderEquals(String[] expectedElementsOrder) {
        for (int i=0; i < expectedElementsOrder.length;i++) {
            expectedElementsOrder[i] = expectedElementsOrder[i].replaceAll(" +", " ").trim();
        }
        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedElementsOrder)); // conversion Array expectedTitles into List<String>
        if ((getElementsValues().equals(expectedElementsList))) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedElementsList);
            System.out.println("ACTUAL LIST:   " + getElementsValues());
            return false;
        }
    }

    /**
     * Check: Does elements equals?
     * @param expectedElementsOrder
     * @return
     */
    public boolean isElementsEquals(String[] expectedElementsOrder) {
        for (int i=0; i < expectedElementsOrder.length;i++) {
            expectedElementsOrder[i] = expectedElementsOrder[i].replaceAll(" +", " ").trim();
        }
        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedElementsOrder)); // conversion Array expectedTitles into List<String>
        if ((getElementsValues().containsAll(expectedElementsList))&& (expectedElementsOrder.length == elementsBorder.size())) {
            return true;
        } else {

            System.out.println("EXPECTED LIST: " + expectedElementsList);
            System.out.println("ACTUAL LIST:   " + getElementsValues());
            return false;
        }
    }

// REVIEW IDEA with assertion
//    public void compareElementsOrder(String[] expectedValuesOrder) {
//        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedValuesOrder)); // conversion Array expectedTitles into List<String>
//        Assert.assertEquals("Elements are not equal", getElementsValues(), expectedElementsList);
//    }


    /**
     * Check: Does extraction contains elements?
     * @param expectedElements
     * @return
     */
    public boolean isExtractionContainsElements(String[] expectedElements) {
        for (int i=0; i < expectedElements.length;i++) {
            expectedElements[i] = expectedElements[i].replaceAll(" +", " ").trim();
        }
        ArrayList<String> expectedElementsList = new ArrayList<String>(Arrays.asList(expectedElements)); // conversion Array expectedTitles into List<String>
        if (getElementsValues().containsAll(expectedElementsList)) {
            return true;
        } else {
            System.out.println("EXPECTED LIST: " + expectedElementsList);
            System.out.println("ACTUAL LIST:   " + getElementsValues());
            return false;
        }
    }


//    ====================================== TEMP ============================================
    public void logOut() {
        buttonLogout.click();
    }

    String menuCAB = "//div[@class='add-button-popup']/div[@class='gwt-Label' and text()='Add Story']";
    @FindBy(xpath = "//div[@class='gwt-Label' and text()='Add Story']")
    private WebElement menuCAB01;

    public void addStoryUsingCAB() {
        String parentHandle = getDriver().getWindowHandle();
        buttonCAB.click();
        for(String childHandle : getDriver().getWindowHandles()){
            menuCAB01.click();
         }
        getDriver().switchTo().window(parentHandle);
        waitForSeconds(10);
    }


}