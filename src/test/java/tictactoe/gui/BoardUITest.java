package tictactoe.gui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import tictactoe.core.Board;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.gui.board.BoardUI;
import tictactoe.mocks.MockMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import static org.testfx.matcher.control.TextMatchers.hasText;

public class BoardUITest extends ApplicationTest {

    private MockMediator mockMediator = new MockMediator();
    private BoardUI boardUI = new BoardUI(mockMediator);

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(boardUI, 800, 800);
        boardUI.renderBoard(new Board(3));
        new StylesheetLoader(scene).load();
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void checkLayout() {
        verifyThat(".status-text", NodeMatchers.isNotNull());
        verifyThat(".board-container", NodeMatchers.isNotNull());
        verifyThat(".main-container", NodeMatchers.isNotNull());
    }

    @Test
    public void setStatusText() {
        boardUI.setStatusText("your turn player O");
        verifyThat(".status-text", hasText("your turn player O"));
    }

    @Test
    public void renderBoard() {
        verifyThat(boardUI, hasChildren(9, ".tile"));
    }

    @Test
    public void clickTile() {
        List<Node> tilesList = new ArrayList<>(lookup(".tile").queryAll());
        Node tile1 = tilesList.get(0);
        clickOn(tile1);

        assertEquals(
                "mediator receives correct move",
                1,
                mockMediator.getCurrentMove()
        );
    }

    @Test
    public void renderMoves() {
        Board currentBoard = new Board(3)
                .makeMove(1, PlayerSymbol.O)
                .makeMove(2, PlayerSymbol.X);
        new FxRobot().interact(() -> boardUI.renderBoard(currentBoard));

        verifyThat(boardUI, hasChildren(1, ".player-o"));
        verifyThat(boardUI, hasChildren(1, ".player-x"));
    }

}
