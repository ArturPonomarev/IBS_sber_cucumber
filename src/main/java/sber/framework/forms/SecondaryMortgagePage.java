package sber.framework.forms;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import sber.framework.utils.BrowserUtils;
import sber.framework.utils.StringUtils;

import javax.swing.*;


public class SecondaryMortgagePage extends BaseForm {

    private final String inputFieldByNameTemplate = "//*[contains(text(),'%s')]/preceding-sibling::input";
    private final String valueByNameTemplate = "//*[contains(text(),'%s')]/following-sibling::*//*[text()]";
    private final String calculatorFrameId = "iFrameResizer0";

    //TODO: Попытаться сокртить xpath
    @FindBy(xpath = "//*[contains(text(),'Страхование жизни и здоровья')]/..//input")
    private WebElement insuranceCheckBox;

    @FindBy(xpath = "//*[contains(@class,'switch-input')]")
    private WebElement checkBoxForUpdate;

    public void enterToCalculator() {
        BrowserUtils.enterToFrame(calculatorFrameId);
    }

    public void leaveCalculator() {
        BrowserUtils.leaveAllFrames();
    }

    public void inputCalculatorField(String fieldName, String fieldValue) {
        var element = driver.findElement(By.xpath(String.format(inputFieldByNameTemplate,fieldName)));
        element.sendKeys(Keys.chord(Keys.CONTROL + "a"));
        element.sendKeys(Keys.DELETE);
        sendKeysByNumpads(element,fieldValue);
    }

    public void clickHealthInsuranceCheckbox() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", insuranceCheckBox);
    }

    public String getCalculatedValueByName(String valueName) {
        var elements = driver.findElements(By.xpath(String.format(valueByNameTemplate,valueName)));
        return wait.until(dr -> elements.stream()
                .filter(el -> !el.getText().equals(""))
                .findFirst()
                .get()
                .getText());
    }

    public void waitUntilValueUpdate(Integer excpectedCredit) {
        wait.until(dr -> StringUtils.convertStringToInt(getCalculatedValueByName("Сумма кредита"))
                .equals(excpectedCredit));
    }

    private void sendKeysByNumpads(WebElement element, String value) {
        for (var el : value.toCharArray()) {
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
