package tictactoe.game;

import tictactoe.core.Board;
import tictactoe.core.GuessStatus;
import tictactoe.core.Player;
import tictactoe.core.GameStatus;

import java.util.HashMap;

public class Model {

    private final Board board;
    private final Player currentPlayer;
    private final GameStatus gameStatus;
    private final GuessStatus guessStatus;

    public Model(int boardSize, Player currentPlayer) {
        this.board = new Board(boardSize);
        this.currentPlayer = currentPlayer;
        this.gameStatus = GameStatus.NonTerminal;
        this.guessStatus = GuessStatus.Valid;
    }

    public Model evalMove(int move) {
        return _evalMove(move, this.currentPlayer, this.board);
    }

    public boolean isMoveAvailable(int move) {
        return this.board.isMoveAvailable(move);
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int getBoardSize() {
        return this.board.getBoardSize();
    }

    public HashMap<Integer, Player> getTiles() {
       return this.board.getTiles();
    }

    public Player getWinner() {
        return this.board.winner();
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public GuessStatus getGuessStatus() {
        return this.guessStatus;
    }

    private Model _evalMove(int move, Player currentPlayer, Board board) {
        GuessStatus guessStatus = evalGuessStatus(move);
        if (guessStatus.isValid()) {
            return new Model(move, currentPlayer, board);
        } else {
            return new Model(guessStatus, currentPlayer, board);
        }
    }

    // Constructs new Model based on a valid move
    private Model(int move, Player currentPlayer, Board board) {
        Board nextBoard = board.makeMove(move, currentPlayer);
        this.board = nextBoard;
        this.currentPlayer = currentPlayer.getAlternate();
        this.gameStatus = evalGameStatus(nextBoard);
        this.guessStatus = GuessStatus.Valid;
    }

    // Constructs new Model based on an invalid guess status
    private Model(GuessStatus guessStatus, Player currentPlayer, Board board) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.gameStatus = GameStatus.NonTerminal;
        this.guessStatus = guessStatus;
    }


    private GameStatus evalGameStatus(Board board) {
        if (!board.winner().isEmpty()) {
            return GameStatus.Win;
        } else if (board.isFull()) {
            return GameStatus.Draw;
        } else {
            return GameStatus.NonTerminal;
        }
    }

    private GuessStatus evalGuessStatus(int move) {
        if (isOutOfBounds(move)) {
            return GuessStatus.OutOfBounds;
        } else if (!isMoveAvailable(move)) {
            return GuessStatus.AlreadyTaken;
        } else {
            return GuessStatus.Valid;
        }
    }

    private boolean isOutOfBounds(int move) {
        int upperBound = getBoardSize() * getBoardSize();
        return move < 1 || move > upperBound;
    }
}
