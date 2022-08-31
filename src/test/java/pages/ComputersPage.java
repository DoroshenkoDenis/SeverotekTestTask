package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ComputersPage extends BasePage {
    public ComputersPage(WebDriver driver) {
        super(driver);
    }

    /**
     * method for selecting an item from a list
     *
     * @param itemName item name
     */
    public void selectItem(String itemName) {
        try {
            driver.findElement(By.xpath("//a[text() = '" + itemName + "']"))
                    .click();
        } catch (NoSuchElementException e) {
            throw new Error("WebDriver could not locate the element: " + e);
        }
    }

}
