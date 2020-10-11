package Tasks;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.CarnivalHomePage;
import pageObjects.ResultPage;
import util.JsonReader;

public class SmokeTestingTasks {
    WebDriver driver;
    /**
     * The identifiers of the element into the Enums an into the dataDrive.json
     */
    private String
            sailTo = "Sail To",
            sailFrom = "Sail From",
            charleston = "Charleston",
            dates = "Date",
            duration = "Duration",
            grid = "Grid",
            min = "Min",
            max = "Max";

    private CarnivalHomePage carnivalHomepage;
    private ResultPage resultPage;

    /**
     * The constructor of the class
     *
     * @param driver
     */
    public SmokeTestingTasks(WebDriver driver) {
        this.driver = driver;
        carnivalHomepage = new CarnivalHomePage(driver);
        resultPage = new ResultPage(driver);
    }

    /**
     * Navigates towards the page that receive as parameter an click on the body of the DOM to get rid of the initial
     * pop-up
     *
     * @param jsonObject
     * @param page
     */
    public void getInto(JSONObject jsonObject, String page) {
        driver.get(JsonReader.getValueFromPackage(jsonObject, page));
        driver.findElement(By.id("MainBody")).click();
        driver.get(driver.getCurrentUrl());
        carnivalHomepage.waitForElement(sailTo);
    }

    /**
     * Enters all the needed information to start a new search for a cruise taking the information from dataDrive.json
     *
     * @param jsonObject
     */
    public void enterTheSearchInformation(JSONObject jsonObject) {
        carnivalHomepage.selectValueFrom(sailTo, JsonReader.getValueFromPackage(jsonObject, sailTo));
        carnivalHomepage.selectValueFrom(sailFrom, JsonReader.getValueFromPackage(jsonObject, sailFrom));
        carnivalHomepage.clickOn(JsonReader.getValueFromPackage(jsonObject, charleston));
        carnivalHomepage.selectValueFrom(dates, JsonReader.getValueFromPackage(jsonObject, dates));
        carnivalHomepage.clickOn("122021");
        carnivalHomepage.selectValueFrom(duration, JsonReader.getValueFromPackage(jsonObject, duration));
    }

    /**
     * Verifies that the prices of the cruises have changed between the values selected on the slider bar.
     */
    public void theResultsChangeIntoTheRange() {
        resultPage.theResultsChangeIntoTheRange();
    }

    /**
     * Clicks on a button identified with the String that received as parameter.
     *
     * @param button
     */
    public void clickOn(String button) {
        carnivalHomepage.clickOn(button);
    }

    /**
     * Verify that the user have left the the page that is received as a parameter.
     *
     * @param page
     */
    public void theUserIsRedirected(String page) {
        carnivalHomepage.theUserIsRedirectedFrom(page);
    }

    /**
     * It makes the driver to map up the URL to get all the elements after a redirection has happen
     */
    public void goToCurrentPage() {
        driver.get(driver.getCurrentUrl());
    }

    /**
     * It waits until the page fully charge the result.
     */
    public void waitForTheResultToBeShown() {
        resultPage.waitForElement(grid);
    }

    /**
     * Verify that after the result are shown, these are being displayed as a grid
     */
    public void theResultsAreShownAsAGrid() {
        resultPage.theResultsAreShownAsAGrid();
    }

    /**
     * It moves the Slider Bar towards certain price defined into the dataDrive.json
     *
     * @param jsonObject
     */
    public void theUserFilterUsingTheSlideBar(JSONObject jsonObject) {
        resultPage.theUserFilterUsingTheSlideBar(JsonReader.getValueFromPackage(jsonObject, min), JsonReader.getValueFromPackage(jsonObject, max));
    }
}