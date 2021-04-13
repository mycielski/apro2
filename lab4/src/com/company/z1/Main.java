package com.company.z1;

public class Main {

    public static void main(String[] args) {
        GraphWeighted G = new GraphWeighted(8);
        G.addExEdge(G); //do zaimplementowania - przykladowy graf
        PrimAlg mst = new PrimAlg(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.2f\n", mst.weight());

    }
}
