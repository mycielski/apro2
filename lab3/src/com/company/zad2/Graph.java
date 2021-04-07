package com.company.zad2;


import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    private final int vertices,edges;
    private final ArrayList<ArrayList<Integer>> adjacencyList;

    /**
     * Initializes the graph based on its number of vertices and edges.
     * @param vertices total number of vertices in this graph
     * @param edges total number of edges in this graph
     */
    public Graph(int vertices, int edges, ArrayList<ArrayList<Integer>> adjacencyList) {
        this.vertices = vertices;
        this.edges = edges;
        this.adjacencyList = adjacencyList;
    }

    /**
     *  prints out the path between two vertices
     * @param source index of starting vertex
     * @param destination index of destination vertex
     */
    public void printShortestPathBetweenTwoVertices(int source, int destination){
        int[] predecessor = new int[vertices];
        int[] distance = new int[vertices];

        if (!BFS(source,destination,predecessor,distance)) {
            System.out.println("Podane wierzchołki nie są połączone.");
            return;
        }
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = destination;
        path.add(crawl);
        while(predecessor[crawl] != -1){
            path.add(predecessor[crawl]);
            crawl = predecessor[crawl];
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer stop : path){
            stringBuilder.append(stop).append(" ");
        }
        System.out.println(stringBuilder.toString());

    }

    private boolean BFS(int source, int destination, int[] predecessor, int[] distance){
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++){
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = -1;
        }

        visited[source] = true;
        distance[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()){
            int u = queue.remove();
            for (int i = 0; i < adjacencyList.get(u).size(); i++){
                if (!visited[adjacencyList.get(u).get(i)]){
                    visited[adjacencyList.get(u).get(i)] = true;
                    distance[adjacencyList.get(u).get(i)] = distance[u] + 1;
                    predecessor[adjacencyList.get(u).get(i)] = u;
                    queue.add(adjacencyList.get(u).get(i));

                    // break upon finding destination
                    if(adjacencyList.get(u).get(i).equals(destination)) return true;
                }
            }
        }
        return false;
    }
}
