package yang.al.ch03;

import yang.al.ch01.Queue;

/**
 * Created by root on 15-6-3.
 */
public class SepaSeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public SepaSeparateChainingHashST(){
        this(INIT_CAPACITY);
    }

    public SepaSeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    private void resize(int chains) {
        SepaSeparateChainingHashST<Key, Value> temp = new SepaSeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M = temp.M;
        this.N = temp.N;
        this.st = temp.st;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    private void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }

        if (N >= 10*M) resize(2*M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, value);
    }

    public void delete(Key key) {
        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);

        if (M > INIT_CAPACITY && N <= 2*M) resize(M/2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key: st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }

}
