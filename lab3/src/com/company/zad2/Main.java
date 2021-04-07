package com.company.zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // write your code here

    }

    /**
     * Generates a graph from a file
     *
     * @param pathToFile path to file
     * @return Graph generated from file
     */
    public static Graph generateGraphFromFile(String pathToFile) {
        BufferedReader reader;
        int vertices = 0;
        int edges = 0;
        int[][] adjacencyMatrix;
        try {
            reader = new BufferedReader(new FileReader(pathToFile));
            String line = reader.readLine();
            //skips through the beginning of file comments
            while (line.indexOf('#') == 0) line = reader.readLine();
            //parses the two lines describing the number of vertices and edges, initializes the adjacency matrix
            // based on the number of vertices
            vertices = Integer.parseInt(line);
            adjacencyMatrix = new int[vertices][vertices];
            line = reader.readLine();
            edges = Integer.parseInt(line);
            line = reader.readLine();
            while (line != null) {
                // if first char of line is a # ignores the line
                if (line.charAt(0) == '#') {
                    continue;
                } else {
                    // splits the line at space character and parses the parts of the line to integer, then updates
                    // the adjacency matrix
                    String[] parts = line.split(" ");
                    int[] adjacentVerticesIndexes = new int[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        adjacentVerticesIndexes[i] = Integer.parseInt(parts[i]);
                    }
                    for (int i = 1; i < adjacentVerticesIndexes.length; i++) {
                        // it's a non-directed graph so we make an edge in both ways
                        adjacencyMatrix[adjacentVerticesIndexes[0]][adjacentVerticesIndexes[i]] = 1;
                        adjacencyMatrix[adjacentVerticesIndexes[i]][adjacentVerticesIndexes[0]] = 1;
                    }
                }
                line = reader.readLine();
            }
            return new Graph(vertices, edges, adjacencyMatrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
