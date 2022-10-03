package fadeev.alexey.console.calc.calculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResultOutputTest {

    @Test
    public void prettyOutputTest_error() {
        String error = "Некорретная строка";
        String expectedResult = "Ошибка: " + error;
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setError(error);

        Assert.assertEquals(expectedResult, resultOutput.prettyOutput());
    }

    @Test
    public void prettyOutputTest_success_without_value() {
        String expectedResult = "Результат: ";
        ResultOutput resultOutput = new ResultOutput();

        Assert.assertEquals(expectedResult, resultOutput.prettyOutput());
    }

    @Test
    public void prettyOutputTest_success_with_value() {
        String expectedResult = "Результат: 2";
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setResultExpression(2.0);

        Assert.assertEquals(expectedResult, resultOutput.prettyOutput());
    }

    @Test
    public void prettyOutputTest_success_with_float_value() {
        String expectedResult = "Результат: 2,1";
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setResultExpression(2.1);

        Assert.assertEquals(expectedResult, resultOutput.prettyOutput());
    }

}