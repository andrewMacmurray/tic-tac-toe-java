package tictactoe.core;

import org.junit.Test;
import tictactoe.core.*;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void currentPlayer() {
        Game game = new Game(3, PlayerSymbol.X);
        assertEquals("cli should contain a currentPlayer", PlayerSymbol.X, game.getCurrentPlayer());
    }

    @Test
    public void boardSize() {
        Game game = new Game(3, PlayerSymbol.X);
        assertEquals("cli should contain the board size", 3, game.getBoardSize());
    }

    @Test
    public void evalMove() {
        Game game = new Game(3, PlayerSymbol.X).evalMove(1);
        assertTrue("PlayerSymbol X should have made first move", moveTakenBy(1, PlayerSymbol.X, game));
        assertEquals("PlayerSymbol O should be the next player", PlayerSymbol.O, game.getCurrentPlayer());
    }

    @Test
    public void immutableModel() {
        Game game1 = new Game(3, PlayerSymbol.X)
                .evalMove(1)
                .evalMove(2);

        Game game2 = game1
                .evalMove(3)
                .evalMove(4);

        assertFalse("game1 should not be affected by changes to game2", moveTakenBy(3, PlayerSymbol.X, game1));
        assertTrue("game2 should be updated correctly", moveTakenBy(3, PlayerSymbol.X, game2));
    }

    @Test
    public void validMoveStatus() {
        Game game = new Game(3, PlayerSymbol.X).evalMove(1);

        assertEquals("valid guess should result in a valid guess status", GuessStatus.Valid, game.getGuessStatus());
        assertTrue("board should be updated correctly", moveTakenBy(1, PlayerSymbol.X, game));
    }

    @Test
    public void outOfBoundsMoveStatus() {
        Game beforeGame = new Game(3, PlayerSymbol.X);
        Game afterGame = beforeGame.evalMove(10);

        assertEquals("out of bounds guess should result in OutOfBounds guess status", GuessStatus.OutOfBounds, afterGame.getGuessStatus());
        // assertArrayEquals("board should remain unmodified", beforeGame.getTiles(), afterGame.getTiles());
    }

    @Test
    public void alreadyTakenStatus() {
        Game beforeGame = new Game(3, PlayerSymbol.X).evalMove(1);
        Game afterGame = beforeGame.evalMove(1);

        assertEquals("attempting to take a previously taken tile should result in AlreadyTaken guess status", GuessStatus.AlreadyTaken, afterGame.getGuessStatus());
        // assertArrayEquals("board should remain unmodified", beforeGame.getTiles(), afterGame.getTiles());
    }

    @Test
    public void getGameStatus() {
        Game game = new Game(3, PlayerSymbol.X)
                .evalMove(1)
                .evalMove(2)
                .evalMove(3);
        assertEquals("cli should be in NonTerminal State", GameStatus.NonTerminal, game.getGameStatus());
    }

    @Test
    public void winStatus() {
        Game game = new Game(3, PlayerSymbol.X)
                .evalMove(1)
                .evalMove(4)
                .evalMove(2)
                .evalMove(5)
                .evalMove(3);
        assertEquals("cli should be in Win state", GameStatus.Win, game.getGameStatus());
    }

    @Test
    public void drawStatus() {
        int boardSize = 3;
        int[] drawMoves = {1, 2, 3, 5, 4, 6, 8, 7, 9};
        Game game = new Game(boardSize, PlayerSymbol.X);
        for (int move : drawMoves) {
            game = game.evalMove(move);
        }
        assertEquals("board should be in draw state", GameStatus.Draw, game.getGameStatus());
    }


    private boolean moveTakenBy(int index, PlayerSymbol playerSymbol, Game game) {
        return game
                .getTiles()
                .get(index)
                .isTakenBy(playerSymbol);
    }
}