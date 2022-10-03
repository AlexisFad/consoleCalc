package fadeev.alexey.console.calc;

import fadeev.alexey.console.calc.calculator.Calculator;
import fadeev.alexey.console.calc.controller.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	private final Calculator calculator;

	private final Controller controller;

	public static void main(String[] args) {
		SpringApplication.run(
				Application.class, args);
	}

	@Override
	public void run(String... args) {
		controller.output("Введите выражение: ");
		String inputExpr = controller.input();
		controller.output(calculator.calculate(inputExpr).prettyOutput());
	}

}