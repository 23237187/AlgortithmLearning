package yang.al.ch06;

/**
 * Created by root on 15-6-11.
 */
public class FlowEdge {
    private final int v;
    private final int w;
    private final double capacity;
    private double flow;

    public FlowEdge(int v, int w, double capacity) {
        if (v < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (w < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (!(capacity >= 0.0)) throw new IllegalArgumentException("Edge capacity must be nonnegaitve");
        this.v = v;
        this.w = w;
        this.capacity = capacity;
    }

    public FlowEdge(int v, int w, double capacity, double flow) {
        if (v < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (w < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (!(capacity >= 0.0)) throw new IllegalArgumentException("Edge capacity must be nonnegaitve");
        if (!(flow <= capacity)) throw new IllegalArgumentException("Flow exceeds capacity");
        if (!(flow >= 0.0))      throw new IllegalArgumentException("Flow must be nonnnegative");
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = flow;
    }

    public FlowEdge(FlowEdge e) {
        this.v = e.v;
        this.w = e.w;
        this.capacity = e.capacity;
        this.flow = e.flow;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    public double residualCapacityTo(int vertex) {
        if (vertex == v) return flow;
        else if (vertex == w) return capacity - flow;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v) flow -= delta;//负向增加流量 意味着减少流量
        else if (vertex == w) flow += delta;
        else throw new IllegalArgumentException("Illegal endpoint");
        if (Double.isNaN(delta)) throw new IllegalArgumentException("Change in flow = NaN");
        if (!(flow >= 0.0)) throw new IllegalArgumentException("Flow is negative");
        if (!(flow <= capacity)) throw new IllegalArgumentException("Flow exceeds capacity");
    }


}
