package com.company.zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Graph{

    private ArrayList<Node> list = new ArrayList();
    private int nodes=0, edges;

    public Graph(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(reader.readLine() !=null){
            if (line.indexOf('#') == 1) {
                continue;
            } else if (line.indexOf(' ') != -1) {
                String[] parts = line.split(" ");

                continue;
            } else {
                if (nodes == 0) {
                    nodes = Integer.parseInt(line);
                    for (int i = 0; i < nodes; i++) list.add(new Node(i));
                } else {
                    edges = Integer.parseInt(line);
                }
            }
        }
    }

    class Node {

        private int nodeIndex;
        private ArrayList<Node> connectedNodes = new ArrayList<>();

        public Node(int nodeIndex) {
            this.nodeIndex = nodeIndex;
        }
    }
}
