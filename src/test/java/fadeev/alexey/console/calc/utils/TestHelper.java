package fadeev.alexey.console.calc.utils;

import fadeev.alexey.console.calc.operator.Operator;
import fadeev.alexey.console.calc.operator.OperatorHelper;
import fadeev.alexey.console.calc.operator.impl.AddOperator;
import fadeev.alexey.console.calc.operator.impl.DivideOperator;
import fadeev.alexey.console.calc.operator.impl.MultipleOperator;
import fadeev.alexey.console.calc.operator.impl.SubtractionOperator;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class TestHelper {

    public static OperatorHelper getOperatorHelper() {
        Map<String, Operator> map = new HashMap<>();
        map.put("+", new AddOperator());
        map.put("/", new DivideOperator());
        map.put("*", new MultipleOperator());
        map.put("-", new SubtractionOperator());
        return new OperatorHelper(map.values(), map);
    }

}
