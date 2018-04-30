package tictactoe.game;

import tictactoe.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Model {

    private final Board board;
    private final PlayerSymbol currentPlayer;
    private final GameStatus gameStatus;
    private final GuessStatus guessStatus;

    public Model(int boardSize, PlayerSymbol currentPlayer) {
        this.board = new Board(boardSize);
        this.currentPlayer = currentPlayer;
        this.gameStatus = GameStatus.NonTerminal;
        this.guessStatus = GuessStatus.Valid;
    }

    public Model evalMove(int move) {
        return _evalMove(move, this.currentPlayer, this.board);
    }

    public PlayerSymbol getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int getBoardSize() {
        return this.board.getBoardSize();
    }

    public Map<Integer, Tile> getTiles() {
       return this.board.getTiles();
    }

    public Optional<PlayerSymbol> getWinner() {
        return this.board.winner();
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public GuessStatus getGuessStatus() {
        return this.guessStatus;
    }

    private Model _evalMove(int move, PlayerSymbol currentPlayer, Board board) {
        GuessStatus guessStatus = evalGuessStatus(move);
        if (guessStatus.isValid()) {
            return new Model(move, currentPlayer, board);
        } else {
            return new Model(guessStatus, currentPlayer, board);
        }
    }

    // Constructs new Model based on a valid move
    private Model(int move, PlayerSymbol currentPlayer, Board board) {
        Board nextBoard = board.makeMove(move, currentPlayer);
        this.board = nextBoard;
        this.currentPlayer = currentPlayer.getAlternate();
        this.gameStatus = evalGameStatus(nextBoard);
        this.guessStatus = GuessStatus.Valid;
    }

    // Constructs new Model based on an invalid guess status
    private Model(GuessStatus guessStatus, PlayerSymbol currentPlayer, Board board) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.gameStatus = GameStatus.NonTerminal;
        this.guessStatus = guessStatus;
    }


    private GameStatus evalGameStatus(Board board) {
        if (board.winner().isPresent()) {
            return GameStatus.Win;
        } else if (board.isFull()) {
            return GameStatus.Draw;
        } else {
            return GameStatus.NonTerminal;
        }
    }

    private GuessStatus evalGuessStatus(int move) {
        if (this.board.isOutOfBounds(move)) {
            return GuessStatus.OutOfBounds;
        } else if (!this.board.isMoveAvailable(move)) {
            return GuessStatus.AlreadyTaken;
        } else {
            return GuessStatus.Valid;
        }
    }
}
