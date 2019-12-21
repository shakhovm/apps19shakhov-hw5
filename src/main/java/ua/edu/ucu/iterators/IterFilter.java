package ua.edu.ucu.iterators;

import java.util.Iterator;
import ua.edu.ucu.function.IntPredicate;

public class IterFilter implements Iterator<Integer> {

    private IntPredicate predicate;
    private final Iterator<Integer> iter;


    public IterFilter(Iterator<Integer> iter, IntPredicate predicate) {
        this.predicate = predicate;
        this.iter = iter;
    }

    @Override
    public boolean hasNext() {

        return iter.hasNext();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        Integer value = iter.next();
        if (value == null) {
            return null;
        }
        if (!predicate.test(value)) {

            value = next();
        }
        return value;
    }


}
