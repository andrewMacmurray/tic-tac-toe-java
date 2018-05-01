package tictactoe.core;

import java.util.Map;
import java.util.Optional;

public class Game {

    private final Board board;
    private final PlayerSymbol currentPlayer;
    private final GameStatus gameStatus;
    private final GuessStatus guessStatus;

    public Game(int boardSize, PlayerSymbol currentPlayer) {
        this.board = new Board(boardSize);
        this.currentPlayer = currentPlayer;
        this.gameStatus = GameStatus.NonTerminal;
        this.guessStatus = GuessStatus.Valid;
    }

    public Game evalMove(int move) {
        return _evalMove(move, currentPlayer, board);
    }

    public PlayerSymbol getCurrentPlayer() {
        return currentPlayer;
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    public Map<Integer, Tile> getTiles() {
       return board.getTiles();
    }

    public Optional<PlayerSymbol> getWinner() {
        return board.getWinner();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public GuessStatus getGuessStatus() {
        return guessStatus;
    }

    private Game _evalMove(int move, PlayerSymbol currentPlayer, Board board) {
        GuessStatus guessStatus = evalGuessStatus(move);
        if (guessStatus.isValid()) {
            return new Game(move, currentPlayer, board);
        } else {
            return new Game(guessStatus, currentPlayer, board);
        }
    }

    // Constructs new Game based on a valid move
    private Game(int move, PlayerSymbol currentPlayer, Board board) {
        Board nextBoard = board.makeMove(move, currentPlayer);
        this.board = nextBoard;
        this.currentPlayer = currentPlayer.getAlternate();
        this.gameStatus = evalGameStatus(nextBoard);
        this.guessStatus = GuessStatus.Valid;
    }

    // Constructs new Game based on an invalid guess status
    private Game(GuessStatus guessStatus, PlayerSymbol currentPlayer, Board board) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.gameStatus = GameStatus.NonTerminal;
        this.guessStatus = guessStatus;
    }

    private GameStatus evalGameStatus(Board board) {
        if (board.hasWinner()) {
            return GameStatus.Win;
        } else if (board.isFull()) {
            return GameStatus.Draw;
        } else {
            return GameStatus.NonTerminal;
        }
    }

    private GuessStatus evalGuessStatus(int move) {
        if (board.isMoveOutOfBounds(move)) {
            return GuessStatus.OutOfBounds;
        } else if (!board.isMoveAvailable(move)) {
            return GuessStatus.AlreadyTaken;
        } else {
            return GuessStatus.Valid;
        }
    }
}
