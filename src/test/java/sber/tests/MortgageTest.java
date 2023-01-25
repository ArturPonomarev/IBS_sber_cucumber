package sber.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sber.framework.forms.MainPage;
import sber.framework.forms.SecondaryMortgagePage;

public class MortgageTest extends BaseTest {

    private final String MORTGAGE_MENU_NAME = "Ипотека";
    private final String MORTGAGE_SUBMENU_NAME = "Ипотека на вторичное жильё";

    private final String PRICE_FIELD_NAME = "Стоимость недвижимости";
    private final String INITIAL_FEE_FIELD_NAME = "Первоначальный взнос";
    private final String CREDIT_TERM_FIELD_NAME = "Срок кредита";

    //TODO: Вынести в тестовые данные
    private final String expectedCredit = "2 122 000 ₽";
    private final String expectedMounthPay = "21 664 ₽";
    private final String expectedIncome = "36 829 ₽";
    private final String expectedProcent = "11";


    private final Integer PRICE_VALUE = 5180000;
    private final Integer INITIAL_FEE_VALUE = 3058000;
    private final Integer CREDIT_TERM_VALUE = 30;

    MainPage mainPage = new MainPage();
    SecondaryMortgagePage mortgagePage = new SecondaryMortgagePage();

    @Test
    public void testMethod(){
        mainPage.clickOpenMenuButton(MORTGAGE_MENU_NAME);
        mainPage.clickSubMenuButton(MORTGAGE_SUBMENU_NAME);
        mortgagePage.enterToCalculator();
        mortgagePage.inputCalculatorField(PRICE_FIELD_NAME,PRICE_VALUE.toString());
        mortgagePage.inputCalculatorField(INITIAL_FEE_FIELD_NAME,INITIAL_FEE_VALUE.toString());
        mortgagePage.inputCalculatorField(CREDIT_TERM_FIELD_NAME,CREDIT_TERM_VALUE.toString());
        mortgagePage.clickHealthInsuranceCheckbox();
        Assertions.assertEquals(expectedCredit, mortgagePage.getCalculatedValueByName("Сумма кредита"),"Сумма кредита не соотвествует ожидаемой");
        Assertions.assertEquals(expectedMounthPay, mortgagePage.getCalculatedValueByName("Ежемесячный платеж"), "Ежемесячный платёж не соотвествует ожидаемой");
        Assertions.assertEquals(expectedIncome, mortgagePage.getCalculatedValueByName("Необходимый доход"), "Необходимый доход не соотвествует ожидаемому");
        Assertions.assertEquals(expectedProcent, mortgagePage.getCalculatedValueByName("Процентная ставка"), "Процентная ставка не соответствует ожидаемой");
        mortgagePage.leaveCalculator();
    }
}
