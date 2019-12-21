package Main;

import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import javax.lang.model.type.NullType;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        IntStream asIntStream = AsIntStream.of(2, 1,35, 28, 1).flatMap(x -> AsIntStream.of (x-1, x, x + 1));
        int[] x = asIntStream.toArray();
        for (int y: x) {
            System.out.println(y);
        }

    }
}
