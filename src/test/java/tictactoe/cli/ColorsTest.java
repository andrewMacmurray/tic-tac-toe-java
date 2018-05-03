package tictactoe.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class ColorsTest {

    @Test
    public void colorBlue() {
        assertEquals(
                "should add blue sequence to input",
                "\u001B[1;34mHello\u001B[0m",
                Colors.toLightBlue("Hello")
        );
    }

    @Test
    public void colorGreen() {
        assertEquals(
                "should add green sequence to input",
                "\u001B[32mHello\u001B[0m",
                Colors.toGreen("Hello")
        );
    }
}
