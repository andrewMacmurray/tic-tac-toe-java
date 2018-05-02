package tictactoe.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class IOTest {

    @Test
    public void printer() {
        IOHelper ioHelper = new IOHelper();

        IO io = new IO(ioHelper.in, ioHelper.print);
        io.println("hello");

        assertEquals("hello\n", ioHelper.output());
    }

    @Test
    public void intReader() {
        IOHelper ioHelper = new IOHelper("1");
        IO io = new IO(ioHelper.in, ioHelper.print);

        assertEquals("reads a valid int from input stream", 1, io.readIntWithRetry("something went wrong"));
    }

    @Test
    public void readRetry() {
        IOHelper ioHelper = new IOHelper("unrecognised input 2");
        IO io = new IO(ioHelper.in, ioHelper.print);

        assertEquals("will retry until it reaches a valid int", 2, io.readIntWithRetry("something went wrong"));
        assertTrue(ioHelper.output().contains("something went wrong"));
    }

    @Test
    public void clearScreen() {
        IOHelper ioHelper = new IOHelper();
        IO io = new IO(ioHelper.in, ioHelper.print);

        io.println("something to the screen");
        io.clearScreen();
        String clearSequence = "\033[H\033[2J";
        assertTrue(ioHelper.output().contains(clearSequence));
    }

    @Test
    public void readIntInRange() {
        IOHelper ioHelper = new IOHelper("2");
        IO io = new IO(ioHelper.in, ioHelper.print);

        assertEquals(
                "reads a valid int within a range",
                2,
                io.readIntInRange(1, 3, "something went wrong")
        );
    }

    public void readUntilInRange() {
        IOHelper ioHelper = new IOHelper("5 blah 3");
        IO io = new IO(ioHelper.in, ioHelper.print);

        assertEquals(
                "reads until it reaches an in within range",
                3,
                io.readIntInRange(1, 3, "something went wrong")
        );
        assertTrue(ioHelper.output().contains("something went wrong"));
    }
}
