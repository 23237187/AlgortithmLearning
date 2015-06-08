package yang.al.ch04;

import yang.al.stdlib.StdIn;
import yang.al.stdlib.StdOut;

/**
 * Created by root on 15-6-8.
 */
public class CPM {
    private CPM() {}

    public static void main(String[] args) {
        int N = StdIn.readInt();

        int source = 2*N;
        int sink = 2*N + 1;

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N + 2);
        for (int i = 0; i < N; i++) {
            double duration = StdIn.readDouble();
            G.addEdge(new DirectedEdge(source, i, 0.0));
            G.addEdge(new DirectedEdge(i + N, sink, 0.0));
            G.addEdge(new DirectedEdge(i, i+N, duration));

            int M = StdIn.readInt();
            for (int j = 0; j < M; j++) {
                int precedent = StdIn.readInt();
                G.addEdge(new DirectedEdge(N+i, precedent, 0.0));
            }
        }

        AcyclicLP lp = new AcyclicLP(G, source);
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i+N));
        }
        StdOut.printf("Finish time: %7.1f\n", lp.distTo(sink));
    }

}
