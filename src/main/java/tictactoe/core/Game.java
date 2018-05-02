package tictactoe.core;

import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;

public class Game {

    private Board board;
    private final Players players;
    private final UI ui;

    public Game(int boardSize, UI ui, Players players) {
        this.board = new Board(boardSize);
        this.ui = ui;
        this.players = players;
    }

    public void play() {
        initialBoard();
        playMoves();
        summary();
    }

    private void initialBoard() {
        ui.clear();
        ui.showBoard(board);
        ui.showMoveInstructions(board.getBoardSize(), players.currentPlayerSymbol());
    }

    private void summary() {
        ui.clear();
        ui.showBoard(board);
        if (board.xWin()) {
            ui.showWin(PlayerSymbol.X);
        } else if (board.oWin()) {
            ui.showWin(PlayerSymbol.O);
        } else if (board.isFull()) {
            ui.showDraw();
        }
    }

    private void playMoves() {
        while (!board.isTerminal()) {
            playMove();
        }
    }

    private void playMove() {
        Board newBoard = evalNextMove();
        nextState(newBoard);
    }

    private void nextState(Board board) {
        this.board = board;
        players.switchPlayers();
    }

    private Board evalNextMove() {
        Integer move = players.chooseNextMove(board);
        Board newBoard = board.makeMove(move, players.currentPlayerSymbol());

        ui.clear();
        ui.showBoard(newBoard);
        ui.showMoveSummary(move, board, players.currentPlayerSymbol());

        return newBoard;
    }

}
