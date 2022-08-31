package pages;

import org.openqa.selenium.*;

public class YandexMarketHome extends BasePage {
    public YandexMarketHome(WebDriver driver) {
        super(driver);
    }

    private final By catalogBtnSelector = By.id("catalogPopupButton");

    /**
     * method of opening a catalog on the main page of the Market
     */
    public void openCatalog() {
        try {
            driver.findElement(catalogBtnSelector)
                    .click();
        } catch (NoSuchElementException e) {
            throw new Error("WebDriver could not locate the element: " + e);
        }
    }

}
