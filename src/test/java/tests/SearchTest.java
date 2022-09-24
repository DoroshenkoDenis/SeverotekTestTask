package tests;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.WebElement;

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
    public void checkSearchedItemsByBrandTest(String brand, int min, int max, String category, String item) throws InterruptedException {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + brand + " from " + min + " to " + max);

        homePage.open(URL)
                .openCatalog()
                .selectItemByHoverToCategory(category, item);
        notebooksPage.setPrice(min, max)
                .selectBrand(brand)
        // метод применяющий условия фильтрации нажатием на кнопку (которой нет в UI, но есть по требованиям)
        // ожно закомментить, чтобы пропустить неявное ожидание в 10 секунд; ошибка обработана, тест продолжится
        .applyFilter();
        scrollDown();


        /* **************************************
         *************** USING MAP **************
         ***************************************/

//        boolean isBrandNameContain = true;
//        boolean isCostInRange = true;
//        if (notebooksPage.createHashmapFromTitlesAndPrices().size() > 0) {
//            for (String title : notebooksPage.createHashmapFromTitlesAndPrices().keySet()) {
//                if (!title.contains(brand.toLowerCase())) {
//                    isBrandNameContain = false;
//                    break;
//                }
//            }
//            for (int cost : notebooksPage.createHashmapFromTitlesAndPrices().values()) {
//                if (cost < min || cost > max) {
//                    isCostInRange = false;
//                    break;
//                }
//            }
//        } else {
//            isBrandNameContain = false;
//            isCostInRange = false;
//        }
//
//        logger.info("Make sure the search results show the brand name and the price is in the range");
//        Assertions.assertTrue(isBrandNameContain);
//        Assertions.assertTrue(isCostInRange);


        /* **************************************
         *************** USING STREAM ***********
         ***************************************/

        boolean isBrand = false;
        if (notebooksPage.getSearchedTitles().size() > 0) {
            isBrand = notebooksPage.getSearchedTitles()
                    .parallelStream()
                    .allMatch(webElement -> webElement.getAttribute("title").toLowerCase().contains((brand).toLowerCase()));
        }

        boolean isInRange = false;
        if (notebooksPage.getSearchedPrices().size() > 0) {
            isInRange = notebooksPage.getSearchedPrices()
                    .parallelStream()
                    .map(WebElement::getText)
                    .map(e -> e.replace(" ", ""))
                    .map(Integer::parseInt)
                    .allMatch(cost -> cost >= min && cost <= max);
        }

        logger.info("Make sure the search results show the brand name and the price is in the range");
        Assertions.assertTrue(isBrand);
        Assertions.assertTrue(isInRange);

    }
}
