package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AsIntStream implements IntStream {

    private Iterator<Integer> iter;

    private AsIntStream() { }

    private AsIntStream(Iterator<Integer> iter) {
        this.iter = iter;
    }

    public static IntStream of(int... values) {

        List<Integer> list = new ArrayList<>();
        for (int value : values) {
            list.add(value);
        }
        return new AsIntStream(list.iterator());
    }

    private void exceptionThrower() {
        if (!iter.hasNext()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Double average() {
        exceptionThrower();
        int sum = 0;
        int counter = 0;
        while (iter.hasNext())
        {
            Integer key = iter.next();
            if (key != null) {

                sum += key;
                counter++;
            }

        }
        return (double)sum/counter;
    }

    private Integer findValue(IntComparable comparable, int keyValue) {
        exceptionThrower();
        int key = keyValue;
        while (iter.hasNext())
        {
            Integer value = iter.next();
            if (value == null) {
                return key;
            }
            if (comparable.compare(value, key))
            {
                key = value;
            }
        }
        return key;
    }

    @Override
    public Integer max() {
        return findValue((x, y) -> x > y, Integer.MIN_VALUE);
    }

    @Override
    public Integer min() {
        return findValue((x, y) -> x < y, Integer.MAX_VALUE);
    }

    @Override
    public long count() {
        long counter = 0;
        while (iter.hasNext())
        {
            if (iter.next() != null) {
                counter++;
            }

        }
        return counter;
    }

    @Override
    public Integer sum() {
        exceptionThrower();
        Integer sum = 0;
        while (iter.hasNext())
        {
            sum += iter.next();
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {

        return new AsIntStream(new IterFilter(iter, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (iter.hasNext())
        {
            Integer x = iter.next();
            if (x != null) {
                action.accept(x);
            }

        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new IterMap(iter, mapper));
    }

    @Override
    public AsIntStream flatMap(IntToIntStreamFunction func) {

        return new AsIntStream(
                new IterFlatMap(iter, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int initValue = identity;
        while (iter.hasNext()) {
            Integer x = iter.next();
            if (x != null) {
                initValue = op.apply(initValue, x);
            }

        }
        return initValue;
    }

    public Iterator<Integer> toIter() {
        return iter;
    }

    @Override
    public int[] toArray() {
        List<Integer> list = new ArrayList<>();
        while (iter.hasNext()) {
            Integer x = iter.next();
            if (x != null) {
                list.add(x);
            }
        }
        int[] newArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newArray[i] = list.get(i);
        }
        return newArray;
    }

}
