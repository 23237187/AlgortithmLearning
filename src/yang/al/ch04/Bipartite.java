package yang.al.ch04;

import yang.al.ch01.Stack;

/**
 * Created by root on 15-6-4.
 */
public class Bipartite {
    private boolean isBipartite;
    private boolean[] color;
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public Bipartite(Graph G) {
        isBipartite = true;
        color = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }

        assert check(G);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public boolean color(int v) {
        if (!isBipartite)
            throw new UnsupportedOperationException("Graph is not bipartite");
        return color[v];
    }

    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    private boolean check(Graph G) {
        if (isBipartite) {
            for (int v = 0; v < G.V(); v++) {
                for (int w: G.adj(v)) {
                    if (color[v] == color[w]) {
                        System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", v, w, v, w);
                        return false;
                    }
                }
            }
        } else {
            int first = -1, last = -1;
            for (int v: oddCycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }

        return true;


    }
}
