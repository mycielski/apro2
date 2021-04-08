package com.company.zad2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private final int vertices, edges;
    private final ArrayList<ArrayList<Integer>> adjacencyList;

    /**
     * Initializes the graph based on its number of vertices and edges.
     *
     * @param vertices total number of vertices in this graph
     * @param edges    total number of edges in this graph
     */
    public Graph(int vertices, int edges, ArrayList<ArrayList<Integer>> adjacencyList) {
        this.vertices = vertices;
        this.edges = edges;
        this.adjacencyList = adjacencyList;
    }

    public void printPathBetweenVertices(int source, int destination) {
        if (source > destination){
            int tmp = source;
            source = destination;
            destination = tmp;
        }
        boolean[] visited = new boolean[vertices];
        ArrayList<Integer> path = new ArrayList<Integer>();
        if (pathfinder(source, destination, visited, path) == -1) {
            System.out.println("Wierzchołki nie są połączone.");
        } else {
            System.out.println(source);
        }
    }

    public int pathfinder(int source, int destination, boolean[] visited, ArrayList<Integer> path) {
        if (!visited[source]) {
            visited[source] = true;
            for (Integer vertex : adjacencyList.get(source)) {
                if (vertex == destination) {
                    System.out.print(destination + " ");
                    path.add(destination);
                    return source;
                }
            }
            for (Integer vertex : adjacencyList.get(source)) {
                int findings =  pathfinder(vertex, destination, visited, path);
                if (findings == -1) {
                } else {
                    System.out.print(findings + " ");
                    path.add(findings);
                    return source;
                }
            }
        }
        return -1;
    }

    public int countDisjointedSubgraphs() {
        boolean[] visited = new boolean[vertices];
        int count = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int current = queue.remove();
                    visited[current] = true;
                    for (Integer vertex : adjacencyList.get(current)) {
                        if (!visited[vertex]) queue.add(vertex);
                    }
                }
                count++;
            }
        }
        return count;
    }
}
