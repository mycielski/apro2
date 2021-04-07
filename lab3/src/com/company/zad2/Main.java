package com.company.zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int choice = 1;
        System.out.println("Podaj ścieżkę do pliku z grafem");
        Scanner scanner = new Scanner(System.in);
        String filepath = scanner.nextLine();
        Graph graph = generateGraphFromFile(filepath);
        while (choice != 0){
            System.out.println("Wybierz co chcesz zrobić:");
            System.out.println("1 - liczenie spójnych składowych grafu");
            System.out.println("2 - szukanie ścieżki między wierzchołkami grafu");
            System.out.println("0 - wyjście z programu");
            choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    System.out.println(graph.countDisjointedSubgraphs());
                    break;
                case 2:
                    System.out.println("Podaj indeksy wierzchołków między którymi chcesz wyznaczyć ścieżkę.");
                    int v1 = scanner.nextInt();
                    int v2 = scanner.nextInt();
                    graph.printPathBetweenVertices(v1,v2);
                    //graph.printShortestPathBetweenTwoVertices(v2,v1);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nie rozumiem. Spróbuj ponownie.");
            }

        }
    }

    /**
     * Generates a graph from a file
     * @param pathToFile path to file
     * @return Graph generated from file
     */
    public static Graph generateGraphFromFile(String pathToFile) {
        BufferedReader reader;
        int vertices = 0;
        int edges = 0;
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();
        try {
            reader = new BufferedReader(new FileReader(pathToFile));
            String line = reader.readLine();
            //skips through the beginning of file comments
            while (line.indexOf('#') == 0) line = reader.readLine();
            //parses the two lines describing the number of vertices and edges, initializes the adjacency list
            // based on the number of vertices
            vertices = Integer.parseInt(line);
            for (int i = 0; i < vertices; i++) adjacencyList.add(new ArrayList<Integer>());
            line = reader.readLine();
            edges = Integer.parseInt(line);
            line = reader.readLine();
            while (line != null) {
                // if first char of line is a # ignores the line
                if (line.charAt(0) == '#') {
                    continue;
                } else {
                    // splits the line at space character and parses the parts of the line to integer, then updates
                    // the adjacency list
                    String[] parts = line.split(" ");
                    for (int i = 1; i < parts.length; i++){
                        adjacencyList.get(Integer.parseInt(parts[0])).add(Integer.parseInt(parts[i]));
                        adjacencyList.get(Integer.parseInt(parts[i-1])).add(Integer.parseInt(parts[0]));
                    }
                }
                line = reader.readLine();
            }
            return new Graph(vertices,edges,adjacencyList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}