package tictactoe.cli;

import tictactoe.core.players.Time;

public class SimpleTime implements Time {

    @Override
    public void pauseAndThen(int ms, Runnable task) {
        try {
            Thread.sleep(ms);
            task.run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
