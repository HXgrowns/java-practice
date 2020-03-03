package collection;

import java.util.Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Sequence implements Iterable<Integer> {
    private final Integer start;
    private final Integer end;

    public Sequence(Integer start, Integer end) {
        if (start >= end) {
            throw new IllegalArgumentException("Start must be smaller than End.");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {

      return new SequenceIterator(start, end);
    }
}

class SequenceIterator implements Iterator<Integer> {
    private Integer current;
    private final Integer end;

    SequenceIterator(Integer start, Integer end) {
        // TODO: please implements the following code to pass the test
        // <--start
        if (start >= end) {
            throw new IllegalArgumentException("Start must be smaller than End.");
        }
        this.current = start;
        this.end = end;
        // --end-->
    }

    @Override
    public boolean hasNext() {
        // TODO: please implements the following code to pass the test
        // <--start
        return this.current <= this.end - 1;
        // --end-->
    }

    @Override
    public Integer next() {
        // TODO: please implements the following code to pass the test
        // <--start
        return this.current++;
        // --end-->
    }
}
