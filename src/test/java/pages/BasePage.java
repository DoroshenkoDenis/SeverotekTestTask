package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Stream;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * opens browser page with url
     *
     * @param url target url
     */
    public void open(String url) {
        driver.get(url);
    }

    /**
     * the method checks that the titles of the found products contain the brand name
     *
     * @param itemName name of search item
     * @return stream of logical true or false
     */
    public Stream<Boolean> checkSearchedItemsByName(String itemName) {
        List<WebElement> searchResult = driver.findElements(By.xpath("//div[@data-index]//h3/a"));
        return searchResult.stream()
                .map(webElement -> webElement.getAttribute("title")
                        .toLowerCase()
                        .contains(itemName.toLowerCase()));
    }

    /**
     * the method checks if the price of the found product matches the given parameters
     *
     * @param min minimum required price value
     * @param max maximum required price value
     * @return stream of logical true or false
     */
    public Stream<Boolean> checkSearchedItemsByPrice(int min, int max) {
        List<WebElement> searchResult = driver.findElements(By.xpath("//div[@data-zone-name = 'price']//span[@data-auto='mainPrice']/span[not(contains(text(), 'от'))][1]"));
        return searchResult.stream()
                .map(WebElement::getText)
                .map(e -> e.replace(" ", ""))
                .map(Integer::parseInt)
                .map(e -> e >= min && e <= max);
    }

}
