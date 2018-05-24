package tictactoe.gui;

import javafx.application.Platform;
import tictactoe.core.players.Time;

import java.util.concurrent.FutureTask;

public class FxTime implements Time {

    @Override
    public void pauseAndThen(int ms, Runnable task) {
        FutureTask<Void> delayedTask = new FutureTask<>(() -> {
            Thread.sleep(ms);
            Platform.runLater(task);
            return null;
        });

        try {
            new Thread(delayedTask).start();
        } catch (Exception e) {
            System.out.println("Fx Time interrupted");
        }
    }

}
