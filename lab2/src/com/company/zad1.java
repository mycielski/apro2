package com.company;

public class zad1 {

    public static void main(String[] args) {
        System.out.println();
        int[] randomArrayHS = generateRandomIntArray(100, 1, 100);
        int[] randomArrayMS = randomArrayHS;
        HeapSort randomHS = new HeapSort(randomArrayHS);
        System.out.println("Random heap sort: \n substitutions: " + randomHS.getSubstitutions() + " \n comparisons: " + randomHS.getComparisons());
        //Arrays.stream(randomHS.getArray()).forEach(System.out::println);
        MergeSort randomMS = new MergeSort(randomArrayMS);
        System.out.println("Random merge sort: \n substitutions: " + randomMS.getSubstitutions() + " \n comparisons: " + randomMS.getComparisons());
        //Arrays.stream(randomMS.getArray()).forEach(System.out::println);
        System.out.println();
        int[] partiallySortedIntArrayHS = generatePartiallySortedIntArray(100, 1, 100);
        int[] partiallySortedIntArrayMS = partiallySortedIntArrayHS;
        HeapSort partiallyOrderedHS = new HeapSort(partiallySortedIntArrayHS);
        System.out.println("Partially sorted heap sort: \n substitutions: " + partiallyOrderedHS.getSubstitutions() + " \n comparisons: " + partiallyOrderedHS.getComparisons());
        //Arrays.stream(partiallyOrderedHS.getArray()).forEach(System.out::println);
        MergeSort partiallyOrderedMS = new MergeSort(partiallySortedIntArrayMS);
        System.out.println("Partially sorted merge sort: \n substitutions: " + partiallyOrderedMS.getSubstitutions() + " \n comparisons: " + partiallyOrderedMS.getComparisons());
        //Arrays.stream(partiallyOrderedMS.getArray()).forEach(System.out::println);
        System.out.println();
        int[] sortedIntArrayHS = generateSortedIntArray(100);
        int[] sortedIntArrayMS = sortedIntArrayHS;
        HeapSort sortedHS = new HeapSort(sortedIntArrayHS);
        System.out.println("Sorted heap sort: \n substitutions: " + sortedHS.getSubstitutions() + " \n comparisons: " + sortedHS.getComparisons());
        //Arrays.stream(sortedHS.getArray()).forEach(System.out::println);
        MergeSort sortedMS = new MergeSort(sortedIntArrayMS);
        System.out.println("Sorted merge sort: \n substitutions: " + sortedMS.getSubstitutions() + " \n comparisons: " + sortedMS.getComparisons());
        //Arrays.stream(sortedMS.getArray()).forEach(System.out::println);
        System.out.println();
    }

    public static int[] generateRandomIntArray(int size, int min, int max) {
        int[] output = new int[size];
        for (int i = 0; i < size; i++) {
            output[i] = (int) (Math.random() * (max - min) + min);
        }
        return output;
    }

    public static int[] generatePartiallySortedIntArray(int size, int min, int max) {
        int[] output = new int[size];
        for (int i = size / 2; i < size; i++) {
            output[i] = (int) (Math.random() * (max - min) + min);
        }
        for (int i = 0; i < size / 2; i++) {
            output[i] = i + 1;
        }
        return output;
    }

    public static int[] generateSortedIntArray(int size) {
        int[] output = new int[size];
        for (int i = 0; i < size; i++) output[i] = i;
        return output;
    }
}

class MergeSort {
    private int comparisons, substitutions;

    private final int[] array;

    public MergeSort(int[] a) {
        this.substitutions = 0;
        this.comparisons = 0;
        array = a;
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] a, int l, int r) {
        if (l < r) {
            comparisons++;
            int m = l + (r - l) / 2;
            substitutions++;
            sort(a, l, m);
            sort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    private void merge(int[] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        substitutions++;
        substitutions++;

        int[] left = new int[n1];
        int[] right = new int[n2];
        substitutions++;
        substitutions++;

        for (int i = 0; i < n1; ++i) {
            left[i] = a[l + i];
            substitutions++;
        }
        for (int i = 0; i < n2; ++i) {
            right[i] = a[m + i + 1];
            substitutions++;
        }

        int i = 0, j = 0;
        substitutions++;
        substitutions++;

        int k = l;
        substitutions++;

        while (i < n1 && j < n2) {
            comparisons++;
            comparisons++;
            if (left[i] <= right[j]) {
                comparisons++;
                a[k] = left[i];
                substitutions++;
                i++;
            } else {
                a[k] = right[j];
                substitutions++;
                j++;
            }
            k++;
        }
    }

    public int[] getArray() {
        return array;
    }

    public int getComparisons() {
        return this.comparisons;
    }

    public int getSubstitutions() {
        return this.substitutions;
    }
}

class HeapSort {

    private final int[] array;
    private int comparisons = 0, substitutions = 0;

    public HeapSort(int[] a) {
        array = a;
        sort(array);
    }

    private void sort(int[] a) {
        int n = a.length;
        substitutions++;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heap(a, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            substitutions++;
            substitutions++;
            substitutions++;
            heap(a, i, 0);
        }
    }

    private void heap(int[] a, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && a[l] > a[largest]) {
            comparisons++;
            comparisons++;
            largest = l;
            substitutions++;
        }
        if (r < n && a[r] > a[largest]) {
            comparisons++;
            comparisons++;
            largest = r;
            substitutions++;
        }
        if (largest != i) {
            comparisons++;
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            substitutions++;
            substitutions++;
            substitutions++;
            heap(a, n, largest);
        }
    }

    public int[] getArray() {
        return array;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSubstitutions() {
        return substitutions;
    }
}