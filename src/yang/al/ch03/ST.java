package yang.al.ch03;

/**
 * Created by root on 15-6-5.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private TreeMap<Key, Value> st;

    public ST() {
        st = new TreeMap<Key, Value>();
    }

    public Value get(Key key) {
        if (key == null) throw new NullPointerException("called get() with null key");
        return st.get(key);
    }

    public void put(Key key, Value value) {
        if (key == null) throw new NullPointerException("called put() with null key");
        if (value == null) st.remove(key);
        else st.put(key, value);
    }

    public void delete(Key key) {
        if (key == null) throw new NullPointerException("called delete() with null key");
        st.remove(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("called contains() with null key");
        return st.containsKey(key);
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return st.lastKey();
    }

    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("called ceiling() with null key");
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }

    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("called floor() with null key");
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }
}
