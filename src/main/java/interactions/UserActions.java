package interactions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserActions {
    WebDriver driver;

    public UserActions(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(String text, WebElement element) {
        element.sendKeys(text);
    }

    public WebElement findElementByXpath(String xpath) {
        return this.driver.findElement(By.xpath(xpath));
    }

    public List<WebElement> findElementsByXpath(String xpath) {
        return this.driver.findElements(By.xpath(xpath));
    }

    public boolean isThisPageRedirectedTowards(String page) {
        Waits.waitUntilIsRedirectedTowards(page, driver);
        return driver.getCurrentUrl().equalsIgnoreCase(page);
    }

    public boolean isThisElementDisplayed(WebElement element) {
        if (element.isDisplayed())
            return true;
        return false;
    }

    public boolean isTheValueOfThisElementEqualsToByAttribute(WebElement element, String value, String attribute) {
        if (element.getAttribute(attribute).equals(value))
            return true;
        return false;
    }


}
