package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

public class YandexMarketHome extends BasePage {
    public YandexMarketHome(WebDriver driver) {
        super(driver);
    }

    final private static Logger logger = Logger.getLogger(YandexMarketHome.class);

    private final By catalogBtnSelector = By.id("catalogPopupButton");

    /**
     * method of opening a catalog on the main page of the Market
     */
    public void openCatalog() {
        addLoggerXPathInfo(catalogBtnSelector);
        try {
            driver.findElement(catalogBtnSelector)
                    .click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
    }

}
