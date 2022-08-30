package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
            WebElement category = driver.findElement(By.xpath("//span[text() = '" + categoryName + "']/.."));
            category.click();
        } catch (
                TimeoutException e) {
            throw new Error("WebDriver could not locate the element " + e);
        }
    }
}
