package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        CSVReader csvReader = new CSVReader("Sacramentorealestatetransactions.csv");
        System.out.println(csvReader);
        int numberOfDataCells = csvReader.getColumns() * csvReader.getLines();
        int columnIndex = 1;
        int counter = 0;
        ArrayList<String> list = (ArrayList<String>) csvReader.streamValues().collect(Collectors.toList());
        for (int i = 0; i < csvReader.getColumns(); i++) {
            list.remove(0);
        }
        Set<String> set = new LinkedHashSet<>();
        for (String string : list) {
            while (counter < 12) {
                counter++;
                continue;
            }
            if (counter % csvReader.getColumns() == columnIndex) {
                set.add(string);
            }
            counter++;
        }
        System.out.println("Plik przedstawia spis transakcji sprzedaży nieruchomości w " + set.size() + " " +
                "miastach:");
        HashMap<String, int[]> pricesHashMap = new HashMap<>();
        for (String city :
                set) {
            System.out.println(city);
            pricesHashMap.put(city, new int[]{0,0});
        }
        for (int i = 1; i < list.size()-8; i+=12) {
            int price = pricesHashMap.get(list.get(i))[0];
            int denominator = pricesHashMap.get(list.get(i))[1];
            price += Double.parseDouble(list.get(i+8));
            denominator++;
            pricesHashMap.put(list.get(i),new int[]{price,denominator});
        }
        System.out.println("Srednie ceny nieruchomości w poszczególnych miastach to:");
        String city = "";
        int price = 0;
        for (String key :
                pricesHashMap.keySet()) {
            int avgPrice = pricesHashMap.get(key)[0]/pricesHashMap.get(key)[1];
            System.out.println(key + ") " + (avgPrice));
            if (price < avgPrice) {
                price = avgPrice;
                city = key;
            }
        }

        System.out.println("Możemy z tego wywnioskować, że najbardziej ekskluzywnym miastem jest " + city + " gdzie " +
                "średnia cena nieruchomości to aż " + price + " dolary!");
    }

}
