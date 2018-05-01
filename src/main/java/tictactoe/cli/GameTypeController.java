package tictactoe.cli;

import tictactoe.core.types.GameType;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

public class GameTypeController {

    private Scanner scanner;
    private PrintStream out;
    private GameType gameType;

    public GameTypeController(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    public GameType run() {
        greetUser();
        gameTypeInstructions();
        promptGameType();
        return getGameType();
    }

    public void greetUser() {
        out.println(Messages.welcome);
    }

    public void gameTypeInstructions() {
        Messages.gameTypeOptions()
                .forEach(out::println);
    }

    public GameType getGameType() {
        return gameType;
    }

    public void promptGameType() {
        Integer i = parseGameTypeInput();
        Optional<GameType> res = parseGameType(i);
        if (res.isPresent()) {
            this.gameType = res.get();
        } else {
            printParseError();
            promptGameType();
        }
    }

    private Integer parseGameTypeInput() {
        String opt = scanner.next();
        try {
            return Integer.parseInt(opt);
        } catch (RuntimeException e) {
            printParseError();
            return parseGameTypeInput();
        }
    }

    private Optional<GameType> parseGameType(Integer i) {
        switch (i) {
            case 1:
                return Optional.of(GameType.HumanVsHuman);
            case 2:
                return Optional.of(GameType.HumanVsComputer);
            case 3:
                return Optional.of(GameType.ComputerVsComputer);
            default:
                return Optional.empty();
        }
    }

    private void printParseError() {
        out.println(Messages.unrecognised);
    }
}
