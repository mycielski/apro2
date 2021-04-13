package com.company.z2;

public class UnionFind {
    private int[] id; //tablica indentyfikatorów składowych
                      //tablica indeksowana punktami
    private int count;//liczba składowych

    public UnionFind(int N) {
        count = N; //tyle składowych co punktów
        id = new int[N];
        for(int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count() { return count; }
	
	public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
	
    public int find(int p) {
        return id[p];
    }
    public void union(int p, int q) {
        //umieszczenie p i q w jednej składowej
        int pID = find(p);
        int qID = find(q);
        //brak działania jeśli p i q są w jednej składowej
        if (pID == qID) return;

        //zmiana nazwy składowej dla p na
        //nazwę składową do której należy q
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;
        count--;
    }


}
