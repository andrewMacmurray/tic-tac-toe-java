package tictactoe.cli;

import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.players.PlayerSymbol;

public class ConsoleMediator extends Mediator {

    private Console console;

    public ConsoleMediator(Console console) {
        this.console = console;
    }

    @Override
    public void runGame() {
        console.clear();
        console.greetUser();
        console.showGameOptions();
        requestPlayersFromUI();
    }

    @Override
    public void gameInstructions(Board board, PlayerSymbol playerSymbol) {
        console.clear();
        console.showBoard(board);
        console.showMoveInstructions(board.getBoardSize(), playerSymbol);
    }

    @Override
    public void requestMoveFromUI(Board board, PlayerSymbol playerSymbol) {
        receiveMove(console.requestMove(board, playerSymbol));
    }

    @Override
    public void moveSummary(int move, Board prevBoard, Board nextBoard, PlayerSymbol playerSymbol) {
        console.clear();
        console.showBoard(nextBoard);
        console.showMoveSummary(move, prevBoard, playerSymbol);
    }

    public void playAgain() {
        if (console.requestPlayAgain()) {
            runGame();
        } else {
            console.goodbye();
        }
    }

    @Override
    public void requestBoardSizeFromUI() {
        receiveBoardSize(console.requestBoardSize());
    }

    @Override
    public void requestPlayersFromUI() {
        receivePlayers(console.requestPlayers(this));
    }

    @Override
    public void announceWin(PlayerSymbol playerSymbol, Board board) {
        console.clear();
        console.showBoard(board);
        console.showWin(playerSymbol);
        playAgain();
    }

    @Override
    public void announceDraw(Board board) {
        console.clear();
        console.showBoard(board);
        console.showDraw();
        playAgain();
    }

}