package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class HomePage {

    protected WebDriver driver;
    final private static Logger logger = Logger.getLogger(HomePage.class);
    final private By titles = By.xpath("//div[@data-index]//h3/a");
    final private By prices = By.xpath("//div[@data-zone-name = 'price']//span[@data-auto='mainPrice']/span[not(contains(text(), 'от'))][1]");
    private final By catalogBtnSelector = By.id("catalogPopupButton");
    private final By showResultsBtnSelector = By.id("showButton");
    private final String categoryName = "Компьютеры";
    final private By category = By.xpath("//span[text() = '" + categoryName + "']/..");
    private final String itemName = "Ноутбуки";
    final private By item = By.xpath("//a[text() = '" + itemName + "']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * opens browser page with url
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
            System.exit(0);
        }
        return this;
    }

    /**
     * the method checks that the titles of the found products contain the brand name
     *
     * @param brand name of search item
     * @return stream of logical true or false
     */
    public Stream<Boolean> checkSearchedItemsByBrand(String brand) {
        addLoggerXPathInfo(titles);
        try {
            List<WebElement> searchResult = driver.findElements(titles);
            if (searchResult.stream().anyMatch(WebElement::isEnabled)) {
                return searchResult.stream().map(webElement -> webElement.getAttribute("title").toLowerCase().contains(brand.toLowerCase()));
            }
            return Stream.generate(() -> Boolean.FALSE).limit(1);
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
            driver.quit();
        }
        return null;
    }

    /**
     * the method checks if the price of the found product matches the given parameters
     *
     * @param min minimum required price value
     * @param max maximum required price value
     * @return stream of logical true or false
     */
    public Stream<Boolean> checkSearchedItemsByPrice(int min, int max) {
        addLoggerXPathInfo(prices);
        try {
            List<WebElement> searchResult = driver.findElements(prices);
            if (searchResult.stream().anyMatch(WebElement::isEnabled)) {
                return searchResult.stream()
                        .map(WebElement::getText)
                        .map(e -> e.replace(" ", ""))
                        .map(Integer::parseInt)
                        .map(e -> e >= min && e <= max);
            }
            return Stream.generate(() -> Boolean.FALSE).limit(1);
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
            driver.quit();
        }
        return null;
    }

    /**
     * method of opening a catalog on the main page of the Market
     */
    public HomePage openCatalog() {
        addLoggerXPathInfo(catalogBtnSelector);
        try {
            driver.findElement(catalogBtnSelector)
                    .click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
        return this;
    }

    /**
     * method for selecting an item from a list
     */
    public void selectItemByHoverToCategory() {
        try {
            addLoggerXPathInfo(category);
            new Actions(driver).moveToElement(driver.findElement(category)).pause(Duration.ofSeconds(5)).build().perform();
            addLoggerXPathInfo(item);
            driver.findElement(item).click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
    }

    /**
     * the method applies the filtering conditions by clicking on the apply button
     */
    public void applyFilter() {
        addLoggerXPathInfo(showResultsBtnSelector);
        try {
            driver.findElement(showResultsBtnSelector).click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
    }

    /**
     * the method adds logs bout xPaths
     */
    public void addLoggerXPathInfo(By xpath) {
        logger.info(Thread.currentThread().getStackTrace()[2].getMethodName() + " -> Find Elements: " + xpath);
    }

}
