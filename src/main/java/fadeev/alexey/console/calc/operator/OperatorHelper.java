package fadeev.alexey.console.calc.operator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
public class OperatorHelper {

    private final Collection<Operator> operators;
    private final Map<String, Operator> operatorsMap;

    @PostConstruct
    private void init () {
        operators.forEach(operator -> operatorsMap.put(operator.getToken(), operator));
    }

    public int precedenceByToken(Character token) {
        Operator operator = operatorsMap.get(token.toString());
        return Objects.isNull(operator) ? -1 : operator.precedence();

    }

    public Optional<Operator> operationByToken(String token) {
        return Optional.ofNullable(operatorsMap.get(token));
    }

}