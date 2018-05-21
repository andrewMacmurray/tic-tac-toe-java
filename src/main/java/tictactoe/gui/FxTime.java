package tictactoe.gui;

import javafx.application.Platform;
import tictactoe.core.players.Time;

import java.util.concurrent.FutureTask;

public class FxTime implements Time {

    @Override
    public void pauseAndThen(int ms, Runnable task) {
        FutureTask<Void> sleeper = new FutureTask<>(() -> {
            Thread.sleep(ms);
            Platform.runLater(task::run);
            return null;
        });

        try {
            new Thread(sleeper).start();
        } catch (Exception e) {
            System.out.println("Fx Time interrupted");
        }
    }

}
