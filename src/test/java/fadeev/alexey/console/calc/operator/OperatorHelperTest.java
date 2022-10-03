package fadeev.alexey.console.calc.operator;

import fadeev.alexey.console.calc.utils.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class OperatorHelperTest {

    private OperatorHelper operatorHelper;

    @Before
    public void init() {
        operatorHelper = TestHelper.getOperatorHelper();
    }

    @Test
    public void precedenceByTokenTest() {
        assertEquals(1, operatorHelper.precedenceByToken('+'));
        assertEquals(2, operatorHelper.precedenceByToken('*'));
        assertEquals(1, operatorHelper.precedenceByToken('-'));
        assertEquals(2, operatorHelper.precedenceByToken('/'));
        assertEquals(-1, operatorHelper.precedenceByToken('~'));
    }

    @Test
    public void OperatorByTokenTest() {

        Optional<Operator> optionalOperator = operatorHelper.operationByToken("-");
        assertTrue(optionalOperator.isPresent());
        Operator operator = optionalOperator.get();
        assertEquals("-", operator.getToken());

    }

}
