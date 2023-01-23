package sber.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import sber.framework.driver.DriverManager;
import sber.framework.utils.FrameworkUtils;
import sber.framework.utils.JsonDataProvider;

public abstract class BaseTest {

    protected WebDriver driver = DriverManager.getInstance().getDriver();

    @BeforeAll
    public static void oneTimeSetUp() {
        FrameworkUtils.initFramework();
    }

    @BeforeEach
    public void setUp() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.navigate().to(JsonDataProvider.testData.baseUrl);
    }

    @AfterEach
    public void tearDown() {
        //FrameworkUtils.closeFramework();
    }
}