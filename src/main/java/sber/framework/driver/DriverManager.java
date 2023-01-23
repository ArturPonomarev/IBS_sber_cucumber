package sber.framework.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static DriverManager driverProvider;
    private WebDriver driver;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        return driverProvider == null ? driverProvider = new DriverManager() : driverProvider;
    }

    public WebDriver getDriver() {
        return driver == null ? driver = DriverFactory.createDriver() : driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
