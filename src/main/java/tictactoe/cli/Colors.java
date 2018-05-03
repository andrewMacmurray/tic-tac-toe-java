package tictactoe.cli;

import java.util.function.Function;

public class Colors {

    private static String lightBlue = "\u001B[1;34m";
    private static String green = "\u001B[32m";
    private static String reset = "\u001B[0m";

    public static String toGreen(String input) {
        return toColor(green).apply(input);
    }

    public static String toLightBlue(String input) {
        return toColor(lightBlue).apply(input);
    }

    private static Function<String, String> toColor(String color) {
        return input -> String.format("%s%s%s", color, input, reset);
    }

}
