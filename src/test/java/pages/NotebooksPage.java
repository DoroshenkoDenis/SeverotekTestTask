package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotebooksPage extends BasePage {

    public NotebooksPage(WebDriver driver) {
        super(driver);
    }

    /**
     * brand selection in the product filter
     *
     * @param brand brand name of the product
     */
    public void selectBrand(String brand) {
        try {
            WebElement brandCheckBox = driver.findElement(By.xpath("//span[text() = '" + brand + "']/../.."));
            brandCheckBox.click();
        } catch (TimeoutException e) {
            throw new Error("WebDriver could not locate the element " + e);
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
            WebElement minPrice = driver.findElement(By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'от')]/../input"));
            minPrice.sendKeys(String.valueOf(min));
            WebElement maxPrice = driver.findElement(By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'до')]/../input"));
            maxPrice.sendKeys(String.valueOf(max));
        } catch (TimeoutException e) {
            throw new Error("WebDriver could not locate the element " + e);
        }
    }

}
