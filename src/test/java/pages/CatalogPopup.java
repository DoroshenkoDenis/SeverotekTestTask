package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CatalogPopup extends BasePage {

    public CatalogPopup(WebDriver driver) {
        super(driver);
    }

    /**
     * product category selection method
     *
     * @param categoryName name of item category
     */
    public void openCategory(String categoryName) {
        try {
            driver.findElement(By.xpath("//span[text() = '" + categoryName + "']/.."))
                    .click();
        } catch (NoSuchElementException e) {
            throw new Error("WebDriver could not locate the element: " + e);
        }

    }
}
