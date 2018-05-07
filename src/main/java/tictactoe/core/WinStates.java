package tictactoe.core;

import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WinStates {

    private final int boardSize;

    public WinStates(int boardSize) {
        this.boardSize = boardSize;
    }

    public Stream<Stream<Integer>> generate() {
        return Stream.of(
                rows(),
                columns(),
                Stream.of(bottomLeftDiagonal(), topLeftDiagonal())
        )
                .flatMap(x -> x);
    }

    private Stream<Stream<Integer>> columns() {
        return moveStream(1, x -> x + 1)
                .map(y -> IntStream.range(0, boardSize).mapToObj(z -> z * boardSize + y));
    }

    private Stream<Stream<Integer>> rows() {
        return moveStream(1, x -> x + boardSize).map(y -> moveStream(y, z -> z + 1));
    }

    private Stream<Integer> topLeftDiagonal() {
        return moveStream(boardSize, x -> x + boardSize - 1);
    }

    private Stream<Integer> bottomLeftDiagonal() {
        return moveStream(1, x -> x + boardSize + 1);
    }

    private Stream<Integer> moveStream(Integer seed, UnaryOperator<Integer> fn) {
        return Stream
                .iterate(seed, fn)
                .limit(boardSize);
    }
}
