package sber.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static final String BROWSER_PROPERTY_NAME = "browserName";
    private static final String CHROME_BROWSER_NAME = "chrome";
    private static final String FIREFOX_BROWSER_NAME = "firefox";
    private static final String EDGE_BROWSER_NAME = "edge";

    public static WebDriver createDriver() {
        switch (System.getProperty(BROWSER_PROPERTY_NAME).toLowerCase()) {
            case CHROME_BROWSER_NAME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case FIREFOX_BROWSER_NAME:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case EDGE_BROWSER_NAME:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            default:
                throw new RuntimeException("Incorrect browser name in config file");
        }
    }
}