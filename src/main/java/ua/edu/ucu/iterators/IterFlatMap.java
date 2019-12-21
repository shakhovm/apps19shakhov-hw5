package ua.edu.ucu.iterators;

import ua.edu.ucu.stream.IntStream;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterFlatMap implements Iterator<Integer> {

    private IntToIntStreamFunction streamFunction;
    private final Iterator<Integer> iter;
    private Iterator<Integer> iterValue;


    public IterFlatMap(Iterator<Integer> iter, IntToIntStreamFunction streamFunction) {
        this.streamFunction = streamFunction;
        this.iter = iter;

        this.iterValue = !iter.hasNext() ? iter :streamFunction.applyAsIntStream(iter.next()).toIter();



    }

    @Override
    public boolean hasNext() {

        return iterValue.hasNext() || iter.hasNext();
    }

    @Override
    public Integer next() {
        if (!iterValue.hasNext()) {
            Integer x = iter.next();
            if (x == null) {
                return null;
            }
            iterValue = streamFunction.applyAsIntStream(x).toIter();
        }

        return iterValue.next();
    }
}
