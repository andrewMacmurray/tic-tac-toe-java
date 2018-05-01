package tictactoe.cli.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class IOHelper {
    public static ByteArrayOutputStream out;
    public static ByteArrayInputStream in;
    public static PrintStream print;

    public IOHelper() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        this.out = out;
        this.in = in;
        this.print = new PrintStream(out);
    }

    public IOHelper(String input) {
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
