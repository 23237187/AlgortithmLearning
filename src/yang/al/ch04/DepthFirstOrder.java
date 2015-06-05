package yang.al.ch04;

import yang.al.ch01.Queue;
import yang.al.ch01.Stack;

import java.util.Iterator;

/**
 * Created by root on 15-6-5.
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private int[] pre;
    private int[] post;
    private Queue<Integer> preorder;
    private Queue<Integer> postorder;
    private int preCounter;
    private int postCounter;

    public DepthFirstOrder(Digraph G) {
        pre = new int[G.V()];
        post = new int[G.V()];
        preorder = new Queue<Integer>();
        postorder = new Queue<Integer>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        preorder.enqueue(v);
        pre[v] = preCounter++;

        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }

        postorder.enqueue(v);
        post[v] = postCounter++;
    }

    public int pre(int v) {
        return pre[v];
    }

    public int post(int v) {
        return post[v];
    }

    public Iterable<Integer> post() {
        return postorder;
    }

    public Iterable<Integer> pre() {
        return preorder;
    }

    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int v: postorder) {
            reverse.push(v);
        }
        return reverse;
    }
}
