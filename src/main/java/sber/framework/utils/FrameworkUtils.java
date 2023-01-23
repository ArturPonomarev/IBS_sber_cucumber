package sber.framework.utils;

import sber.framework.driver.DriverManager;

import java.time.Duration;

public class FrameworkUtils {

    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(JsonDataProvider.configData.pageLoadTimeout));
        driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(JsonDataProvider.configData.elementWaitingTimeoutImplicit));
    }

    public static void closeFramework() {
        driverManager.quitDriver();
    }
}
