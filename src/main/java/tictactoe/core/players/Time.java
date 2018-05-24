package tictactoe.core.players;

public interface Time {

    void pauseAndThen(int ms, Runnable task);

}
