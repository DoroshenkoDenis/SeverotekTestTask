package pages;

import org.openqa.selenium.By;
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
        WebElement category = driver.findElement(By.xpath("//span[text() = '" + categoryName + "']/.."));
        category.click();
    }
}
