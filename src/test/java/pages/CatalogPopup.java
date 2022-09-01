package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CatalogPopup extends BasePage {

    public CatalogPopup(WebDriver driver) {
        super(driver);
    }

    final private static Logger logger = Logger.getLogger(CatalogPopup.class);
    private final String categoryName = "Компьютеры";
    final private By category = By.xpath("//span[text() = '" + categoryName + "']/..");

    /**
     * product category selection method
     */
    public void openComputers() {
        addLoggerXPathInfo(category);
        try {
            driver.findElement(category)
                    .click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
    }

}
