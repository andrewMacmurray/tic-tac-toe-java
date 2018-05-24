package tictactoe.cli;

public class CliMain {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        ConsoleMediator consoleMediator = new ConsoleMediator(console);
        consoleMediator.runGame();
    }

}
