package tictactoe.cli;

import tictactoe.core.Board;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.ui.UIRequest;
import tictactoe.core.ui.UIShow;

import java.io.InputStream;
import java.io.PrintStream;

public class Console implements UIRequest, UIShow {

    private final IO io;

    Console(InputStream in, PrintStream print) {
        io = new IO(in, print);
    }

    @Override
    public Integer requestMove(Board board, PlayerSymbol playerSymbol) {
        Integer move = readIntWithMessage();
        if (!board.isValidMove(move)) {
            showMoveSummary(move, board, playerSymbol);
            return requestMove(board, playerSymbol);
        } else {
            return move;
        }
    }

    @Override
    public void showMoveSummary(Integer move, Board board, PlayerSymbol playerSymbol) {
        if (board.isMoveOutOfBounds(move)) {
            printOutOfBounds(move);
        } else if (!board.isMoveAvailable(move)) {
            showAlreadyTaken(move);
        } else {
            showValidMove(move, playerSymbol);
        }
    }

    @Override
    public void showMoveInstructions(Integer boardSize, PlayerSymbol playerSymbol) {
        io.println(Messages.enterNumbers(boardSize, playerSymbol));
    }

    @Override
    public void clear() {
        io.clearScreen();
    }

    @Override
    public Players requestPlayers() {
        return PlayersFactory.createPlayers(readPlayerOption(), this);
    }

    @Override
    public void showBoard(Board board) {
        io.println(BoardSerializer.render(board));
    }

    void greetUser() {
        io.println(Messages.welcome);
    }

    void showInstructions() {
        Messages.gameTypeOptions()
                .forEach(io::println);
    }

    @Override
    public void showWin(PlayerSymbol playerSymbol) {
        io.println(Messages.winner(playerSymbol));
    }

    @Override
    public void showDraw() {
        io.println(Messages.draw);
    }

    private void printOutOfBounds(Integer guess) {
        io.println(Messages.outOfBounds(guess));
    }

    private void printUnrecognised() {
        io.println(Messages.unrecognised);
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
