package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            CSVReader csvReader = new CSVReader("Sacramentorealestate.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
