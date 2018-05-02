package tictactoe.cli;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class IOHelper {

    static ByteArrayOutputStream out;
    static ByteArrayInputStream in;
    static PrintStream print;

    IOHelper() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        this.out = out;
        this.in = in;
        this.print = new PrintStream(out);
    }

    IOHelper(String input) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        this.out = out;
        this.in = in;
        this.print = new PrintStream(out);
    }

    public String joinLines(String[] lines) {
        return String.join("\n", lines) + "\n";
    }

    public String output() {
        return out.toString();
    }

    public void reset() {
        out.reset();
    }

    public void setIn(String input) {
        ByteArrayInputStream newIn = new ByteArrayInputStream(input.getBytes());
        this.in = newIn;
    }
}
