package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        WebElement item = driver.findElement(By.xpath("//a[text() = '" + itemName + "']"));
        item.click();
    }

}
