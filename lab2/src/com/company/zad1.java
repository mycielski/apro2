package com.company;

import java.util.Arrays;

public class zad1 {

    public static void main(String[] args) {
        int[] randomArrayHS = generateRandomIntArray(100,1,100);
        int[] randomArrayMS = randomArrayHS;
        HeapSort hs = new HeapSort(randomArrayHS);
        Arrays.stream(hs.getArray()).forEach(System.out::println);

    }

    public static int[] generateRandomIntArray(int size, int min, int max){
        int[] output = new int[size];
        for (int i = 0; i < size; i++){
            output[i] = (int) (Math.random() * (max-min) + min);
        }
        return output;
    }

    public static int[] generatePartiallySortedIntArray(int size, int min, int max){
        int[] output = new int[size];
        for (int i = size/2; i < size; i++){
            output[i] = (int) (Math.random() * (max-min) + min);
        }
        for (int i = 0; i < size/2; i++){
            output[i] = i+1;
        }
        return output;
    }

    public static int[] generateSortedIntArray(int size){
        int[] output = new int[size];
        for (int i = 0; i < size; i++) output[i] = i;
        return output;
    }
}

class MergeSort{

}

class HeapSort {

    private final int[] array;

    public HeapSort(int[] a) {
        array = a;
        sort(array);
    }

    private void sort(int[] a){
        int n = a.length;
        for (int i = n/2 - 1; i>=0; i--){
            heap(a, n ,i);
        }
        for (int i = n - 1; i>0; i--){
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heap(a,i,0);
        }
    }

    private void heap(int[] a, int n, int i){
        int largest = i;
        int l = 2 *i+1;
        int r = 2*i+2;

        if (l < n && a[l]> a[largest]){
            largest = l;
        }
        if (r < n && a[r] > a[largest]){
            largest = r;
        }
        if (largest != i) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            heap(a,n,largest);
        }
    }

    public int[] getArray() {
        return array;
    }
}