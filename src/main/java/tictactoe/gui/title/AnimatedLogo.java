package tictactoe.gui.title;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimatedLogo extends HBox {

    private List<LogoPiece> pieces;

    public AnimatedLogo() {
        pieces = makePieces();
        setup();
    }

    private void setup() {
        appendPieces();
        addCss();
    }

    private void appendPieces() {
        this.getChildren().addAll(pieces);
    }

    private void addCss() {
        this.getStyleClass().add("logo-container");
    }

    private List<LogoPiece> makePieces() {
        return Stream.of("X", "O", "X", "O")
                .map(LogoPiece::new)
                .collect(Collectors.toList());
    }

    public void animateAndThen(Runnable onFinish) {
        hidePieces();
        Timeline timeline = new Timeline();
        for (int i = 0; i < pieces.size(); i++) {
            timeline.getKeyFrames().addAll(
                    opacityKeyframe(0, (i + 1) * 300, i),
                    opacityKeyframe(1, (i + 3) * 300, i)
            );
        }
        timeline.play();
        timeline.setOnFinished(e -> onFinish.run());
    }

    private void hidePieces() {
        pieces.forEach(piece -> piece.setOpacity(0));
    }

    private KeyFrame opacityKeyframe(int opacity, int duration, int index) {
        return new KeyFrame(
                new Duration(duration),
                new KeyValue(pieces.get(index).opacityProperty(), opacity)
        );
    }

}