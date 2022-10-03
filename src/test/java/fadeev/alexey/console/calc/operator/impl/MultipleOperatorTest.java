package fadeev.alexey.console.calc.operator.impl;

import fadeev.alexey.console.calc.exception.OperatorException;
import fadeev.alexey.console.calc.operator.Operator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MultipleOperatorTest {

    private final Operator subj = new MultipleOperator();

    @Test
    public void getTokenTest() {
        assertEquals("*", subj.getToken());
    }

    @Test
    public void getPrecedenceTest() {
        assertEquals(2, (long) subj.precedence());
    }

    @Test
    public void getExecuteTestSuccess() {
        assertEquals(6.0, subj.execute(2, 3), 0);
    }

    @Test(expected = OperatorException.class)
    public void getExecuteTestError() {
        assertEquals(3.0, subj.execute(2, 1, 1), 0);
    }


}
