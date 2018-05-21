package tictactoe.cli;

import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;

import java.io.InputStream;
import java.io.PrintStream;

public class Console {

    private final IO io;

    public Console(InputStream in, PrintStream print) {
        io = new IO(in, print);
    }

    public int requestMove(Board board, PlayerSymbol playerSymbol) {
        int move = readIntWithMessage();
        if (!board.isValidMove(move)) {
            showMoveSummary(move, board, playerSymbol);
            return requestMove(board, playerSymbol);
        } else {
            return move;
        }
    }

    public Players requestPlayers(Mediator mediator) {
        return new PlayersFactory(mediator, new SimpleTime()).createPlayers(readPlayerOption());
    }

    public int requestBoardSize() {
        io.println(Messages.boardSize);
        return io.readIntInRange(3, 4, Messages.unrecognised);
    }

    public boolean requestPlayAgain() {
        io.println(Messages.playAgain);
        return io.readYesNoWithRetry(Messages.unrecognised);
    }

    public void showMoveSummary(Integer move, Board board, PlayerSymbol playerSymbol) {
        if (board.isMoveOutOfBounds(move)) {
            printOutOfBounds(move);
        } else if (!board.isMoveAvailable(move)) {
            showAlreadyTaken(move);
        } else {
            showValidMove(move, playerSymbol);
        }
    }

    public void showMoveInstructions(Integer boardSize, PlayerSymbol playerSymbol) {
        io.println(Messages.enterNumbers(boardSize, playerSymbol));
    }

    public void clear() {
        io.clearScreen();
    }

    public void showBoard(Board board) {
        String boardString = new BoardSerializer(board).render();
        io.println(boardString);
    }

    public void greetUser() {
        io.println(Messages.welcome);
    }

    public void goodbye() {
        io.println(Messages.goodbye);
    }

    public void showGameOptions() {
        Messages.gameTypeOptions()
                .forEach(io::println);
    }

    public void showWin(PlayerSymbol playerSymbol) {
        io.println(Messages.winner(playerSymbol));
    }

    public void showDraw() {
        io.println(Messages.draw);
    }

    private void printOutOfBounds(Integer guess) {
        io.println(Messages.outOfBounds(guess));
    }

    private void showAlreadyTaken(Integer guess) {
        io.println(Messages.alreadyTaken(guess));
    }

    private void showValidMove(Integer guess, PlayerSymbol playerSymbol) {
        Messages.playerGuess(guess, playerSymbol)
                .forEach(io::println);
    }

    private Integer readPlayerOption() {
        return io.readIntInRange(
                PlayersFactory.minOption,
                PlayersFactory.maxOption,
                Messages.unrecognised
        );
    }

    private Integer readIntWithMessage() {
        return io.readIntWithRetry(Messages.unrecognised);
    }
}
