package tests;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class SearchTest extends BaseTest {
    private static final String URL = "https://market.yandex.ru/";
    final private static Logger logger = Logger.getLogger(SearchTest.class);

    /**
     * the test checks if the title of the product contains the name of its brand
     *
     * @param brand brand name of the product
     */
    @DisplayName("Check Searched Items By Brand")
    @ParameterizedTest(name = "{index} - {0} is a brand, price from {1} to {2}")
    @CsvFileSource(resources = {"/testData.csv"}, delimiter = ';')
    public void checkSearchedItemsByBrandTest(String brand, int min, int max) throws InterruptedException {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + brand + " from " + min + " to " + max);
        homePage.open(URL)
                .openCatalog()
                .openComputers();
        computersPage.selectNotebooks();
        notebooksPage.setPrice(min, max)
                .selectBrand(brand)
                .applyFilter();
        scrollDown();
        notebooksPage.checkSearchedItemsByBrand(brand)
                .forEach(Assertions::assertTrue);
    }

    /**
     * the test checks whether the found cost of goods is within the specified price range
     *
     * @param brand brand name of the product
     * @param min   min minimum required price value
     * @param max   max maximum required price value
     */
    @DisplayName("Check Searched Items By Price value")
    @ParameterizedTest(name = "{index} - {0} is a brand, price from {1} to {2}")
    @CsvFileSource(resources = {"/testData.csv"}, delimiter = ';')
    public void checkSearchedItemsByPriceTest(String brand, int min, int max) throws InterruptedException {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + brand + " from " + min + " to " + max);
        homePage.open(URL).openCatalog().openComputers();
        computersPage.selectNotebooks();
        notebooksPage.setPrice(min, max)
                .selectBrand(brand)
                .applyFilter();
        scrollDown();
        notebooksPage.checkSearchedItemsByPrice(min, max)
                .forEach(Assertions::assertTrue);
    }

}
