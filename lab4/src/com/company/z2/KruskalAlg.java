package com.company.z2;

public class KruskalAlg {
    private final ListQueue<Edge> mst = new ListQueue<Edge>();
    MinPriorityQueue<Edge> pq = new MinPriorityQueue<Edge>();
    private double weight;

    public KruskalAlg(GraphWeighted G) {
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UnionFind uf = new UnionFind(G.V()); //sprawdzenie cykli, klada do zaimplementowania
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.weight();
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
}
