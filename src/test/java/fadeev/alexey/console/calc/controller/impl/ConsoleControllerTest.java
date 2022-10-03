package fadeev.alexey.console.calc.controller.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final InputStream systemIn = System.in;
    private final PrintStream originalOut = System.out;

    private final ConsoleController controller = new ConsoleController();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(systemIn);
    }

    @Test
    public void outputTest() {
        String test = "outputTest";
        controller.output(test);
        Assert.assertEquals(test, outContent.toString());
    }

    @Test
    public void inputTest() {
        String test = "inputTest";
        provideInput(test);
        Assert.assertEquals(test, controller.input());
    }


    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

}
