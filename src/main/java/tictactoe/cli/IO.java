package tictactoe.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

public class IO {

    private final PrintStream out;
    private final Scanner scanner;

    public IO() {
        out = System.out;
        scanner = new Scanner(System.in);
    }

    public IO(InputStream in, PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    public void println(String string) {
        out.println(string);
    }

    public void clearScreen() {
        final String clearSequence = "\033[H\033[2J";
        out.print(clearSequence);
        out.flush();
    }

    public Optional<Integer> readInt() {
        try {
            Integer i = parseNextInt();
            return Optional.of(i);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private Integer parseNextInt() throws NumberFormatException {
        String input = scanner.next();
        return Integer.parseInt(input);
    }
}
