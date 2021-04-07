package com.company.zad2;


import java.util.ArrayList;

public class Graph {

    private final int vertices,edges;
    private final int[][] adjacencyMatrix;

    /**
     * Initializes the graph based on its number of vertices and edges.
     * @param vertices total number of vertices in this graph
     * @param edges total number of edges in this graph
     */
    public Graph(int vertices, int edges, int[][] adjacencyMatrix) {
        this.vertices = vertices;
        this.edges = edges;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    /**
     * Uses Dijkstra's algorithm to find the shortest path between given vertices
     * @param sourceVertexIndex index of vertex where we start
     */
    public void dijsktraComputeShortestPath(int sourceVertexIndex, int destinationVertexIndex) {
        boolean[] shortestPathTree = new boolean[vertices];
        int[] distance = new int[vertices];
        final int INFINITY = Integer.MAX_VALUE;
        ArrayList<Integer> list = new ArrayList<>();

        // initialize every distance to be infinite
        for (int i = 0; i < distance.length; i++) distance[i] = INFINITY;

        // distance from source vertex to itself is of course 0
        distance[sourceVertexIndex] = 0;

        //create shortest path tree
        for (int i = 0; i < vertices; i++){

            //get the vertex with minimum distance
            int vertexU = getMinimumDistanceVertex(shortestPathTree,distance);

            // add this vertex to the shortest path tree
            shortestPathTree[vertexU] = true;

            // iterate through all the adjacent vertices of above vertex and update the keys
            for (int vertexV = 0; vertexV < vertices; vertexV++){
                //check if edge between vertexU and vertexV exists
                if(adjacencyMatrix[vertexU][vertexV] > 0) {
                    //check if vertexV is already in the shortest path tree and if distance[vertexV]!=Infinity
                    if(shortestPathTree[vertexV] == false && adjacencyMatrix[vertexU][vertexV] != INFINITY){
                        //check whether distance needs to be updated

                        int newKey = adjacencyMatrix[vertexU][vertexV] + distance[vertexU];
                        if (newKey < distance[vertexV]) distance[vertexV] = newKey;
                    }
                }
            }
        }
        // prints the path
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Start: ").append(sourceVertexIndex);

    }

    /**
     *  get the not yet included in shortest path tree vertex with minimum distance
     * @param minimumSpanningTree
     * @param key
     * @return index of vertex with minimum distance
     */
    int getMinimumDistanceVertex(boolean[] minimumSpanningTree, int[] key){
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < vertices; i++){
            if (minimumSpanningTree[i] == false && minKey>key[i]){
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

}
