package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class SearchTest extends BaseTest {
    private static final String URL = "https://market.yandex.ru/";

    /**
     * the test checks if the title of the product contains the name of its brand
     *
     * @param brand brand name of the product
     */
    @DisplayName("Check Searched Items By Brand")
    @ParameterizedTest(name = "{index} - {0} is a brand")
    @ValueSource(strings = {"Lenovo", "Acer"})
    public void checkSearchedItemsByBrandTest(String brand) throws InterruptedException {
        yandexMarketHome.open(URL);
        yandexMarketHome.openCatalog();
        catalogPopup.openCategory("Компьютеры");
        computersPage.selectItem("Ноутбуки");
        notebooksPage.setPrice(20000, 30000);
        notebooksPage.selectBrand(brand);
        scrollDown();
        notebooksPage.checkSearchedItemsByName(brand)
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
    @ParameterizedTest
    @CsvSource({
            "Lenovo, 20000, 30000",
            "Acer, 25000, 60000"
    })
    public void checkSearchedItemsByPriceTest(String brand, int min, int max) throws InterruptedException {
        yandexMarketHome.open(URL);
        yandexMarketHome.openCatalog();
        catalogPopup.openCategory("Компьютеры");
        computersPage.selectItem("Ноутбуки");
        notebooksPage.setPrice(min, max);
        notebooksPage.selectBrand(brand);
        scrollDown();
        notebooksPage.checkSearchedItemsByPrice(min, max)
                .forEach(Assertions::assertTrue);
    }

}
