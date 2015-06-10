package yang.al.ch05;

import yang.al.ch01.Bag;
import yang.al.ch01.Stack;
import yang.al.ch04.Digraph;
import yang.al.ch04.DirectedDFS;

/**
 * Created by root on 15-6-10.
 */
public class NFA {
    private Digraph G;
    private String regexp;
    private int M;

    public NFA(String regexp) {
        this.regexp = regexp;
        M = regexp.length();
        Stack<Integer> ops = new Stack<Integer>();
        G = new Digraph(M+1);
        for (int i = 0; i < M; i++) {
            int lp = i;
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|') {
                ops.push(i);
            } else if (regexp.charAt(i) == ')') {
                int or = ops.pop();

                if (regexp.charAt(or) == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                } else if (regexp.charAt(or) == '(') {
                    lp = or;
                } else {
                    assert false;
                }
            }

            if (i < M - 1 && regexp.charAt(i+1) == '*') {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')') {
                G.addEdge(i, i+1);
            }
        }
    }

    public boolean recognizes(String txt) {
        DirectedDFS dfs = new DirectedDFS(G, 0);
        Bag<Integer> pc = new Bag<Integer>();
        for (int v = 0;  v < G.V();  v++) {
            if (dfs.marked(v)) pc.add(v);
        }

        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<Integer>();
            for (int v: pc) {//必然有至少一个字幕态（DFS）
                if (v == M) continue;
                if (regexp.charAt(v) == txt.charAt(i) || regexp.charAt(i) == '.' ) {
                    match.add(v+1);
                }
            }
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) pc.add(v);
            }

            if (pc.size() == 0) return false;
        }

        for (int v: pc) if (v == M) return true;
        return false;
    }
}
