package ua.edu.ucu.stream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsIntStreamTest {

    private IntStream mainStream;

    @Before
    public void setUp() throws Exception {
        mainStream = AsIntStream.of(1, 2, 3, 4, 5, 6, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void average() throws IllegalArgumentException{
        double realValue = mainStream.filter(x -> x % 2 == 0).
                map(x -> 2*x).
                average();

        double expectedValue = 8.0;
        assertEquals(expectedValue, realValue, 0.001);
        realValue = mainStream.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void max() {
        int realValue = mainStream.filter(x -> x < 3).flatMap(x ->
                AsIntStream.of(x -1 , x, x + 1)).max();
        int expectedValue = 3;
        assertEquals(expectedValue, realValue);
        realValue = mainStream.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void min() {
        int realValue = mainStream.filter(x -> x > 3).flatMap(x ->
                AsIntStream.of(x -1 , x, x + 1)).min();
        int expectedValue = 3;
        assertEquals(expectedValue, realValue);
        realValue = mainStream.min();
    }

    @Test
    public void count() {
        long realValue = mainStream.filter(x -> x > 4).flatMap(x ->
                AsIntStream.of(x -1 , x, x + 1)).count();
        long expectedValue = 9;
        assertEquals(expectedValue, realValue);
    }


    @Test
    public void toArray() {
        int[] realValue = mainStream.filter(x -> x > 4).flatMap(x ->
                AsIntStream.of(x -1 , x, x + 1)).map(x -> x + 1).toArray();
        int[] expectedValue = new int[]{5, 6, 7, 6, 7, 8, 7, 8, 9};
        assertArrayEquals(expectedValue, realValue);
    }


    @Test
    public void sum() {
        int realValue = mainStream.filter(x -> x > 6).flatMap(x ->
                AsIntStream.of(x - 7, x , x + 7)).sum();
        int expectedValue = 21;
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void filter() {
        int[] realValue = mainStream.filter(x -> x < 3).filter(x -> x % 2 == 0).toArray();
        int[] expectedValue = new int[]{2};
        assertArrayEquals(expectedValue, realValue);
    }

    @Test
    public void forEach() {
        StringBuilder stringBuilder = new StringBuilder();
        mainStream.filter(x -> x % 3 == 0).forEach(stringBuilder::append);
        String realValue = stringBuilder.toString();
        String expectedValue = "36";
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void map() {
        int[] realValue = mainStream.map(x -> x - 1).map(x-> x*2).toArray();
        int[] expectedValue = new int[]{0, 2, 4, 6, 8, 10, 12};
        assertArrayEquals(expectedValue, realValue);

    }

    @Test
    public void flatMap() {
        int[] realValue = mainStream.filter(x -> x > 6).flatMap(x -> AsIntStream.of(x, x*2, x*3)).toArray();
        int[] expectedValue = new int[]{7, 14, 21};
        assertArrayEquals(expectedValue, realValue);
    }

    @Test
    public void reduce() {
        int realValue = mainStream.filter(x -> x > 5).reduce(4, Integer::sum);
        int expectedValue = 17;
        assertEquals(expectedValue, realValue);
    }

}