package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class zad3 {

    public static void main(String[] args) {
        Numbers numbers = new Numbers(654);
        System.out.println(numbers.getMedian());
        numbers.insertNumber(656);
        System.out.println(numbers.getMedian());
        numbers.insertNumber(958);
        System.out.println(numbers.getMedian());
        numbers.insertNumber(699);
        System.out.println(numbers.getMedian());
        numbers.removeMedianNumber();
        System.out.println(numbers.getMedian());
    }
}

class Numbers {

    private int median;
    private boolean even = true;
    private final ArrayList<Integer> maxHeap = new ArrayList<Integer>();
    private final ArrayList<Integer> minHeap = new ArrayList<Integer>();

    public Numbers(int firstNumber) {
        median = firstNumber;
        even = false;
    }

    public double getMedian() {
        if (!even) return median;
        return (Collections.min(minHeap) / 2 + Collections.max(maxHeap) / 2);
    }

    public void insertNumber(int number) {
        if (even) {
            if (number < Collections.min(minHeap) && number > Collections.max(maxHeap)) {
                median = number;
            } else if (number <= Collections.max(maxHeap)) {
                median = Collections.max(maxHeap);
                maxHeap.remove(Collections.max(maxHeap));
                maxHeap.add(number);
            } else if (number >= Collections.min(minHeap)) {
                median = Collections.min(minHeap);
                minHeap.remove(Collections.min(minHeap));
                minHeap.add(number);
            }
        } else {
            maxHeap.add(Math.min(number, median));
            minHeap.add(Math.max(number, median));
        }
        even = !even;
    }

    public void removeMedianNumber() {
        try {
            if (even) {
                minHeap.remove(Collections.min(minHeap));
                maxHeap.remove(Collections.max(maxHeap));
                median = Collections.min(minHeap) / 2 + Collections.max(maxHeap) / 2;
            } else {
                median = Collections.min(minHeap) / 2 + Collections.max(maxHeap) / 2;
            }
            even = !even;
        } catch (NoSuchElementException e){
            System.out.println("There are no more numbers to remove.");
        }
    }
}
