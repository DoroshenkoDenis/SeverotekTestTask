package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BasePage {
    protected WebDriver driver;
    final private static Logger logger = Logger.getLogger(BasePage.class);
    final private By titles = By.xpath("//div[@data-index]//h3/a");
    final private By prices = By.xpath("//div[@data-zone-name = 'price']//span[@data-auto='mainPrice']/span[not(contains(text(), 'от'))][1]");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * opens browser page with url
     *
     * @param url target url
     */
    public void open(String url) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Open page: " + url);
        try {
            driver.get(url);
        } catch (WebDriverException e) {
            logger.fatal("Server is not available. Check the URL or Internet connection", e);
            driver.quit();
            System.exit(0);
        }
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
            return searchResult.stream().map(webElement -> webElement.getAttribute("title").toLowerCase().contains(brand.toLowerCase()));
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
            return searchResult.stream()
                    .map(WebElement::getText)
                    .map(e -> e.replace(" ", ""))
                    .map(Integer::parseInt)
                    .map(e -> e >= min && e <= max);
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
            driver.quit();
        }
        return null;
    }

    /**
     * the method adds logs bout xPaths
     */
    public void addLoggerXPathInfo(By xpath) {
        logger.info(Thread.currentThread().getStackTrace()[2].getMethodName() + " -> Find Elements: " + xpath);
    }

}
