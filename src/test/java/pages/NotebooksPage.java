package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotebooksPage extends BasePage {

    public NotebooksPage(WebDriver driver) {
        super(driver);
    }

    /**
     * brand selection in the product filter
     * @param brand brand name of the product
     */
    public void selectBrand(String brand) {
        WebElement brandCheckBox = driver.findElement(By.xpath("//span[text() = '" + brand + "']/../.."));
        brandCheckBox.click();
    }

    /**
     * the method sets the price range in the product filter
     * @param min min minimum required price value
     * @param max max maximum required price value
     */
    public void setPrice(int min, int max) {
        WebElement minPrice = driver.findElement(By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'от')]/../input"));
        minPrice.sendKeys(String.valueOf(min));
        WebElement maxPrice = driver.findElement(By.xpath("//div[@data-filter-id = 'glprice']//label[contains(text(), 'до')]/../input"));
        maxPrice.sendKeys(String.valueOf(max));
    }

}
