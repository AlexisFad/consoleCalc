package fadeev.alexey.console.calc.controller;

public interface Controller {

    /**
     * Получение строки из внешнего источника.
     * @return строка из внешнего источника.
     */
    String input();

    /**
     * Записывает результат во внешний источник.
     * @param string  строка, для записи
     */
    void output(String string);

}
