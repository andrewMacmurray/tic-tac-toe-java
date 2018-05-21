package tictactoe.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;
import tictactoe.gui.board.BoardUI;
import tictactoe.gui.options.OptionsUI;

public class GuiMediator extends Mediator {

    private Scene currentScene;
    private BoardUI boardUI;
    private OptionsUI optionsUI;

    public GuiMediator() {
        setup();
    }

    private void setup() {
        createBoardUI();
        createOptionsUI();
        initScene();
    }

    private void initScene() {
        Scene scene = new Scene(optionsUI, 800, 700);
        Stylesheet.load(scene);
        currentScene = scene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    private void createBoardUI() {
        boardUI = new BoardUI(this::receiveMove, this::requestPlayersFromUI);
    }

    private void createOptionsUI() {
        optionsUI = new OptionsUI(this::prepareEmptyBoard, this::receiveGameTypeOption);
    }

    @Override
    public void runGame() {
        requestPlayersFromUI();
    }

    @Override
    public void requestPlayersFromUI() {
        optionsUI.reset();
        setScene(optionsUI);
    }

    public void receiveGameTypeOption(int option) {
        Players players = new PlayersFactory(this, new FxTime()).createPlayers(option);
        receivePlayers(players);
    }

    private void prepareEmptyBoard(int boardSize) {
        boardUI.renderBoard(new Board(boardSize));
        receiveBoardSize(boardSize);
    }

    @Override
    public void requestBoardSizeFromUI() {
        optionsUI.showBoardSizeOptions();
        setScene(optionsUI);
    }

    @Override
    public void requestMoveFromUI(Board board, PlayerSymbol playerSymbol) {
        boardUI.renderBoard(board);
    }

    @Override
    public void moveSummary(int move, Board prevBoard, Board nextBoard, PlayerSymbol playerSymbol) {
        String statusText = "Your turn player " + playerSymbol.getAlternate();
        boardUI.renderBoard(nextBoard);
        boardUI.disableClicks();
        boardUI.setStatusText(statusText);
    }

    @Override
    public void gameInstructions(Board board, PlayerSymbol playerSymbol) {
        String instructions = "Your turn player " + playerSymbol;
        boardUI.setStatusText(instructions);
        currentScene.setRoot(boardUI);
    }

    @Override
    public void announceWin(PlayerSymbol playerSymbol, Board board) {
        boardUI.disableClicks();
        boardUI.setStatusText("Player " + playerSymbol + " Won!");
        boardUI.playAgain();
    }

    @Override
    public void announceDraw(Board board) {
        boardUI.disableClicks();
        boardUI.setStatusText("It's a draw!");
        boardUI.playAgain();
    }

    private void setScene(Parent rootNode) {
        currentScene.setRoot(rootNode);
    }

}