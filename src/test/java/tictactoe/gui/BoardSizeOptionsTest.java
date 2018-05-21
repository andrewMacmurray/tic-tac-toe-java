package tictactoe.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import tictactoe.gui.options.BoardSizeOptions;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class BoardSizeOptionsTest extends ApplicationTest {

    private BoardSizeOptions boardSizeOptions;
    private int currentOption;

    @Override
    public void start(Stage stage) {
        boardSizeOptions = new BoardSizeOptions(this::setCurrentOption);
        Scene scene = new Scene(boardSizeOptions, 800, 700);
        Stylesheet.load(scene);

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void renderTwoButtons() {
        verifyThat(boardSizeOptions, hasChildren(2, ".option-button"));
    }

    @Test
    public void clickOption() {
        clickOn(".option-button-3");
        assertEquals(3, currentOption);
    }

    private void setCurrentOption(int option) {
        currentOption = option;
    }

}
