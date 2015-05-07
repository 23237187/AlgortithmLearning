package yang.al.ch03;


import yang.al.ch01.Queue;
import yang.al.stdlib.StdIn;
import yang.al.stdlib.StdOut;

import java.util.Iterator;


public class SequentialSearchST<Key, Value> {
    private int N;
    private Node first;

    private class Node{
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST(){}

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public Value get(Key key){
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val){
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)){
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public void delete(Key key){
        first = delete(first, key);
    }

    private Node delete(Node x, Key key){
        if (x == null) return null;
        if (key.equals(x.key)) {
            N--;
            return x.next;
        }
        x = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next){
            queue.enqueue(x.key);
        }
        return queue;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }


}
