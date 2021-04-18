package com.company;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        CSVReader csvReader = new CSVReader("Sacramentorealestatetransactions.csv");
        System.out.println(csvReader);
        int columnIndex = 1;
        int counter = 0;
        ArrayList<String> list = (ArrayList<String>) csvReader.streamValues().collect(Collectors.toList()); // zapisywanie wartości wszystkich pól danych do ArrayListy
        list.subList(0, csvReader.getColumns()).clear(); // usuwanie danych z pierwszego wiersza pliku
        Set<String> set = new LinkedHashSet<>();
        for (String string : list) {
            if (counter % csvReader.getColumns() == columnIndex) {
                set.add(string);
            }
            counter++;
        }
        System.out.println("Plik przedstawia spis transakcji sprzedaży nieruchomości w " + set.size() + " " +
                "miastach.");
        HashMap<String, int[]> pricesHashMap = new HashMap<>();
        for (String city :
                set) {
            pricesHashMap.put(city, new int[]{0, 0, 0});
        }
        for (int i = 1; i < list.size() - 8; i += 12) {
            int price = pricesHashMap.get(list.get(i))[0];
            int denominator = pricesHashMap.get(list.get(i))[1];
            int squareFootage = pricesHashMap.get(list.get(i))[2];
            squareFootage += Integer.parseInt(list.get(i + 5));
            price += Double.parseDouble(list.get(i + 8));
            denominator++;
            pricesHashMap.put(list.get(i), new int[]{price, denominator, squareFootage});
        }
        System.out.println("Srednie ceny nieruchomości w poszczególnych miastach to:");
        String mostExpensivePropertyCity = "";
        String mostExpensiveSqFootCity = "";
        int price = 0;
        int averagePricePerSqFoot = 0;
        LinkedList<Integer> pricesPerSqFoot = new LinkedList<>();
        for (String key :
                pricesHashMap.keySet()) {
            int avgPrice = pricesHashMap.get(key)[0] / pricesHashMap.get(key)[1];
            try {
                if (pricesHashMap.get(key)[0] / pricesHashMap.get(key)[2] <= 0) throw new ArithmeticException();
                System.out.println(key + ") " + (avgPrice) + " USD (" + pricesHashMap.get(key)[0] / pricesHashMap.get(key)[2] + " dolarów na stopę kwadratową)");
                pricesPerSqFoot.add(pricesHashMap.get(key)[0] / pricesHashMap.get(key)[2]);
            } catch (ArithmeticException e) {
                System.out.println(key + ") " + (avgPrice) + " USD (brak danych o cenie / stopa kwadratowa)");
                continue;
            }
            if (pricesHashMap.get(key)[0] / pricesHashMap.get(key)[2] > averagePricePerSqFoot) {
                averagePricePerSqFoot = pricesHashMap.get(key)[0] / pricesHashMap.get(key)[2];
                mostExpensiveSqFootCity = key;
            }
            if (price < avgPrice) {
                price = avgPrice;
                mostExpensivePropertyCity = key;
            }
        }
        int stDevOfSqFootPrices = stDev(pricesPerSqFoot, mean(pricesPerSqFoot));
        System.out.println("Możemy z tego wywnioskować, że najbardziej ekskluzywnym miastem jest " + mostExpensivePropertyCity + " gdzie " +
                "średnia cena nieruchomości to aż " + price + " dolary, lub " + mostExpensiveSqFootCity + ", gdzie " +
                "średnia cena za stopę kwadratową to aż " + averagePricePerSqFoot + " dolary!");
        System.out.println("Odchylenie standardowe cen na stopę kwadratową to " + stDevOfSqFootPrices + " dolary.");
        System.out.println("Srednia cena stopy kwadratowej nieruchomości to " + mean(pricesPerSqFoot) + " dolary.");
        if (stDevOfSqFootPrices > mean(pricesPerSqFoot)) System.out.println("Tak wysokie odchylenie standardowe " +
                "wskazuje na duże zróżnicowanie cen nieruchomości w zależności od miasta.");
        else System.out.println("Tak niskie odchylenie standardowe wskazuje na małe zróżnicowanie cen nieruchomości" +
                " w zależności od miasta.");
    }

    public static int mean(LinkedList<Integer> listOfIntegers) {
        double mean = 0;
        for (Integer integer :
                listOfIntegers) {
            mean += ((double) integer) / listOfIntegers.size();
        }
        return (int) mean;
    }

    public static int stDev(LinkedList<Integer> listOfIntegers, int mean) {
        double summation = listOfIntegers.stream().mapToDouble(integer -> Math.pow((double) integer - (double) mean, 2)).sum();
        summation = summation / listOfIntegers.size();
        return (int) Math.sqrt(summation);
    }
}
