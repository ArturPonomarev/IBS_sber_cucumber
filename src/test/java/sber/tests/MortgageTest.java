package sber.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sber.framework.forms.MainPage;
import sber.framework.forms.SecondaryMortgagePage;

import static sber.framework.utils.JsonDataProvider.testData;

public class MortgageTest extends BaseTest {

    private final String MORTGAGE_MENU_NAME = "Ипотека";
    private final String MORTGAGE_SUBMENU_NAME = "Ипотека на вторичное жильё";

    private final String PRICE_FIELD_NAME = "Стоимость недвижимости";
    private final String INITIAL_FEE_FIELD_NAME = "Первоначальный взнос";
    private final String CREDIT_TERM_FIELD_NAME = "Срок кредита";

    private final String CREDIT_SUMM_VALUE_NAME = "Сумма кредита";
    private final String MONTH_PAY_VALUE_NAME = "Ежемесячный платеж";
    private final String NEED_INCOME_VALUE_NAME = "Необходимый доход";
    private final String CREDIT_PROCENT_VALUE_NAME = "Процентная ставка";

    MainPage mainPage = new MainPage();
    SecondaryMortgagePage mortgagePage = new SecondaryMortgagePage();

    @Test
    public void testMethod(){
        mainPage.clickOpenMenuButton(MORTGAGE_MENU_NAME);
        mainPage.clickSubMenuButton(MORTGAGE_SUBMENU_NAME);
        mortgagePage.enterToCalculator();
        mortgagePage.inputCalculatorField(PRICE_FIELD_NAME, testData.calculatorInputValues.housingPrice.toString());
        mortgagePage.inputCalculatorField(INITIAL_FEE_FIELD_NAME,testData.calculatorInputValues.initialFee.toString());
        mortgagePage.inputCalculatorField(CREDIT_TERM_FIELD_NAME,testData.calculatorInputValues.creditTerm.toString());
        mortgagePage.clickHealthInsuranceCheckbox();
        Assertions.assertEquals(testData.expectedValues.credit, mortgagePage.getCalculatedValueByName(CREDIT_SUMM_VALUE_NAME),
                "Сумма кредита не соотвествует ожидаемой");
        Assertions.assertEquals(testData.expectedValues.monthPay, mortgagePage.getCalculatedValueByName(MONTH_PAY_VALUE_NAME),
                "Ежемесячный платёж не соотвествует ожидаемой");
        Assertions.assertEquals(testData.expectedValues.income, mortgagePage.getCalculatedValueByName(NEED_INCOME_VALUE_NAME),
                "Необходимый доход не соотвествует ожидаемому");
        Assertions.assertEquals(testData.expectedValues.percent, mortgagePage.getCalculatedValueByName(CREDIT_PROCENT_VALUE_NAME),
                "Процентная ставка не соответствует ожидаемой");
        mortgagePage.leaveCalculator();
    }
}
