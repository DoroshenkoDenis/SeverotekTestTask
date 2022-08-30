package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
            WebElement catalogButton = driver.findElement(catalogBtnSelector);
            catalogButton.click();
        } catch (TimeoutException e) {
            throw new Error("WebDriver could not locate the element " + e);
        }
    }

}
