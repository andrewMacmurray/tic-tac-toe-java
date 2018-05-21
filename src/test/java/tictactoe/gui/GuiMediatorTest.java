package tictactoe.gui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import static org.testfx.matcher.control.TextMatchers.hasText;

public class GuiMediatorTest extends ApplicationTest {

    private GuiMediator guiMediator;
    private Parent rootNode;

    @Override
    public void start(Stage stage) {
        GuiMediator guiMediator = new GuiMediator();
        Scene scene = guiMediator.getCurrentScene();
        guiMediator.runGame();
        rootNode = scene.getRoot();

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void clickOnTile() {
        clickOnTile(0);
        verifyThat(rootNode, hasChildren(1, ".player-x"));
        verifyThat(rootNode, hasChildren(0, ".player-o"));
    }

    @Test
    public void makeMultipleMoves() {
        clickOnTiles(0, 1, 2, 3);
        verifyThat(rootNode, hasChildren(2, ".player-x"));
        verifyThat(rootNode, hasChildren(2, ".player-o"));
    }

    @Test
    public void disabledClickOnExistingMove() {
        clickOnTiles(0, 0);
        verifyThat(rootNode, hasChildren(1, ".player-x"));
        verifyThat(rootNode, hasChildren(0, ".player-o"));
    }

    @Test
    public void xWin() {
        clickOnTiles(0, 3, 1, 4, 2);
        verifyThat(".status-text", hasText("Player X Won!"));
    }

    @Test
    public void oWin() {
        clickOnTiles(3, 0, 4, 1, 6, 2);
        verifyThat(".status-text", hasText("Player O Won!"));
    }

    @Test
    public void disabledClicksAfterGame() {
        clickOnTiles(0, 3, 1, 4, 2, 8);
        verifyThat(rootNode, hasChildren(3, ".player-x"));
        verifyThat(rootNode, hasChildren(2, ".player-o"));
    }

    private void clickOnTiles(int... tiles) {
        for (int tileIndex : tiles) {
            clickOnTile(tileIndex);
        }
    }

    private void clickOnTile(int index) {
        clickOn(lookupTile(index));
    }

    private Node lookupTile(int index) {
        ArrayList<Node> tiles = new ArrayList<>(lookup(".tile").queryAll());
        return tiles.get(index);
    }

}
