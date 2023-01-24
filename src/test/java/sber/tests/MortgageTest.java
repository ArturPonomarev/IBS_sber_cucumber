package sber.tests;

import org.junit.jupiter.api.Test;
import sber.framework.forms.MainPage;
import sber.framework.forms.SecondaryMortgagePage;

public class MortgageTest extends BaseTest {

    private final String MORTGAGE_MENU_NAME = "Ипотека";
    private final String MORTGAGE_SUBMENU_NAME = "Ипотека на вторичное жильё";

    private final String PRICE_FIELD_NAME = "Стоимость недвижимости";
    private final String INITIAL_FEE_FIELD_NAME = "Первоначальный взнос";
    private final String CREDIT_TERM_FIELD_NAME = "Срок кредита";

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
        mortgagePage.waitUntilValueUpdate(4580000);
        mortgagePage.inputCalculatorField(INITIAL_FEE_FIELD_NAME,INITIAL_FEE_VALUE.toString());
        mortgagePage.waitUntilValueUpdate(2122000);
        mortgagePage.inputCalculatorField(CREDIT_TERM_FIELD_NAME,CREDIT_TERM_VALUE.toString());
        mortgagePage.clickHealthInsuranceCheckbox();
        System.out.println(mortgagePage.getCalculatedValueByName("Сумма кредита"));
        System.out.println(mortgagePage.getCalculatedValueByName("Ежемесячный платеж"));
        System.out.println(mortgagePage.getCalculatedValueByName("Необходимый доход"));
        System.out.println(mortgagePage.getCalculatedValueByName("Процентная ставка"));

        mortgagePage.leaveCalculator();
    }
}
