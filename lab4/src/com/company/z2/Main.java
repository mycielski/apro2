package com.company.z2;

public class Main {

    public static void main(String[] args) {
        GraphWeighted G = new GraphWeighted(8);
        G.addExEdge(G);
        System.out.println(G);
        KruskalAlg mst = new KruskalAlg(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.2f\n", mst.weight());
    }
}
