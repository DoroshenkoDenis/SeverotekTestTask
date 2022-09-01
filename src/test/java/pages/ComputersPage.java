package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ComputersPage extends BasePage {
    public ComputersPage(WebDriver driver) {
        super(driver);
    }

    final private static Logger logger = Logger.getLogger(ComputersPage.class);
    private final String itemName = "Ноутбуки";
    final private By item = By.xpath("//a[text() = '" + itemName + "']");

    /**
     * method for selecting an item from a list
     */
    public void selectNotebooks() {
        addLoggerXPathInfo(item);
        try {
            driver.findElement(item)
                    .click();
        } catch (NoSuchElementException e) {
            logger.error("Element could not found", e);
        }
    }

}
