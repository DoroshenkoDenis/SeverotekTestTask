package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    public static Logger logger = Logger.getLogger(BaseTest.class);
    protected HomePage homePage = new HomePage(driver);
    protected NotebooksPage notebooksPage = new NotebooksPage(driver);

    /**
     * driver manager automatically selects the driver for the required operating system and installed browser
     * driver sets browser window size to maximum and implicit wait for DOM elements
     */
    @BeforeAll
    static void setUp() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            //Set implicit wait:
//wait for WebElement
            int TIMEOUT = 20;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
//wait for loading page
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT));
//wait for an asynchronous script to finish execution
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(TIMEOUT));
        } catch (NullPointerException e) {
            logger.error("Driver could not found", e);
        }
    }

    /**
     * Close the current window, quitting the browser if it's the last window currently open.
     * Quits this driver, closing every associated window.
     */
    @AfterAll
    static void close() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    /**
     * scroll down to get a list of products from the first searched page
     */
    public void scrollDown() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
    }

    @BeforeEach
    public void runNotify() {
        logger.info("Test is running...");
    }

}