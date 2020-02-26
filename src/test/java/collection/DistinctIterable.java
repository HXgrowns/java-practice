package collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DistinctIterable<Character> implements Iterable<Character> {
    private Iterable<Character> iterable;

    public DistinctIterable(Iterable<Character> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<Character> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<Character> toList() {
        List<Character> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class DistinctIterator<Character> implements Iterator<Character> {
    // TODO: Implement the class to pass the test.
    // <--start
    private final Iterator<Character> iterator;
    private Set<Character> set;
    private Character distinctElement;


    DistinctIterator(Iterator<Character> iterator) {
        this.iterator = iterator;
        this.set = new HashSet<>();
    }

    @Override
    public boolean hasNext() {
        while (this.iterator.hasNext()) {
            distinctElement = this.iterator.next();
            if (set.add(distinctElement)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Character next() {
        return distinctElement;
    }

    // --end->
}