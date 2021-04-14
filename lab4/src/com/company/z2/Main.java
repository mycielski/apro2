package com.company.z2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        GraphWeighted G = generateGraphWeightedFromFile("g250.txt");
        System.out.println(G);
        KruskalAlg mst = new KruskalAlg(G);
        for (Edge e : mst.getEdges()) {
            System.out.println(e);
        }
        System.out.printf("%.2f\n", mst.getWeight());
        System.out.println("Minimalne drzewo rozpinające:");
        for (Edge e :
                mst.getEdges()) {
            System.out.println(e);
        }
    }

    /**
     * Generates a weightedGraph from file
     *
     * @param filepath path to file
     * @return weightedGraph from file
     */
    public static GraphWeighted generateGraphWeightedFromFile(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine();
            int vertices = Integer.parseInt(line);
            GraphWeighted graphWeighted = new GraphWeighted(vertices);
            line = reader.readLine();
            int edges = Integer.parseInt(line);
            line = reader.readLine();
            for (int i = 0; i < edges; i++) {
                String[] edgeData = line.split(" ");
                int v = Integer.parseInt(edgeData[0]);
                int w = Integer.parseInt(edgeData[1]);
                double weight = Double.parseDouble(edgeData[2]);
                graphWeighted.addEdge(new Edge(v, w, weight));
                line = reader.readLine();
            }
            return graphWeighted;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
