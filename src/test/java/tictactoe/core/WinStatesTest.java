package tictactoe.core;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class WinStatesTest {

    @Test
    public void threeByThreeStates() {
        List<Stream<Integer>> expectedStates = Arrays.asList(
                Stream.of(1, 2, 3), Stream.of(4, 5, 6), Stream.of(7, 8, 9),
                Stream.of(1, 4, 7), Stream.of(2, 5, 8), Stream.of(3, 6, 9),
                Stream.of(1, 5, 9), Stream.of(3, 5, 7)
        );

        List<Stream<Integer>> actualStates = new WinStates(3)
                .generate()
                .collect(Collectors.toList());

        for (int i = 0; i < expectedStates.size(); i++) {
            assertStreamEquals(expectedStates.get(i), actualStates.get(i));
        }
    }

    @Test
    public void fourByFourStates() {
        List<Stream<Integer>> expectedStates = Arrays.asList(
                Stream.of(1, 2, 3, 4),
                Stream.of(5, 6, 7, 8),
                Stream.of(9, 10, 11, 12),
                Stream.of(13, 14, 15, 16),
                Stream.of(1, 5, 9, 13),
                Stream.of(2, 6, 10, 14),
                Stream.of(3, 7, 11, 15),
                Stream.of(4, 8, 12, 16),
                Stream.of(1, 6, 11, 16),
                Stream.of(4, 7, 10, 13)
        );

        List<Stream<Integer>> actualStates = new WinStates(4)
                .generate()
                .collect(Collectors.toList());

        for (int i = 0; i < expectedStates.size(); i++) {
            assertStreamEquals(expectedStates.get(i), actualStates.get(i));
        }
    }

    private static void assertStreamEquals(Stream<?> s1, Stream<?> s2) {
        Iterator<?> iter1 = s1.iterator(),
                    iter2 = s2.iterator();
        while (iter1.hasNext() && iter2.hasNext())
            assertEquals(iter1.next(), iter2.next());
        assert !iter1.hasNext() && !iter2.hasNext();
    }
}

