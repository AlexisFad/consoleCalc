package fadeev.alexey.console.calc.calculator.impl;

import fadeev.alexey.console.calc.calculator.ResultOutput;
import fadeev.alexey.console.calc.exception.RpnParseException;
import fadeev.alexey.console.calc.operator.OperatorHelper;
import fadeev.alexey.console.calc.operator.impl.AddOperator;
import fadeev.alexey.console.calc.utils.RpnHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RpnCalculatorTest {

    @Mock
    private RpnHelper rpnHelper;

    @Mock
    private OperatorHelper operatorHelper;

    @InjectMocks
    private RpnCalculator rpnCalculator;

    @Test
    public void rpnCalculatorTest() {
        String expr = "1+1";
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setResultExpression(2.0);

        when(operatorHelper.operationByToken("+")).thenReturn(Optional.of(new AddOperator()));
        when(rpnHelper.infixToPostfix(expr)).thenReturn("1 1 +");

        Assert.assertEquals(resultOutput, rpnCalculator.calculate(expr));
    }

    @Test
    public void rpnCalculatorTest_rpnParseException() {
        String expr = "1+1";
        String exceptionError = "Ошибка";
        RpnParseException rpnParseException = new RpnParseException(exceptionError);

        when(rpnHelper.infixToPostfix(expr)).thenThrow(rpnParseException);

        Assert.assertEquals(exceptionError, rpnCalculator.calculate(expr).getError());
    }

    @Test
    public void rpnCalculatorTest_invalidExpression_extra_number() {
        String expr = "1+1";
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setResultExpression(2.0);

        when(operatorHelper.operationByToken("+")).thenReturn(Optional.of(new AddOperator()));
        when(rpnHelper.infixToPostfix(expr)).thenReturn("1 1 1 +");

        Assert.assertEquals("Что-то пошло не так, результат должен быть только один",
                rpnCalculator.calculate(expr).getError());
    }

    @Test
    public void rpnCalculatorTest_invalidExpression_one_number() {
        String expr = "1+1";
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setResultExpression(2.0);

        when(operatorHelper.operationByToken("+")).thenReturn(Optional.of(new AddOperator()));
        when(rpnHelper.infixToPostfix(expr)).thenReturn("1 +");

        Assert.assertEquals("Некорректное выражение, для операции + должно быть 2 числа",
                rpnCalculator.calculate(expr).getError());
    }



}
