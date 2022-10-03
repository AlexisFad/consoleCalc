/*
 * Operator.java
 * Created on 11/22/18 11:17 AM
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

package fadeev.alexey.console.calc.operator;

public interface Operator {

    /**
     * Получить токен операции.
     * @return токен
     */
    String getToken();

    /**
     * Получить приоритет операции.
     * @return приоритет операции
     */
    Integer precedence();

    /**
     * Выполнить выражение.
     * @param operands операнды
     * @return результат
     */
    double execute(double... operands);
}
