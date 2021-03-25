package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class zad3 {

}

class Numbers {

    private int median;
    private boolean even = true;
    private ArrayList<Integer> maxHeap = new ArrayList<Integer>();
    private ArrayList<Integer> minHeap = new ArrayList<Integer>();

    public Numbers(int firstNumber) {
        median = firstNumber;
        even = !even;
    }

    public double getMedian() {
        if (!even) return median;
        return (Collections.min(minHeap)/2 + Collections.max(maxHeap)/2);
    }

    public void insertNumber(int number){
        if (even){
            if (number < Collections.min(minHeap) && number > Collections.max(maxHeap)){
                median = number;
                even = !even;
            } else if (number <= Collections.max(maxHeap)){
                median = Collections.max(maxHeap);
                maxHeap.remove(Collections.max(maxHeap));
                maxHeap.add(number);
            } else if (number >= Collections.min(minHeap)){
                median = Collections.min(minHeap);
                minHeap.remove(Collections.min(minHeap));
                minHeap.add(number);
            }

        } else {
            maxHeap.add(Math.min(number,median));
            minHeap.add(Math.max(number,median));
            even = !even;
        }
    }
}
