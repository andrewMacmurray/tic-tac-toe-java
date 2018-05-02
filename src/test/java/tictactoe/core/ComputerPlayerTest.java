package tictactoe.core;


import org.junit.Test;
import tictactoe.core.players.ComputerPlayer;
import tictactoe.core.players.Player;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.mocks.MockTime;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ComputerPlayerTest {

    @Test
    public void playerSymbol() {
        Player computer = new ComputerPlayer(PlayerSymbol.O, new MockTime());

        assertEquals(
                "can retrieve the player symbol for computer player",
                PlayerSymbol.O,
                computer.getSymbol()
        );
    }

    @Test
    public void chooseMove() {
        Player computer = new ComputerPlayer(PlayerSymbol.X, new MockTime());
        Board board = new Board(3);
        ArrayList<Integer> allAvailableMoves = board.allAvailableMoves();


        assertTrue(
                "computer makes a random move",
                allAvailableMoves.contains(computer.chooseNextMove(board))
        );
    }

    @Test
    public void delayedMove() {
        MockTime time = new MockTime();
        Player computer = new ComputerPlayer(PlayerSymbol.X, time);
        Board board = new Board(3);

        computer.chooseNextMove(board);
        assertEquals(
                "computer makes move after a delay",
                "some time has passed",
                time.timeLog
        );
    }
}

