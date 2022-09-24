package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public interface StableElementSearch {
    /**
     * нереализоанный метод, возвращает драйвер после реализации
     *
     * @return драйвер
     * @Override public WebDriver setDriver() {
     * return this.driver;
     * }
     */
    WebDriver setDriver();

    /**
     * Явный поиск элемента по условию и локатору
     *
     * @param condition - условие ожидания
     * @param <V>       - возвращаемый тип (List<WebElement>, WebElement)
     * @return элемент
     */
    default <V> V explicitSearch(Function<? super WebDriver, V> condition) {
        // отключено неявное ожидание
        setDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        // включено явное ожидание с переданным условием
        V element = new WebDriverWait(setDriver(), Duration.ofSeconds(30)).until(condition);
        // включено неявное ожидание
        setDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return element;
    }

    default WebElement searchElementByXpath(By xPath) {
        return explicitSearch(visibilityOfElementLocated(xPath));
    }

    default WebElement searchElementByXpath(String xPath) {
        return explicitSearch(visibilityOfElementLocated(By.xpath(xPath)));
    }

    default List<WebElement> searchElementsByXpath(By xPath) {
        searchElementByXpath(xPath);
        return setDriver().findElements(xPath);
    }

    default List<WebElement> searchElementsByXpath(String xPath) {
        searchElementByXpath(xPath);
        return setDriver().findElements(By.xpath(xPath));
    }

}
