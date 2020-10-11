package pageObjects;

import interactions.UserActions;
import interactions.Waits;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.stream.Stream;

public class ResultPage extends PageModel {


    WebDriver driver;
    UserActions userActions;

    private String
            pricing = "Pricing",
            slideBar = "SlideBar",
            grid = "Grid",
            sliderBarMin = "SliderBar Min",
            firstPrice = "First Price";

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        userActions = new UserActions(driver);
    }

    /**
     * Enum that contains all the dynamic xpath of the web Elements into the current page, at it returns the respective
     * xpath using the "name" of the element.
     */
    private enum EnumWebElement {
        INPUT_NOT_FOUND("not found", "WebElement not found"),
        GRID_OR_LIST("Grid", "//ccl-view-result-grid[@class = 'ng-scope ng-isolate-scope']"),
        BUTTON_PRICING("Pricing", "//a[@id = 'sfn-nav-pricing']"),
        BAR_SLIDE_BAR("SlideBar", "//a[@id = 'sfn-nav-pricing']//*[@class = 'sfn-title__chevron fa fa-chevron-down sfn-title__chevron--active']"),
        SLIDER_BAR_MIN("SliderBar Min", "//div[@class = 'rzslider ng-isolate-scope']//*[@ng-style = 'minPointerStyle']"),
        PRICE_OF_THE_FIRST_RESULT("First Price", "//ccl-view-result-grid[@class = 'ng-scope ng-isolate-scope']/article[1]//p[@class = 'vrgf-price-box__price-info']//*[@class = 'vrgf-price-box__price-value ng-binding']");

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
            return Stream.of(ResultPage.EnumWebElement.values())
                    .filter(x -> x.getName().equals(name))
                    .findFirst()
                    .orElse(INPUT_NOT_FOUND)
                    .getXpath();
        }
    }

    /**
     * It uses the name of the element to get the xpath of the webelement
     * @param name
     * @return
     */
    public String getWebElementXpathByName(String name) {
        return ResultPage.EnumWebElement.getXpathByButtonName(name);
    }

    /**
     * It use the name of the element to generate a WebElement using the xpath that the enum returns.
     * @param name
     * @return A WebElement
     */
    public WebElement getWebElementByName(String name) {
        return userActions.findElementByXpath(ResultPage.EnumWebElement.getXpathByButtonName(name));
    }

    /**
     * It moves the SliderBar one space each 1.2 sec so the page have time to reload the information.
     * @param min
     * @param max
     */
    public void theUserFilterUsingTheSlideBar(String min, String max) {
        getWebElementByName(pricing).click();
        Waits.waitUltilIsDisplayedxpathShort(getWebElementXpathByName(slideBar), driver);
        Waits.waitUltilIsDisplayedxpathShort(getWebElementXpathByName(sliderBarMin), driver);
        getWebElementByName(sliderBarMin).click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_RIGHT).pause(1200)
                .sendKeys(Keys.ARROW_RIGHT).pause(1200)
                .sendKeys(Keys.ARROW_RIGHT).pause(1200)
                .sendKeys(Keys.ARROW_RIGHT).pause(1200)
                .sendKeys(Keys.ARROW_RIGHT).pause(1200)
                .sendKeys(Keys.ARROW_RIGHT).pause(1200)
                .build()
                .perform();
    }

    /**
     * Verifies that the result are being shown as a grid
     */
    public void theResultsAreShownAsAGrid() {
        Assert.assertTrue(getWebElementByName(grid).getAttribute("ng-switch-when").equals("grid"));
    }

    /**
     * Verifies that the result set change when ever a change on the price filter is done.
     */
    public void theResultsChangeIntoTheRange() {
        int first = Integer.parseInt(getWebElementByName(firstPrice).getText());
        int priceSlider = Integer.parseInt(getWebElementByName(sliderBarMin).getAttribute("aria-valuenow"));
        Assert.assertTrue(first >= priceSlider);
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
