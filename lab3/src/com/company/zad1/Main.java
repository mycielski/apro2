package com.company.zad1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj słowo:");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        System.out.println("Odnaleziono " + countSubstringAnagrams(word) + " par substringów będących anagramami.");
    }

    public static int countSubstringAnagrams(String word){
        int count = 0;
        for (int i = 0; i < word.length()-1; i++) {
            ArrayList<String> list = new ArrayList<String>();

        }
        return count;
    }

}
