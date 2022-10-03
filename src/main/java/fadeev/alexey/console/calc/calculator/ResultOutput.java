package fadeev.alexey.console.calc.calculator;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ResultOutput {

    private static final String SUCCESS_RESULT = "Результат: ";
    private static final String ERROR = "Ошибка: ";

    private Double resultExpression;

    private String error;

    public String prettyOutput() {
        if (Objects.nonNull(error) && !error.isBlank()) {
           return ERROR + error;
        }
        if (Objects.isNull(resultExpression)) {
            return SUCCESS_RESULT;
        }
        DecimalFormat fmt = new DecimalFormat("0.##########");
        return SUCCESS_RESULT + String.format("%s", fmt.format(resultExpression));
    }

}
