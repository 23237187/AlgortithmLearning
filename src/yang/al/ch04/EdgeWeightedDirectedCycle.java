package yang.al.ch04;

import yang.al.ch01.Stack;

/**
 * Created by root on 15-6-8.
 */
public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (cycle != null) return;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }
            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();
                while (e.from() != w) {
                    cycle.push(e);
                    e = edgeTo[e.from()];
                }
                cycle.push(e);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

}
