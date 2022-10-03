package fadeev.alexey.console.calc.utils;

import fadeev.alexey.console.calc.exception.RpnParseException;
import fadeev.alexey.console.calc.operator.OperatorHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Stack;

import static fadeev.alexey.console.calc.constants.CommonConstants.*;

@Component
@RequiredArgsConstructor
public class RpnHelper {

    private final OperatorHelper operatorHelper;

    /**
     * @param expression  строка с выражением.
     * Преобразует из инфиксной нотации в постфиксную.
     * Пример:
     * <br> 1 + 2 - 3                 -> 1 2 + 3 -</br>
     * <br> 1 * 2 /3                  -> 1 2 * 3 / </br>
     * <br> 1 + 2 * 3                 -> 1 2 3 * + </br>
     * <br> 1 * 2 + 3                 -> 1 2 * 3 + </br>
     * <br> 1 * (2 + 3)               -> 1 2 3 + * </br>
     * <br> 1 * 2 + 3 * 4             -> 1 2 * 2 3 * +</br>
     * <br> (1 + 2) * (3 - 4)         -> 1 2 + 3 4 - * </br>
     * <br> ((1 + 2) * 3) - 4         -> 1 2 + 3 * 4 - </br>
     * <br> 1 + 2 * (3 - 4 / (5 + 6)) -> 1 2 3 4 5 6 + / - * + </br>
     *
     * @return преобразованная строка.
     */
    public String infixToPostfix(String expression) {
        expression = parseUnary(expression);

        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == DOT_CHAR) {
                if (
                        i == 0
                        || postfix.length() == 0
                        || Character.isDigit(postfix.charAt(postfix.length() - 1))
                        || postfix.charAt(postfix.length() - 1) == DOT_CHAR
                        || postfix.charAt(postfix.length() - 1) == ' ') {

                    postfix.append(c);
                } else {
                    appendWithWhiteSpace(postfix, c);
                }
            }

            else if (c == LEFT_PARENTHESIS_CHAR) {
                stack.push(c);
            }

            else if (c == RIGHT_PARENTHESIS_CHAR) {
                while (!stack.isEmpty() && stack.peek() != LEFT_PARENTHESIS_CHAR) {
                    appendWithWhiteSpace(postfix, stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != LEFT_PARENTHESIS_CHAR) {
                   throw new RpnParseException("Некорректное выражение");
                } else if (stack.isEmpty()) {
                    throw new RpnParseException("Некорректное выражение");
                } else {
                    stack.pop();
                }
            }
            else {
                postfix.append(WHITESPACE);
                while (!stack.isEmpty() &&
                        operatorHelper.precedenceByToken(c) <= operatorHelper.precedenceByToken(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            appendWithWhiteSpace(postfix, stack.pop());
        }
        return postfix.toString();
    }

    /**
     * @param expr  строка с выражением.
     * Если встречется унарный оператор выполнется преобразование строки.
     * Пример:
     * <br> -2*3    -> (0-2)*3</br>
     * <br> 5*(-1)  -> 5*((0 - 1)) </br>
     * <br> 2+-2    -> 2+(0-2) </br>
     * <br> +2+1    -> 2+1 </br>
     * <br> 2+(+1)  -> 2+(1) </br>
     * <br> 2-+1    -> 2-1 </br>
     *
     * @return  преобразованная строка.
     */
    private String parseUnary(String expr) {
        for(int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == MINUS_CHAR) {
                if (i == 0 || expr.charAt(i - 1) == LEFT_PARENTHESIS_CHAR || operatorHelper.precedenceByToken(expr.charAt(i - 1)) > 0) {
                    StringBuilder stringBuilder = new StringBuilder(expr);
                    stringBuilder.insert(i++, LEFT_PARENTHESIS_CHAR);
                    stringBuilder.insert(i++, ZERO_CHAR);
                    i++;
                    for (; i < stringBuilder.length(); i++) {
                        if (!Character.isDigit(stringBuilder.charAt(i)) && stringBuilder.charAt(i) != DOT_CHAR) {
                            break;
                        }
                    }
                    stringBuilder.insert(i, RIGHT_PARENTHESIS_CHAR);
                    expr = stringBuilder.toString();
                }
            }
            else if (expr.charAt(i) == PLUS_CHAR) {
                if (i == 0 || expr.charAt(i - 1) == LEFT_PARENTHESIS_CHAR || operatorHelper.precedenceByToken(expr.charAt(i - 1)) > 0) {
                    StringBuilder stringBuilder = new StringBuilder(expr);
                    stringBuilder.deleteCharAt(i);
                    expr = stringBuilder.toString();
                    i--;
                }
            }
        }
        return expr;
    }

    private void appendWithWhiteSpace(StringBuilder stringBuilder, Character character) {
        stringBuilder.append(WHITESPACE).append(character);
    }

}