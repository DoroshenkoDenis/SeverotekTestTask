package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NotebooksPage extends HomePage {

    public NotebooksPage(WebDriver driver) {
        super(driver);
    }

    final private static Logger logger = Logger.getLogger(NotebooksPage.class);
    final private By lenovoBrand = By.xpath("//span[text() = 'Lenovo']/..");
    final private By minPrice = By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'от')]/../input");
    final private By maxPrice = By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'до')]/../input");

    /**
     * brand selection in the product filter
     *
     * @param brand brand name of the product
     */
    public NotebooksPage selectBrand(String brand) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> Selected brand: " + brand);
        switch (brand) {
            case "Lenovo" -> {
                addLoggerXPathInfo(lenovoBrand);
                try {
                    driver.findElement(lenovoBrand)
                            .click();
                } catch (NoSuchElementException e) {
                    logger.error("Element could not found", e);
                }
            }
            case "AnyBrand" -> System.out.println("Add code");
            default -> logger.error("Brand not found");
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
            driver.findElement(minPrice)
                    .sendKeys(String.valueOf(min));
            driver.findElement(maxPrice)
                    .sendKeys(String.valueOf(max));
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
        return this;
    }

}
