package sber.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;


public class DriverFactory {

    private static final String BROWSER_PROPERTY_NAME = "browserName";
    private static final String CHROME_BROWSER_NAME = "chrome";
    private static final String FIREFOX_BROWSER_NAME = "firefox";
    private static final String EDGE_BROWSER_NAME = "edge";
    private static final String REMOTE_BROWSER_NAME = "remote";

    public static WebDriver createDriver() {
        System.out.println("Имя браузера: " + System.getProperty(BROWSER_PROPERTY_NAME));
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

            case REMOTE_BROWSER_NAME:
                WebDriverManager.chromedriver().setup();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("browserVersion", "109.0");
                capabilities.setCapability("enableVNC",true);
                capabilities.setCapability("enableVideo",true);
                try {
                    return new RemoteWebDriver(
                            URI.create("http://149.154.71.152:8080/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

            default:
                throw new RuntimeException("Incorrect browser name in config file");
        }
    }
}