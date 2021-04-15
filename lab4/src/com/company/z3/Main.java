package com.company.z3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int V = 9; // Number of vertices in graph
        int E = 12; // Number of edges in graph

        GraphDirectedWeighted graph = new GraphDirectedWeighted(V, E);

        ArrayList<GraphDirectedWeighted.Vertex> vertices = new ArrayList<GraphDirectedWeighted.Vertex>();
        for (int i = 0; i < V; i++) {
            vertices.add(new GraphDirectedWeighted.Vertex(i));
        }

        for (int i = 0; i < 12; i++) {

            int source = generateRandomInt(0, V);
            graph.edge[i].src = source;

            int destination = generateRandomInt(0, V);
            graph.edge[i].dest = destination;
            while (graph.edge[i].dest == graph.edge[i].src) {
                destination = generateRandomInt(0, V);
                graph.edge[i].dest = destination;
            }

            int weight = generateRandomInt(1, 30);
            graph.edge[i].weight = weight;

            vertices.get(source).outboundEdges.add(destination);
            vertices.get(source).outboundEdges.add(weight);
            vertices.get(destination).inboundEdges.add(source);
            vertices.get(destination).inboundEdges.add(weight);

            if (i == 12 && !graph.checkIfGraphIsConnected()) {
                i = 0;
                vertices.clear();
                for (int j = 0; j < V; j++) {
                    vertices.add(new GraphDirectedWeighted.Vertex(j));
                }
            }
        }

        int choice = 3;
        while (choice != 0) {
            System.out.println("""
                    Wybierz co chcesz zrobić:
                    1 - wypisz graf na konsolę
                    2 - znajdź najkrótsze drogi do wierzchołków
                    0 - wyjście z programu
                    """);
            choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    System.out.println("GRAF:");
                    for (GraphDirectedWeighted.Vertex vertex :
                            vertices) {
                        System.out.println("Wierzchołek #" + vertex.index + ":\n" +
                                "krawędzie przychodzące z wierzchołków o numerach:");
                        for (int j = 0; j < vertex.inboundEdges.size()/2; j++) {
                            System.out.println(vertex.inboundEdges.get(2*j) + ", waga: " + vertex.inboundEdges.get(2*j + 1));
                        }
                        System.out.println("krawędzie wychodzące do wierzchołków o numerach:");
                        for (int j = 0; j < vertex.outboundEdges.size()/2; j++) {
                            System.out.println(vertex.outboundEdges.get(2*j) + ", waga: " + vertex.outboundEdges.get(2*j + 1));
                        }
                    }
                    System.out.println();
                    break;
                case 2:
                    graph.BellmanFord(graph, 0,false);
                    break;
                case 0:
                    System.exit(0);
            }

        }
        graph.BellmanFord(graph, 0,false);
    }

    public static int generateRandomInt(int max, int min) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
