package com.company.zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj słowo:");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        System.out.println("Odnaleziono " + countSubstringAnagrams(word) + " par substringów będących anagramami.");
    }

    public static int countSubstringAnagrams(String word){
        if (word.length() < 2) return -1;
        int count = 0;
        for (int i = 1; i < word.length(); i++) {
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> listOfSortedString = new ArrayList<String>();
            for(int j = 0; j < word.length()-i+1; j++){
                // getting a substring and adding it to the list of substrings
                String substring = word.substring(j,j+i);
                list.add(substring);
                // sorting the substring alphabetically and adding it to the list of sorted substrings
                char[] substringAsArray = substring.toCharArray();
                Arrays.sort(substringAsArray);
                String substringSorted = new String(substringAsArray);
                listOfSortedString.add(substringSorted);
                System.out.println(substring +" " + substringSorted);
            }
        }
        return count;
    }

}