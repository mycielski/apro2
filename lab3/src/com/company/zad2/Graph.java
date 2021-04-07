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

    /**
     * prints out the path between two vertices
     *
     * @param destination index of destination vertex
     * @param source      index of source vertex
     */
    /*public void printShortestPathBetweenTwoVertices(int destination, int source) {
        for (Integer v : adjacencyList.get(source)) {
            if (v.equals(destination)) {
                System.out.println(source + " " + destination);
                return;
            }
        }
        int[] predecessor = new int[vertices];
        int[] distance = new int[vertices];

        if (!BFS(destination, source, predecessor, distance)) {
            System.out.println("Podane wierzchołki nie są połączone.");
            return;
        }
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = source;
        path.add(crawl);
        while (predecessor[crawl] != -1) {
            path.add(predecessor[crawl]);
            crawl = predecessor[crawl];
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer stop : path) {
            stringBuilder.append(stop).append(" ");
        }
        System.out.println(stringBuilder.toString());

    }*/
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
    /*
    private boolean BFS(int source, int destination, int[] predecessor, int[] distance) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = -1;
        }

        visited[source] = true;
        distance[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adjacencyList.get(u).size(); i++) {
                if (!visited[adjacencyList.get(u).get(i)]) {
                    visited[adjacencyList.get(u).get(i)] = true;
                    distance[adjacencyList.get(u).get(i)] = distance[u] + 1;
                    predecessor[adjacencyList.get(u).get(i)] = u;
                    queue.add(adjacencyList.get(u).get(i));

                    // break upon finding destination
                    if (adjacencyList.get(u).get(i).equals(destination)) return true;
                }
            }
        }
        return false;
    }

     */
}
