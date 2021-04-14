package com.company.z2;

import java.util.Iterator;

public class GraphWeighted {

    private final int V;
    private final Bag<Edge>[] adj;
    private int E;

    public GraphWeighted(int V) {
        if (V < 0) throw new IllegalArgumentException("Blad! liczba nie moze byc ujemna.");
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Blad! wierzcholek nie nalezy do grafu.");
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        validVertex(v);
        validVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        validVertex(v);
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
            }
        }
        return list;
    }


}

class Edge implements Comparable {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("Blad! liczba nie moze byc ujemna.");
        if (w < 0) throw new IllegalArgumentException("Blad! liczba nie moze byc ujemna.");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Blad! to nie jest liczba.");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Blad! vertex nie jest elementem krawedzi.");
    }

    public int compareTo(Edge e) {
        return Double.compare(this.weight, e.weight);
    }

    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }

    @Override
    public int compareTo(Object o) {
        Edge e = (Edge) o;
        return Double.compare(this.weight, e.weight);
    }
}

class Bag<Item> implements Iterable<Item> {
    private Node first;  //wierzcholek stosu
    private int N;             //liczba elementow

    public static void main(String[] args) {
        Bag<String> lista = new Bag<>();
        lista.add("pierwszy");
        lista.add("drugi");
        lista.add("trzeci");
        lista.add("czwarty");
        for (String s : lista)
            System.out.println("el: " + s);
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class Node {
        //klasa wewnetrzna z def. wezlow
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }
}


