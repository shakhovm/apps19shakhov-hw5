package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.Iterator;

public interface IntStream {

    Double average();

    Integer max();

    Integer min();
    
    IntStream flatMap(IntToIntStreamFunction func);

    long count();

    IntStream filter(IntPredicate predicate);

    void forEach(IntConsumer action);

    IntStream map(IntUnaryOperator mapper);

    int reduce(int identity, IntBinaryOperator op);

    Iterator<Integer> toIter();

    Integer sum();

    int[] toArray();
}
