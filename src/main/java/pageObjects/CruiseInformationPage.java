package pageObjects;

import interactions.UserActions;
import interactions.Waits;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;


public class CruiseInformationPage extends PageModel {

    WebDriver driver;
    UserActions userActions;

    private String
            learnMore = "Learn More",
            learnMoreInfo = "Learn More Info";

    public CruiseInformationPage(WebDriver driver) {
        this.driver = driver;
        userActions = new UserActions(driver);
    }

    /**
     * Enum that contains all the dynamic xpath of the web Elements into the current page, at it returns the respective
     * xpath using the "name" of the element.
     */
    private enum EnumWebElement {
        INPUT_NOT_FOUND("not found", "WebElement not found"),
        ROAD_MAP("Road Map", "//route-map[@class = 'cruise-map roundtrip']//div"),
        LIST_BUTTON_LEARN_MORE("Learn More", "//div[@class = 'tile']//button"),
        LEARN_MORE_INFO("Learn More Info", "//div[@class= 'slide ccl-negative-outline slick-slide slick-current slick-active']"),
        BUTTON_BOOKING("Book Now", "//li[@id = 'sm-booking-btn']/booking-button/div");

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
            return Stream.of(CruiseInformationPage.EnumWebElement.values())
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
        return CruiseInformationPage.EnumWebElement.getXpathByButtonName(name);
    }

    /**
     * It use the name of the element to generate a WebElement using the xpath that the enum returns.
     *
     * @param name
     * @return A WebElement
     */
    public WebElement getWebElementByName(String name) {
        return userActions.findElementByXpath(CruiseInformationPage.EnumWebElement.getXpathByButtonName(name));
    }

    /**
     * Verifies if the information about learn more is displayed
     */
    public void theInformationIsDisplayed() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        getWebElementByName(learnMore).click();
        Waits.waitUltilIsDisplayedxpathShort(getWebElementXpathByName(learnMoreInfo), driver);
        Assert.assertTrue(getWebElementByName(learnMoreInfo).isDisplayed());

    }

    /**
     * verifies if an specific element is displayed
     *
     * @param button
     */
    public void theElementIsDisplayed(String button) {
        Assert.assertTrue(getWebElementByName(button).isDisplayed());
    }

    @Override
    public void waitForElement(String element) {
        Waits.waitUltilIsDisplayedXpath(getWebElementXpathByName(element), driver);
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
}
