package tictactoe.gui;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.service.query.NodeQuery;
import tictactoe.gui.title.AnimatedLogo;

import java.util.Set;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class AnimatedLogoTest extends ApplicationTest {

    AnimatedLogo animatedLogo = new AnimatedLogo();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(animatedLogo, 800, 700);
        Stylesheet.load(scene);

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void fourPieces() {
        verifyThat(animatedLogo, hasChildren(4, ".logo-piece"));
        verifyThat(animatedLogo, hasChildren(2, ".x"));
        verifyThat(animatedLogo, hasChildren(2, ".o"));
    }

    @Test
    public void animateLogo() {
        animatedLogo.animateAndThen(() -> {
            Set<Node> pieces = lookup(".logo-piece").queryAll();
            pieces.forEach(piece -> {
                verifyThat(piece, node -> node.getOpacity() == 1);
            });
        });
    }

}
