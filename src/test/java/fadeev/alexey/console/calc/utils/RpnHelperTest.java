package fadeev.alexey.console.calc.utils;


import fadeev.alexey.console.calc.exception.RpnParseException;
import fadeev.alexey.console.calc.operator.Operator;
import fadeev.alexey.console.calc.operator.OperatorHelper;
import fadeev.alexey.console.calc.operator.impl.AddOperator;
import fadeev.alexey.console.calc.operator.impl.DivideOperator;
import fadeev.alexey.console.calc.operator.impl.MultipleOperator;
import fadeev.alexey.console.calc.operator.impl.SubtractionOperator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RpnHelperTest {

    private RpnHelper rpnHelper;

    @Before
    public void init() {
        Map<String, Operator> map = new HashMap<>();
        map.put("+", new AddOperator());
        map.put("/", new DivideOperator());
        map.put("*", new MultipleOperator());
        map.put("-", new SubtractionOperator());
        OperatorHelper operatorHelper = new OperatorHelper(map.values(), map);
        rpnHelper = new RpnHelper(operatorHelper);
    }

    @Test
    public void test_valid() {
        assertEquals("1 2 + 3 -", rpnHelper.infixToPostfix("1+2-3"));
        assertEquals("1 2 * 3 /", rpnHelper.infixToPostfix("1*2/3"));
        assertEquals("1 2 3 * +", rpnHelper.infixToPostfix("1+2*3"));
        assertEquals("1 2 * 3 +", rpnHelper.infixToPostfix("1*2+3"));

        assertEquals("1 2 3 + *", rpnHelper.infixToPostfix("1*(2+3)"));
        assertEquals("1 2 * 3 4 * +", rpnHelper.infixToPostfix("1*2+3*4"));
        assertEquals("1 2 + 3 4 - *", rpnHelper.infixToPostfix("(1+2)*(3-4)"));
        assertEquals("1 2 3 4 5 6 + / - * +", rpnHelper.infixToPostfix("1+2*(3-4/(5+6))"));

        assertEquals("0 1 - 2 +", rpnHelper.infixToPostfix("-1+2"));
        assertEquals("0 1 - 2 *", rpnHelper.infixToPostfix("(-1)*2"));
        assertEquals("1 2 *", rpnHelper.infixToPostfix("+1*2"));
    }

    @Test(expected = RpnParseException.class)
    public void test_invalid() {
       rpnHelper.infixToPostfix("1+)");
    }

}