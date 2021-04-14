package com.company.z2;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class KruskalAlg {
    private final LinkedList<Edge> mst = new LinkedList<Edge>();
    PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
    private double weight;

    public KruskalAlg(GraphWeighted G) {
        for (Edge e : G.edges()) {
            pq.add(e);
        }
        UnionFind uf = new UnionFind(G.V()); //sprawdzenie cykli, klasa do zaimplementowania
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                uf.union(v, w);
                mst.add(e);
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
