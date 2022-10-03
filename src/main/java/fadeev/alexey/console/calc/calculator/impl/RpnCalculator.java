package fadeev.alexey.console.calc.calculator.impl;

import fadeev.alexey.console.calc.calculator.Calculator;
import fadeev.alexey.console.calc.calculator.ResultOutput;
import fadeev.alexey.console.calc.exception.CalculatorException;
import fadeev.alexey.console.calc.operator.Operator;
import fadeev.alexey.console.calc.operator.OperatorHelper;
import fadeev.alexey.console.calc.utils.RpnHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Stack;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RpnCalculator implements Calculator {

    private final RpnHelper rpnHelper;

    private final OperatorHelper operatorHelper;

    @Override
    public ResultOutput calculate(String expression) {
        ResultOutput result = new ResultOutput();
        try {
            expression = rpnHelper.infixToPostfix(prepareExpression(expression));
            result.setResultExpression(processLine(expression));
        } catch (Exception ex) {
            result.setError(ex.getMessage());
        }
        return result;
    }

    private String prepareExpression(String expr) {
        expr = expr.replaceAll(" ", "");
        expr = expr.replaceAll(",", ".");
        return expr;
    }

    private Double processLine(@NonNull String line) {
        Stack<Double> values = new Stack<>();

        Stream.of(line.split(" "))
                .forEach(s -> processToken(s, values));

        if (values.size() > 1) {
            throw new CalculatorException("Что-то пошло не так, результат должен быть только один");
        }

        return values.pop();
    }

    private void processToken(String token, Stack<Double> values) {
        if (token == null || token.isEmpty()) {
            return;
        }
        Optional<Operator> optionalOperator = operatorHelper.operationByToken(token);
        if (optionalOperator.isPresent()) {
            Operator operator = optionalOperator.get();
            if (values.size() < 2) {
              throw new CalculatorException(String.format("Некорректное выражение, для операции %s должно быть 2 числа",
                      operator.getToken()));
            }
            double value;
            double d1 = values.pop();
            double d2 = values.pop();
            value = operator.execute(d2, d1);
            values.push(value);
        }
        else {
            double value = Double.parseDouble(token);
            values.push(value);
        }
    }

}
