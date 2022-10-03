/*
 * DivOp.java
 * Created on 11/22/18 12:03 PM
 * Author: terry
 *
 * Copyright (c) 2018 Terry Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fadeev.alexey.console.calc.operator.impl;

import fadeev.alexey.console.calc.exception.OperatorException;
import fadeev.alexey.console.calc.operator.Operator;
import org.springframework.stereotype.Component;

@Component
public class DivideOperator implements Operator {

    @Override
    public String getToken() {
        return "/";
    }

    @Override
    public Integer precedence() {
        return 2;
    }

    @Override
    public double execute(double... operands) {
        if (operands.length != 2) {
            throw new OperatorException("Оператор деление должен иметь 2 операнда");
        }
        if (operands[1] == 0) {
            throw new ArithmeticException("Деление на ноль запрещено");
        }
        return operands[0] / operands[1];
    }

}