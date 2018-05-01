package tictactoe.cli;

import org.junit.Test;

import java.util.Optional;

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

        assertEquals(Optional.of(1), io.readInt());
    }

    @Test
    public void intReaderNotFound() {
        IOHelper ioHelper = new IOHelper("unrecognised input");
        IO io = new IO(ioHelper.in, ioHelper.print);

        assertEquals(Optional.empty(), io.readInt());
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
}
