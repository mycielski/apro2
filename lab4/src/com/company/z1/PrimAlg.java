package com.company.z1;

import java.util.Queue;

public class PrimAlg {
    private double weight; // waga MST
    private ListQueue<Edge> mst; // krawedzie MST
    private boolean[] marked; // marked[v] - wierzcholek w MST
    private MinPriorityQueue<Edge> pq; // krawedzie przekroju, dla wierzcholkow w drzewie
    public PrimAlg(GraphWeighted G) {
        mst = new ListQueue<Edge>();
        pq = new MinPriorityQueue<Edge>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) //alg. dla lasu MST
                prim(G, v);
    }
    private void prim(GraphWeighted G, int s) {
        scan(G, s); //skanowanie wierzcholka zrodlowego
        while (!pq.isEmpty()) {
            Edge e = pq.delMin(); //najlzejsza krawedz
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w])
                continue; // obwa wierzcholki krawedzi przeskanowane
            mst.enqueue(e); //dodanie krawedzi do MST
            weight += e.weight();
            if (!marked[v]) scan(G, v); //przeskanowanie v
            if (!marked[w]) scan(G, w); //przeskanowanie w
        }
    }
    //dodanie wszystkich krawedzi wychodzacych z v prowadzacych do wierzolkow nieprzeskanownaych
    private void scan(GraphWeighted G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }
    public Iterable<Edge> edges() {
        return mst;
    }
    public double weight() {
        return weight;
    }

}

