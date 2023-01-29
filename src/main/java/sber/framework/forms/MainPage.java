package sber.framework.forms;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class MainPage extends BaseForm {

    private final String isExpandedAttributeName = "aria-expanded";
    private final String labelAttributeName = "aria-label";

    @FindBy(xpath = "//*[@aria-expanded][not(contains(@class,'external'))]")
    private List<WebElement> topMenuButtonsList;

    @FindBy(xpath = "//*[contains(@class,'kitt-top-menu__item_opened')]//*[contains(@class,'kitt-top-menu__link_second')]")
    private List<WebElement> subMenuButtonsList;

    @Step("Открытие верхнего меню с названием {menuName}")
    public void clickOpenMenuButton(String menuName) {
        Optional<WebElement> element = topMenuButtonsList.stream()
                .filter(el -> el.getText().equals(menuName))
                .findFirst();

        if (element.isPresent())
            element.get().click();
        else
            Assertions.fail("Выпадающего меню с названием " + menuName + " не существует");

        /**
         * Версия java 9+
         */
        /*element.ifPresentOrElse(
                WebElement::click,
                () -> Assertions.fail("Выпадающего меню с названием " + menuName + " не существует"));*/

        waitIsMenuOpen(menuName);
    }

    @Step("Открытие подменю с названием {subMenuName}")
    public void clickSubMenuButton(String subMenuName) {
        Optional<WebElement> element = subMenuButtonsList.stream()
                .filter(el -> el.getText().equals(subMenuName))
                .findFirst();

        if (element.isPresent())
            element.get().click();
        else
            Assertions.fail("Подменю с названием " + subMenuName + " не существует");

        /**
         * Версия java 9+
         */
        /*element.ifPresentOrElse(
                WebElement::click,
                () -> Assertions.fail("Подменю с названием " + subMenuName + " не существует"));*/
    }

    private void waitIsMenuOpen(String menuName) {
        wait.until(dr -> topMenuButtonsList.stream()
                .anyMatch(el -> ((el.getAttribute(labelAttributeName).equals(menuName))
                        && (el.getAttribute(isExpandedAttributeName).equals("true")))));
    }
}
