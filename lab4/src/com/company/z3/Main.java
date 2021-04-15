package com.company.z3;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        int numberOfVertices = 9;
        int numberOfEdges = 26;

        DigraphWeighted G = new DigraphWeighted(numberOfVertices);

        for (int i = 0; i < numberOfEdges; i++) {
            G.addEdge(new DirectEdge(generateRandomInt(0,numberOfVertices), generateRandomInt(0,numberOfVertices),
                    generateRandomInt(1,30)));
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
                    System.out.println("source->destination weight");
                    for (Bag<DirectEdge> bag :
                            G.getAdj()) {
                        System.out.println(bag);
                    }
                    break;
                case 2:
                    System.out.println("Z którego wierzchołka ruszamy?");
                    int s = new Scanner(System.in).nextInt();
                    System.out.println("""
                            Jak dokładny opis procesu chcesz zobaczyć?
                            1 - niedokładny
                            2 - dokładny""");
                    int b = new Scanner(System.in).nextInt();
                    boolean beVerbose = b == 2;
                    BellmanFordAlg bf = new BellmanFordAlg(G, 1, beVerbose);
                    for (int v = 0; v < G.V(); v++) {
                        if (bf.hasPathTo(v)) {
                            System.out.printf("Z %d do %d (%5.2f) ", s, v,
                                    bf.distTo(v));
                            for (DirectEdge e : bf.pathTo(v)) {
                                System.out.print(e + " ");
                            }
                            System.out.println();
                        } else {
                            System.out.printf("Nie istnieje ścieżka z %d do %d\n", s, v);
                        }
                    }
                    break;
                case 0:
                    System.exit(0);
            }

        }

    }


    public static int generateRandomInt(int max, int min) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
