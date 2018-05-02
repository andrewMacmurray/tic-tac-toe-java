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

    @Test
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

    @Test
    public void readYesNoWithRetry() {
        IOHelper ioHelper = new IOHelper("huh Y");
        IO io = new IO(ioHelper.in, ioHelper.print);

        boolean result = io.readYesNoWithRetry("something went wrong");
        assertTrue(
                "Prints error message if doesn't find a yes or no",
                ioHelper.output().contains("something went wrong")
        );
        assertEquals(
                "should read input until it reaches Y and returns true", true, result
        );
    }

    @Test
    public void readSomethingLikeYes() {
        IOHelper ioHelper = new IOHelper("yeee");
        IO io = new IO(ioHelper.in, ioHelper.print);

        boolean result = io.readYesNoWithRetry("something went wrong");
        assertEquals(
                "should read anything starting with a y (lowercase or upper)", true, result
        );
    }

    @Test
    public void readNo() {
        IOHelper ioHelper = new IOHelper("nahh");
        IO io = new IO(ioHelper.in, ioHelper.print);

        boolean result = io.readYesNoWithRetry("something went wrong");
        assertEquals(
                "should return false if input starts with an n",
                false,
                result
        );
    }
}
