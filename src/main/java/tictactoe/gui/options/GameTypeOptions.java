package tictactoe.gui.options;

import javafx.scene.layout.VBox;
import tictactoe.gui.SlideUpTransition;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class GameTypeOptions extends VBox {

    private List<OptionButton> gameOptions;
    private Consumer<Integer> sendGameType;
    private SlideUpTransition transition;

    public GameTypeOptions(Consumer<Integer> sendGameType) {
        this.sendGameType = sendGameType;
        setup();
    }

    public void animate() {
        transition.play();
    }

    private void setup() {
        gameOptions = createButtons();
        appendOptions();
        addListeners();
        setupAnimation();
    }

    private void setupAnimation() {
        transition = new SlideUpTransition(this, 700).setFrom(500);
    }

    private void appendOptions() {
        this.getChildren().addAll(gameOptions);
    }

    private void addListeners() {
        gameOptions.forEach(option -> option.onClick(sendGameType));
    }

    private List<OptionButton> createButtons() {
        return Arrays.asList(
                new OptionButton("Human Vs Human", 1),
                new OptionButton("Human Vs Computer", 2),
                new OptionButton("Computer Vs Computer", 3)
        );
    }

}
