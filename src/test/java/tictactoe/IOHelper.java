package tictactoe;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class IOHelper {
    public static ByteArrayOutputStream out;
    public static PrintStream print;

    public IOHelper() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.out = out;
        this.print = new PrintStream(out);
    }

    public String joinLines(String[] lines) {
        return String.join("\n", lines) + "\n";
    }
}
