package tictactoe.gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import tictactoe.gui.title.TitleText;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class TitleTextTest extends ApplicationTest {

    private TitleText titleText;

    @Override
    public void start(Stage stage) {
        TitleText titleText = new TitleText();
        this.titleText = titleText;

        Scene scene = buildScene(titleText);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void showText() {
        verifyThat(titleText, hasChildren(1, ".title-text"));
    }

    @Test
    public void animateText() {
        verifyThat(".title-text", node -> node.getTranslateY() == 500);
        titleText.animateAndThen(() -> {
            verifyThat(".title-text", node -> node.getTranslateY() == 0);
        });
    }

    private Scene buildScene(TitleText titleText) {
        Scene scene = new Scene(titleText, 800, 700);
        Stylesheet.load(scene);
        return scene;
    }

}
