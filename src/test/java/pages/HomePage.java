package pages;

import helpers.StableElementSearch;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HomePage implements StableElementSearch {

    private final WebDriver driver;
    private final static Logger logger = Logger.getLogger(HomePage.class);
    private final By catalogBtnSelector = By.id("catalogPopupButton");

    public final String CATEGORY = "//span[text() = '%s']/..";
    public final String ITEM = "//a[text() = '%s']";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * open browser page with url
     *
     * @param url target url
     */
    public HomePage open(String url) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Open page: " + url);
        try {
            driver.get(url);
        } catch (WebDriverException e) {
            logger.fatal("Server is not available. Check the URL or Internet connection", e);
            driver.quit();
        }
        return this;
    }

    /**
     * method of opening a catalog on the main page of the Market
     */
    public HomePage openCatalog() {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Find Element: " + catalogBtnSelector);
        try {
            searchElementByXpath(catalogBtnSelector).click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
        return this;
    }

    /**
     * method for selecting an item from a list using the category of products
     */
    public void selectItemByHoverToCategory(String categoryName, String itemName) {
        try {
            String categoryXpath = String.format(CATEGORY, categoryName);
            String itemXpath = String.format(ITEM, itemName);
            logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Find Element: " + By.xpath(categoryXpath));
            new Actions(this.driver).moveToElement(searchElementByXpath(categoryXpath)).perform();
            logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Find Element: " + By.xpath(itemXpath));
            searchElementByXpath(itemXpath).click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }

}