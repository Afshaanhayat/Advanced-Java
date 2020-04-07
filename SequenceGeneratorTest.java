package edu.iust.advancejava.streams.lambda;
import org.junit.Test;
import static edu.iust.advancejava.streams.lambda.SequenceGenerator.*;
import static org.junit.Assert.assertArrayEquals;


public class SequenceGeneratorTest {
    @Test
    public void usingRange() {
        assertArrayEquals(
                range(1, 10, 1).toArray(),
                new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}
        );
    }

    @Test
    public void usingRangeWithStepLogic() throws Exception {
        assertArrayEquals(
                rangeWithStepLogic(1, 20, new StepLogic() {
                    @Override
                    public int increment(int i) { return i + i; }
                }).toArray(),
                new Integer[]{1, 2, 4, 8, 16}
        );
        assertArrayEquals(
                rangeWithStepLogic(2, 100, (i) -> i * i).toArray(),
                new Integer[]{2, 4, 16}
        );
    }

    @Test
    public void usingGenericSeriesWithLogic() throws Exception {
        assertArrayEquals(
                genericSeriesWithLogic(1, seed -> seed + seed > 20 ? null : seed + seed).toArray(),
                new Integer[]{1, 2, 4, 8,16});

    }


}



