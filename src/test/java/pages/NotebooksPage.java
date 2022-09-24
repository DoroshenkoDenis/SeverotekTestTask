package pages;

import helpers.StableElementSearch;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotebooksPage implements StableElementSearch {

    private final WebDriver driver;
    private final static Logger logger = Logger.getLogger(NotebooksPage.class);

    private final By minPrice = By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'от')]/../input");
    private final By maxPrice = By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'до')]/../input");
    private final By showResultsBtnSelector = By.id("showButton");
    private final By prices = By.xpath("//div[@data-zone-name = 'price']//span[@data-auto='mainPrice']/span[not(contains(text(), 'от'))][1]");

    public final String BRAND_CHECK_BOX = "//span[text() = '%s']/..";
    public final String TITLES = "//div[@data-index]//h3/a";

    public NotebooksPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * brand selection in the product filter
     *
     * @param brand brand name of the product
     */
    public NotebooksPage selectBrand(String brand) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Selected brand: " + brand);
        try {
            searchElementByXpath(String.format(BRAND_CHECK_BOX, brand)).click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
        return this;
    }

    /**
     * the method sets the price range in the product filter
     *
     * @param min min minimum required price value
     * @param max max maximum required price value
     */
    public NotebooksPage setPrice(int min, int max) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Selected price from " + min + " to " + max);
        try {
            searchElementByXpath(minPrice).sendKeys(String.valueOf(min));
            searchElementByXpath(maxPrice).sendKeys(String.valueOf(max));
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
        return this;
    }

    /**
     * the method applies the filtering conditions by clicking on the apply button
     */
    public void applyFilter() {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Find Elements: " + showResultsBtnSelector);
        try {
            driver.findElement(showResultsBtnSelector).click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
    }

    /**
     * Возвращает карту (пары ключ-значение) с актуальными данными о товаре
     *
     * @return заполненная карта с товаром
     */
    public Map<String, Integer> createHashmapFromTitlesAndPrices() {
        return new HashMap<>() {{
            List<WebElement> titles = getSearchedTitles();
            List<WebElement> prices = getSearchedPrices();
            for (int i = 0; i < titles.size(); i++) {
                put(
                        titles.get(i).getAttribute("title").toLowerCase(),
                        Integer.valueOf(prices.get(i).getText().replace(" ", ""))
                );
            }
        }};
    }

    /**
     * the method gets the name of the found products
     *
     * @return list of titles or an empty list if nothing matches
     */
    public List<WebElement> getSearchedTitles() {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Find Elements: " + TITLES);
        try {
            return searchElementsByXpath(TITLES);
        } catch (TimeoutException e) {
            logger.error("Elements could not found", e);
            return List.of();
        }
    }

    /**
     * the method gets the prices of the found products
     *
     * @return list of titles
     */
    public List<WebElement> getSearchedPrices() {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Find Elements: " + prices);
        try {
            return searchElementsByXpath(prices);
        } catch (TimeoutException e) {
            logger.error("Elements could not found", e);
            return List.of();
        }
    }

    @Override
    public WebDriver setDriver() {
        return this.driver;
    }

}
