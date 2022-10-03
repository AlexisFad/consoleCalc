package fadeev.alexey.console.calc.calculator;

public interface Calculator {

    /**
     * @param   expression   входная строка, содержащая математическое выражение.
     * @return  результат выполнения выражения.
     */
    ResultOutput calculate(String expression);

}
