package pageObjects;

abstract public class PageModel {
    /**
     * It click on an element that came from the parameter.
     *
     * @param button
     */
    abstract public void clickOn(String button);

    /**
     * Verifies that the user is being redirect from the page that received as parameter
     *
     * @param page
     */
    abstract public void theUserIsRedirectedFrom(String page);

    /**
     * It wait for a certain element to get displayed.
     *
     * @param element
     */
    abstract public void waitForElement(String element);
}
