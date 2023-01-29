package sber.framework.forms;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sber.framework.utils.BrowserUtils;
import sber.framework.utils.StringUtils;

import java.util.List;


public class SecondaryMortgagePage extends BaseForm {

    private final int INSURANCE_CHECKBOX_DELTA = 10;
    private final String inputFieldByNameTemplate = "//*[contains(text(),'%s')]/preceding-sibling::input";
    private final String calculatedValueByNameTemplate = "//*[contains(text(),'%s')]/following-sibling::*//*[text()]";
    private final String calculatorFrameId = "iFrameResizer0";

    //TODO: Попытаться сокртить xpath
    @FindBy(xpath = "//*[contains(text(),'Страхование жизни и здоровья')]/..//input")
    private WebElement insuranceCheckBox;

    @FindBy(xpath = "//*[contains(@class,'switch-input')]")
    private WebElement checkBoxForUpdate;

    @Step("Вход в фрейм калькулятора")
    public void enterToCalculator() {
        BrowserUtils.enterToFrame(calculatorFrameId);
    }

    @Step("Выход из фрейма калькулятора")
    public void leaveCalculator() {
        BrowserUtils.leaveAllFrames();
    }

    @Step("Ввод значения: {fieldValue} в поле калькулятора: {fieldName}")
    public void inputCalculatorField(String fieldName, String fieldValue) {
        WebElement element = driver.findElement(By.xpath(String.format(inputFieldByNameTemplate,fieldName)));
        element.sendKeys(Keys.chord(Keys.CONTROL + "a"));
        element.sendKeys(Keys.DELETE);
        sendKeysByNumpads(element,fieldValue);
        waitUntilCreditValueUpdate();
    }

    @Step("Переключение чекбокса 'Страхование жизни и здоровья'")
    public void clickHealthInsuranceCheckbox() {

        Integer oldPercent = StringUtils.convertStringToInt(getCalculatedValueByName("Процентная ставка"));

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", insuranceCheckBox);

        wait.until(dr -> StringUtils.convertStringToInt(getCalculatedValueByName("Процентная ставка"))
                .equals(oldPercent + INSURANCE_CHECKBOX_DELTA));
    }

    @Step("Получение значения из поля {valueName}")
    public String getCalculatedValueByName(String valueName) {
        List<WebElement> elements = driver.findElements(By.xpath(String.format(calculatedValueByNameTemplate,valueName)));
        return wait.until(dr -> elements.stream()
                .filter(el -> !el.getText().equals(""))
                .findFirst()
                .get()
                .getText());
    }

    private void waitUntilCreditValueUpdate() {
        Integer price = StringUtils.convertStringToInt(driver.findElement(By.xpath(
                String.format(inputFieldByNameTemplate,"Стоимость недвижимости")))
                .getAttribute("value"));
        Integer initialFee = StringUtils.convertStringToInt(driver.findElement(By.xpath(
                String.format(inputFieldByNameTemplate,"Первоначальный взнос")))
                .getAttribute("value"));
        Integer expectedCredit = price - initialFee;

        wait.until(dr -> StringUtils.convertStringToInt(getCalculatedValueByName("Сумма кредита"))
                .equals(expectedCredit));
    }

    private void sendKeysByNumpads(WebElement element, String value) {
        for (char el : value.toCharArray()) {
            switch (el) {
                case '0':
                    element.sendKeys(Keys.NUMPAD0);
                    break;
                case '1':
                    element.sendKeys(Keys.NUMPAD1);
                    break;
                case '2':
                    element.sendKeys(Keys.NUMPAD2);
                    break;
                case '3':
                    element.sendKeys(Keys.NUMPAD3);
                    break;
                case '4':
                    element.sendKeys(Keys.NUMPAD4);
                    break;
                case '5':
                    element.sendKeys(Keys.NUMPAD5);
                    break;
                case '6':
                    element.sendKeys(Keys.NUMPAD6);
                    break;
                case '7':
                    element.sendKeys(Keys.NUMPAD7);
                    break;
                case '8':
                    element.sendKeys(Keys.NUMPAD8);
                    break;
                case '9':
                    element.sendKeys(Keys.NUMPAD9);
                    break;
            }
        }
    }
}
