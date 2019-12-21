package ua.edu.ucu.iterators;

import java.util.Iterator;


import ua.edu.ucu.function.IntUnaryOperator;


public class IterMap implements Iterator<Integer> {
    private IntUnaryOperator intUnaryOperator;
    private final Iterator<Integer> iter;


    public IterMap(Iterator<Integer> iter, IntUnaryOperator intUnaryOperator) {

        this.intUnaryOperator = intUnaryOperator;
        this.iter = iter;
    }

    @Override
    public boolean hasNext() {

        return iter.hasNext();
    }

    @Override
    public Integer next() {
        Integer x = iter.next();
        if (x == null) {
            return null;
        }
        return intUnaryOperator.apply(x);
    }

}
