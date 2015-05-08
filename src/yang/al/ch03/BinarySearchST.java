package yang.al.ch03;


import yang.al.ch01.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(){
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity){
        assert capacity >= N;
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++){
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public Value get(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else
            return null;
    }

    public int rank(Key key){
        int lo = 0, hi = N - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0){
                hi = mid - 1;
            }
            else if (cmp > 0){
                lo = mid + 1;
            }
            else
                return mid;
        }
        return lo;
    }

    public void put(Key key, Value val){
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }

        if (N == keys.length) {
            resize(2*keys.length);
        }
        for (int j = N; j > i; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key){
        if (isEmpty()) return;
        int i = rank(key);
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < N-1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        N--;
        keys[N] = null;
        vals[N] = null;

        if (N > 0 && N == keys.length/4) resize(keys.length/2);
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public Key select(int k){
        if (k < 0 || k >= N) return null;
        return keys[k];
    }

    public Key ceiling(Key key){
        int i = rank(key);
        if (i == N) return null;
        return keys[i];
    }

    public Key floor(Key key){
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0){
            return keys[i];
        } else {
            if (i == 0) {
                return null;
            } else {
                return keys[i-1];
            }
        }
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++){
            q.enqueue(keys[i]);
        }
        if (contains(hi)){
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }
}
