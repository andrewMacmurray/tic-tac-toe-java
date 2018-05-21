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
        TranslateTransition transition = new TranslateTransition(duration, node);
        node.setTranslateY(from);
        transition.setToY(0);
        this.transition = transition;
        return this;
    }

    public void play() {
        if (transition != null) {
            transition.play();
        }
    }

    public void setOnEnd(Runnable nextTask) {
        transition.setOnFinished(e -> nextTask.run());
    }

}
