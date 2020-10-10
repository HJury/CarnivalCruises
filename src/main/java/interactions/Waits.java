package interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Waits {


    private Waits() {

    }

    public static void waitUltilIsDisplayed(WebElement element, WebDriver driver) {
        try {
            WebDriverWait wait;
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            wait.until(WebDriver::getCurrentUrl);
        } catch (TimeoutException e) {
            Exceptions.exceptionMessage(e);
        }

    }

    public static void waitUntilIsRedirectedTowards(String page, WebDriver driver) {
        try {
            WebDriverWait wait;
            ExpectedCondition e = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return (d.getCurrentUrl().equalsIgnoreCase(page));
                }
            };
            wait = new WebDriverWait(driver, 10);
            wait.until(e);
        } catch (TimeoutException e) {
            Exceptions.exceptionMessage(e);
        }
    }

    public static void waitUltilListGreaterThanZero(String elementList, WebDriver driver) {
        try {
            WebDriverWait wait;
            ExpectedCondition e = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return (driver.findElements(By.xpath(elementList)).size() > 0);
                }
            };
            wait = new WebDriverWait(driver, 15);
            wait.until(e);
        } catch (TimeoutException e) {
            Exceptions.exceptionMessage(e);
        }
    }

    public static void waitUltilIsDisplayedxpathShort(String xpath, WebDriver driver) {
        try {
            WebDriverWait wait;
            wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(xpath))));
        } catch (TimeoutException e) {
            System.out.println("The short wait ended and the element: " + xpath + " didn't get displayed");
        }
    }

    public static void waitUltilIsDisplayedXpath(String xpath, WebDriver driver) {
        try {
            WebDriverWait wait;
            wait = new WebDriverWait(driver, 6);
            wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(xpath))));
        } catch (TimeoutException e) {
            System.out.println("The short wait ended and the element: " + xpath + "  didn't get displayed");
        }
    }

    public static void waitUntilInformationInFieldIsDisplayed(String element, WebDriver driver) {
        try {
            WebDriverWait wait;
            ExpectedCondition e = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return (!driver.findElement(By.xpath(element)).getAttribute("Value").equals(""));
                }
            };
            wait = new WebDriverWait(driver, 5);
            wait.until(e);
        } catch (TimeoutException e) {
            System.out.println("The information of element: " + element + "wasn't displayed");
            Exceptions.exceptionMessage(e);
        }
    }

    public static void waitUntilIsInvisible(String xpath, WebDriver driver) {
        try {
            WebDriverWait wait;
            wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.xpath(xpath))));
        } catch (TimeoutException e) {
            Exceptions.exceptionMessage(e);
        }
    }

}
