package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        CSVReader csvReader = new CSVReader("Sacramentorealestatetransactions.csv");
        System.out.println(csvReader);
        int columnIndex = 1;
        int counter = 0;
        ArrayList<String> list = (ArrayList<String>) csvReader.streamValues().collect(Collectors.toList());
        for (String string : list) {
            if (counter % csvReader.getColumns() == columnIndex) System.out.println(counter + ") " + string);
            counter++;
        }
    }

}
