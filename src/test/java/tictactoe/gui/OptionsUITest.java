package tictactoe.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import tictactoe.gui.options.OptionsUI;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class OptionsUITest extends ApplicationTest {

    private OptionsUI optionsUI;
    private int boardSize;

    @Override
    public void start(Stage stage) {
        optionsUI = new OptionsUI(this::setBoardSize);
        Scene scene = new Scene(optionsUI, 800, 700);

        Stylesheet.load(scene);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void allElements() {
        verifyThat(optionsUI, hasChildren(1, ".logo-container"));
        verifyThat(optionsUI, hasChildren(1, ".option-button-3"));
        verifyThat(optionsUI, hasChildren(1, ".option-button-4"));
    }

    @Test
    public void selectBoardSize() {
        clickOn(".option-button-4");
        assertEquals(4, boardSize);
    }

    private void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

}
