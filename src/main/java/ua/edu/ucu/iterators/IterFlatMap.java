package ua.edu.ucu.iterators;


import ua.edu.ucu.function.IntToIntStreamFunction;
import java.util.Iterator;


public class IterFlatMap implements Iterator<Integer> {

    private IntToIntStreamFunction streamFunction;
    private final Iterator<Integer> iter;
    private Iterator<Integer> iterValue;


    public IterFlatMap(Iterator<Integer> iter,
                       IntToIntStreamFunction streamFunction) {
        this.streamFunction = streamFunction;
        this.iter = iter;

        if (iter.hasNext()) {
            iterValue = streamFunction.applyAsIntStream(
                    iter.next()).toIter();
        } else {
            iterValue = iter;
        }

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
