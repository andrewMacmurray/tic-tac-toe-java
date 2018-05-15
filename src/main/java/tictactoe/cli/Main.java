package tictactoe.cli;

public class Main {

    public static void main(String[] args) {
        ConsoleMediator consoleMediator = new ConsoleMediator(new Console(System.in, System.out));
        consoleMediator.startGame();
    }

}
