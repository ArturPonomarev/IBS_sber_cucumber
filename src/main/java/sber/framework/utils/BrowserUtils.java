package sber.framework.utils;

import sber.framework.driver.DriverManager;

public class BrowserUtils {
    public static void enterToFrame(String frameId) {
        DriverManager.getInstance().getDriver().switchTo().frame(frameId);
    }

    public static void leaveAllFrames() {
        DriverManager.getInstance().getDriver().switchTo().defaultContent();
    }
}
