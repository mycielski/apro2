package com.company.z2;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class KruskalAlg {
    private final ListQueue<Edge> mst = new ListQueue<Edge>();
    MinPriorityQueue<Edge> pq = new MinPriorityQueue<Edge>();
    private double weight;

    public KruskalAlg(GraphWeighted G) {
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UnionFind uf = new UnionFind(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                mst.enqueue(e);
                weight += e.weight();
            }
        }
    }

    public Iterable<Edge> getEdges() {
        return mst;
    }

    public double getWeight() {
        return weight;
    }
}
