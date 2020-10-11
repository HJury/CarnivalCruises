package pageObjects;

import interactions.UserActions;
import interactions.Waits;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class CarnivalHomePage extends PageModel {

    WebDriver driver;
    UserActions userActions;

    private String sails = "Sails";

    public CarnivalHomePage(WebDriver driver) {
        this.driver = driver;
        userActions = new UserActions(driver);
    }

    /**
     * Enum that contains all the dynamic xpath of the web Elements into the current page, at it returns the respective
     * xpath using the "name" of the element.
     */
    private enum EnumWebElement {
        INPUT_NOT_FOUND("not found", "WebElement not found"),
        BUTTON_SAIL_TO("Sail To", "//a[@id = 'cdc-destinations']"),
        BUTTON_SAIL_FROM("Sail From", "//a[@id = 'cdc-ports']"),
        BUTTON_DATES("Date", "//a[@id = 'cdc-dates']"),
        BUTTON_DURATION("Duration", "//a[@id = 'cdc-durations']"),
        COLLECTION_SAILS("Sails", "//div[contains(@class, 'cdc-filter-body')]"),
        BUTTON_SEARCH_CRUISES("SEARCH CRUISES", "//li//a[@class= 'cdc-filters-search-cta']"),
        BUTTON_6_9("6 - 9 Days", "//button[@aria-label= '6 - 9 Days ']"),
        BUTTON_JAN_2021("012021", "//button[@data-value = '012021']"),
        BUTTON_DEC_2021("122021", "//button[@data-value = '122021']"),
        BUTTON_THE_BAHAMAS("The Bahamas", "//button[@aria-label= 'The Bahamas ']"),
        BUTTON_THE_BALTIMORE("Baltimore, MD", "//button[contains(@aria-label, 'Baltimore, MD')]"),
        BUTTON_THE_CHARLESTON("Charleston, MD", "//button[contains(@aria-label, 'Charleston, SC')]");


        private String name, xpath;

        EnumWebElement(String name, String xpath) {
            this.name = name;
            this.xpath = xpath;
        }

        private String getXpath() {
            return this.xpath;
        }

        private String getName() {
            return this.name;
        }

        static private String getXpathByButtonName(String name) {
            return Stream.of(EnumWebElement.values())
                    .filter(x -> x.getName().equals(name))
                    .findFirst()
                    .orElse(INPUT_NOT_FOUND)
                    .getXpath();
        }
    }

    /**
     * It uses the name of the element to get the xpath of the webelement
     *
     * @param name
     * @return
     */
    public String getWebElementXpathByName(String name) {
        return EnumWebElement.getXpathByButtonName(name);
    }

    /**
     * It use the name of the element to generate a WebElement using the xpath that the enum returns.
     *
     * @param name
     * @return A WebElement
     */
    public WebElement getWebElementByName(String name) {
        return userActions.findElementByXpath(EnumWebElement.getXpathByButtonName(name));
    }

    /**
     * It select a value from the each of the field needed to search for cruise using identifiers to click it.
     *
     * @param element
     * @param value
     */
    public void selectValueFrom(String element, String value) {
        getWebElementByName(element).click();
        Waits.waitUltilIsDisplayedxpathShort(getWebElementXpathByName(sails), driver);
        getWebElementByName(value).click();
    }

    @Override
    public void clickOn(String button) {
        getWebElementByName(button).click();
    }

    @Override
    public void theUserIsRedirectedFrom(String page) {
        Assert.assertTrue("The user was not redirected towards a new page and stay at: " + page
                , userActions.theUserIsRedirectedFrom(page));
    }

    @Override
    public void waitForElement(String element) {
        Waits.waitUltilIsDisplayedXpath(getWebElementXpathByName(element), driver);
    }
}
