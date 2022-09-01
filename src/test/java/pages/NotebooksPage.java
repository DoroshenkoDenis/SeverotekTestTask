package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NotebooksPage extends BasePage {

    public NotebooksPage(WebDriver driver) {
        super(driver);
    }

    final private static Logger logger = Logger.getLogger(CatalogPopup.class);
    final private By brandNameCheckBox = By.xpath("//span[text() = '" + brand + "']/..");

    /**
     * brand selection in the product filter
     *
     * @param brand brand name of the product
     */
    public void selectBrand(String brand) {
        addLoggerXPathInfo(brandNameCheckBox);
        try {
            driver.findElement(brandNameCheckBox)
                    .click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
    }

    /**
     * the method sets the price range in the product filter
     *
     * @param min min minimum required price value
     * @param max max maximum required price value
     */
    public void setPrice(int min, int max) {
        try {
            driver.findElement(By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'от')]/../input"))
                    .sendKeys(String.valueOf(min));
            driver.findElement(By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'до')]/../input"))
                    .sendKeys(String.valueOf(max));
        } catch (NoSuchElementException e) {
            throw new Error("WebDriver could not locate the element: " + e);
        }
    }

}
