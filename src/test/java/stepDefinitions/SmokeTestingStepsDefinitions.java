package stepDefinitions;

import Tasks.SmokeTestingTasks;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.JSONObject;
import util.JsonReader;

public class SmokeTestingStepsDefinitions {
    private String carnival = "Carnival Cruise";
    private JSONObject jsonObjectValidInfo;
    private SmokeTestingTasks smoketestingTasks;

    @Given("^The user gets into \"(.*?)\" page$")
    public void theUserGetsIntoCarnivalCruisePage(String page) {
        jsonObjectValidInfo = JsonReader.getInfoPackage("Valid Information");
        smoketestingTasks = new SmokeTestingTasks(Hook.driver);
        smoketestingTasks.getInto(jsonObjectValidInfo, page);
    }

    @When("The user enters the needed information")
    public void theUserEntersTheNeededInformation() {
        smoketestingTasks.enterTheSearchInformation(jsonObjectValidInfo);
    }

    @When("Click on \"(.*?)\" button")
    public void clickOnSearchCruisesButton(String button) {
        smoketestingTasks.clickOn(button);
    }

    //<editor-fold desc="User Story #1">
    @Given("The user is redirected to the result page")
    public void theUserIsRedirectedToTheResultPage() {
        smoketestingTasks.theUserIsRedirected(JsonReader.getValueFromPackage(jsonObjectValidInfo, carnival));
        smoketestingTasks.goToCurrentPage();
    }

    @When("The user visualizes the results")
    public void theUserVisualizesTheResults() {
        smoketestingTasks.waitForTheResultToBeShown();
    }

    @Then("These are shown as a grid.")
    public void theseAreShownAsAGrid() {
        smoketestingTasks.theResultsAreShownAsAGrid();
    }

    @Given("The result are already shown")
    public void theResultAreAlreadyShown() {
        smoketestingTasks.waitForTheResultToBeShown();
        smoketestingTasks.theResultsAreShownAsAGrid();
    }

    @Given("The user filters by price using the Slide Bar")
    public void theUserFiltersByPriceUsingTheSlideBar() {
        smoketestingTasks.theUserFilterUsingTheSlideBar(jsonObjectValidInfo);
    }

    @Then("The results change into the range.")
    public void theResultsChangeIntoTheRange() {
        smoketestingTasks.theResultsChangeIntoTheRange();
    }

    @When("The user clicks on \"(.*?)\" button")
    public void theUserClicksOnButton(String button) {
        smoketestingTasks.clickOn(button);
    }

    @Then("The default value will be the cheapest first.")
    public void theDefaultValueWillBeTheCheapestFirst() {
    }
    //</editor-fold>

    //<editor-fold desc="User Story#2">
    @Given("The user selects one of the cruises of the result set")
    public void theUserSelectsOneOfTheCruiseOfTheResultSet() {
    }

    @Then("^The user is redirected towards the \"(.*?)\" page$")
    public void theUserIsRedirectedTowardsTheItineraryPage(String page) {

    }

    @When("The user clicks on \"Learn More\" button of each day")
    public void theUserClickOnLearnMoreButtonOfADay() {

    }

    @Then("The information of that day is displayed.")
    public void theInformationOfThatDayIsDisplayed() {

    }

    @Then("An \"(.*?)\" button is  displayed.")
    public void anInformationOfThatDayIsDisplayed(String button) {

    }
    //</editor-fold>

}
