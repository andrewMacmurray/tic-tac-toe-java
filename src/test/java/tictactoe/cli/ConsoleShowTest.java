package tictactoe.cli;

import org.junit.Test;
import tictactoe.core.Board;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.UI;

import static org.junit.Assert.*;

public class ConsoleShowTest {

    @Test
    public void showValidMoveSummary() {
        IOHelper io = new IOHelper();
        UI console = new Console(io.in, io.print);

        console.showMoveSummary(1, new Board(3), PlayerSymbol.X);
        assertTrue(
                "shows the summary of a valid move",
                io.output().contains("Player X took tile 1")
        );
    }

    @Test
    public void showOutOfBoundsMoveSummary() {
        IOHelper io = new IOHelper();
        UI console = new Console(io.in, io.print);

        console.showMoveSummary(12, new Board(3), PlayerSymbol.X);
        assertTrue(
                "shows out of bounds move summary",
                io.output().contains("12 is not on the board!")
        );
    }

    @Test
    public void showMoveAlreadyTakenSummary() {
        IOHelper io = new IOHelper();
        UI console = new Console(io.in, io.print);

        Board board = new Board(3)
                .makeMove(1, PlayerSymbol.X);

        console.showMoveSummary(1, board, PlayerSymbol.X);
        assertTrue(
                "shows move already taken summary",
                io.output().contains("1 is already taken!")
        );
    }

    @Test
    public void showMoveInstructions() {
        IOHelper io = new IOHelper();
        UI console = new Console(io.in, io.print);

        console.showMoveInstructions(3, PlayerSymbol.X);
        assertTrue(
                "prints correct instructions to player",
                io.output().contains("Ok player X, Enter a number from 1-9")
        );
    }

    @Test
    public void showBoard() {
        IOHelper io = new IOHelper();
        UI console = new Console(io.in, io.print);
        Board board = new Board(3);

        console.showBoard(board);
        assertTrue(
                "renders board to output",
                io.output().contains(new BoardSerializer(board).render())
        );
    }

    @Test
    public void clearScreen() {
        IOHelper io = new IOHelper();
        UI console = new Console(io.in, io.print);

        console.clear();
        assertTrue(
                "renders clear sequence",
                io.output().contains("\033[H\033[2J")
        );
    }

    @Test
    public void showWinner() {
        IOHelper io = new IOHelper();
        UI console = new Console(io.in, io.print);

        console.showWin(PlayerSymbol.X);
        assertTrue(
                "renders a winner correctly",
                io.output().contains("Player X won!")
        );
    }

    @Test
    public void showDraw() {
        IOHelper io = new IOHelper();
        UI console = new Console(io.in, io.print);

        console.showDraw();
        assertTrue(
                "prints draw",
                io.output().contains("It's a draw!")
        );
    }
}

