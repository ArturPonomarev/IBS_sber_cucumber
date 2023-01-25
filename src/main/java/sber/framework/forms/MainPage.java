package sber.framework.forms;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BaseForm {

    private final String isExpandedAttributeName = "aria-expanded";
    private final String labelAttributeName = "aria-label";

    @FindBy(xpath = "//*[@aria-expanded][not(contains(@class,'external'))]")
    private List<WebElement> topMenuButtonsList;

    @FindBy(xpath = "//*[contains(@class,'kitt-top-menu__item_opened')]//*[contains(@class,'kitt-top-menu__link_second')]")
    private List<WebElement> subMenuButtonsList;

    @Step("Открытие верхнего меню с названием {menuName}")
    public void clickOpenMenuButton(String menuName) {
        var element = topMenuButtonsList.stream()
                .filter(el -> el.getText().equals(menuName))
                .findFirst();

        element.ifPresentOrElse(
                WebElement::click,
                () -> Assertions.fail("Выпадающего меню с названием " + menuName + " не существует"));

        waitIsMenuOpen(menuName);
    }

    @Step("Открытие подменю с названием {subMenuName}")
    public void clickSubMenuButton(String subMenuName) {
        var element = subMenuButtonsList.stream()
                .filter(el -> el.getText().equals(subMenuName))
                .findFirst();

        element.ifPresentOrElse(
                WebElement::click,
                () -> Assertions.fail("Подменю с названием " + subMenuName + " не существует"));
    }

    private void waitIsMenuOpen(String menuName) {
        wait.until(dr -> topMenuButtonsList.stream()
                .anyMatch(el -> ((el.getAttribute(labelAttributeName).equals(menuName))
                        && (el.getAttribute(isExpandedAttributeName).equals("true")))));
    }
}
