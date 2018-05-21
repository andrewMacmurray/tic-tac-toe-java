package tictactoe.gui;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class SlideUpTransition {

    private Node node;
    private Duration duration;
    private Transition transition;

    public SlideUpTransition(Node node, int duration) {
        this.node = node;
        this.duration = Duration.millis(duration);
    }

    public SlideUpTransition setFrom(int from) {
        TranslateTransition ts = new TranslateTransition(duration, node);
        node.setTranslateY(from);
        ts.setToY(0);
        transition = ts;
        return this;
    }

    public void play() {
        if (transition != null) {
            transition.play();
        }
    }

    public void setOnEnd(Runnable finish) {
        transition.setOnFinished(e -> finish.run());
    }

}
