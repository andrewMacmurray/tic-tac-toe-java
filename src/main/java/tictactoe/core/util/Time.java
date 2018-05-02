package tictactoe.core.util;

public class Time implements ThreadControl {

    @Override
    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
