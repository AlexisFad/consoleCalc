package fadeev.alexey.console.calc.operator.impl;

import fadeev.alexey.console.calc.exception.OperatorException;
import fadeev.alexey.console.calc.operator.Operator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DivideOperatorTest {

    private final Operator subj = new DivideOperator();

    @Test
    public void getTokenTest() {
        assertEquals("/", subj.getToken());
    }

    @Test
    public void getPrecedenceTest() {
        assertEquals(2, (long) subj.precedence());
    }

    @Test
    public void getExecuteTestSuccess() {
        assertEquals(2.0, subj.execute(2, 1), 0);
    }

    @Test(expected = OperatorException.class)
    public void getExecuteTestError() {
        assertEquals(3.0, subj.execute(2, 1, 1), 0);
    }

    @Test(expected = ArithmeticException.class)
    public void getExecuteTestError_divideByZero() {
        assertEquals(3.0, subj.execute(2, 0), 0);
    }

}
