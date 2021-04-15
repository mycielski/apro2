package com.company.z2;

import java.util.NoSuchElementException;

public class MinPriorityQueue<Key> {
    private Key[] pqueue; //tablica przechowuje klucze (elementy) od indeksu 1 do n
    private int n; //liczba elemenow w kolejce

    public MinPriorityQueue(int size) {
        pqueue = (Key[]) new Object[size + 1];
        n = 0;
    }

    public MinPriorityQueue() {
        this(1);
    }

    public MinPriorityQueue(Key[] keys) {
        n = keys.length;
        pqueue = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pqueue[i + 1] = keys[i];
        for (int k = n / 2; k >= 1; k--)
            moveDown(k);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key key) {
        pqueue[++n] = key; //wstawienie na koniec tablicy
        moveUp(n); //przesuniecie nowego elementu do gory w drzewie binarnym
        if (n == pqueue.length - 1) resize(2 * pqueue.length);
    }

    public Key delMin() {
        if (isEmpty()) throw new
                NoSuchElementException("Kolejka jest pusta");
        Key min = pqueue[1]; //zdjecie korzenia drzewa
        swap(1, n--); //przeniesienie ostatniego elementu
        moveDown(1); //przesunięcie elementu w dol drzewa
        pqueue[n + 1] = null; //porzadkowanie tablicy
        if ((n > 0) && (n == (pqueue.length - 1) / 4))
            resize(pqueue.length / 2);
        return min;
    }

    private void moveUp(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void moveDown(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Kolejka jest pusta");
        return pqueue[1];
    }

    private boolean greater(int i, int j) {
        return ((Comparable<Key>) pqueue[i]).compareTo(pqueue[j]) > 0;
    }

    private void swap(int i, int j) {
        Key tmp = pqueue[i];
        pqueue[i] = pqueue[j];
        pqueue[j] = tmp;
    }

    private void resize(int size) {
        Key[] temp = (Key[]) new Object[size];
        for (int i = 0; i <= n; i++)
            temp[i] = pqueue[i];
        pqueue = temp;
    }
}