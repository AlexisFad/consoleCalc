package fadeev.alexey.console.calc.controller.impl;

import fadeev.alexey.console.calc.controller.Controller;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleController implements Controller {

    @Override
    public String input() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void output(String string) {
        System.out.print(string);
    }

}
