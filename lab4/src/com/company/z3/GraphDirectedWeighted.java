package com.company.z3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GraphDirectedWeighted {

    class Edge {

        int src, dest, weight;

        Edge(){
            src = dest = weight = 0;
        }

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    private int v, e;
    private int INFINITY = Integer.MAX_VALUE;
    ArrayList<ArrayList> paths;
    Edge[] edge;

    GraphDirectedWeighted(int v, int e){
        this.v = v;
        this.e = e;
        edge = new Edge[e];
        paths = new ArrayList<>();
        for (ArrayList<ArrayList> list : paths ){
            list.add(new ArrayList<ArrayList>());
        }
        for (int i = 0; i < v; i++) {
            ArrayList<Integer> path = new ArrayList<>();


        }
        for (int i = 0; i < e; i++) {
            edge[i] = new Edge();
        }
    }

    public void BellmanFord(GraphDirectedWeighted graph, int src){
        int v = graph.v;
        int e = graph.e;
        int dist[] = new int[v];
        ArrayList<ArrayList<Integer>> ways = new ArrayList<>();
        for (int i = 0; i <v; i++) {
            ways.add(new ArrayList<Integer>());
        }

        // default all distances to infinite
        for (int i = 0; i < v; ++i) {
            dist[i] = INFINITY;
        }

        // distance to source is 0
        dist[src] = 0;

        // find shortest paths
        for (int i = 1; i < v; ++i) {
            for (int j = 0; j < v; ++j) {
                int s = graph.edge[j].src;
                int d = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                System.out.println("Sprawdzam dystans między " + s + " a " + d);
                if (dist[s] != Integer.MAX_VALUE && dist[s] + weight < dist[d])
                    System.out.println("Dystans między " + s + " a " + d + " jest mniejszy niż dystans między " + src + " a " + d);
                    ways.get(d).add(s);
                    System.out.println("Dodaję " + s + " do ścieżki.");
                    dist[d] = dist[s] + weight;
            }
        }
        System.out.println("#############");
        for (int i = 0; i < ways.size(); i++){
            System.out.println("Trasa z " + src + " do " + i);
            System.out.println(src);
            List<Integer> list = ways.get(i).stream().distinct().collect(Collectors.toList());
            for (Integer stop :
                    list) {
                System.out.println(stop);
            }
            System.out.println(i);
        }

    }
}
