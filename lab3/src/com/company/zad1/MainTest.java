package com.company.zad1;

import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    public static String shuffle(String str) {
        char[] result = str.toCharArray();

        int n = result.length;
        while (n > 0) {
            int v = ThreadLocalRandom.current().nextInt(n);
            char temp = result[v];
            result[v] = result[n - 1];
            result[n - 1] = temp;
            n--;
        }
        return new String(result);
    }

    @RepeatedTest(500)
    void countSubstringAnagrams() {
        String charSetToPermutate = "zxcvbnm";
        String permutation1 = shuffle(charSetToPermutate);
        String permutation2 = shuffle(charSetToPermutate);
        StringBuilder sb = new StringBuilder();
        sb.append("qwertyuiop").append(permutation1).append("asdf").append(permutation2).append("ghjkl");
        assertTrue(Main.countSubstringAnagrams(sb.toString()) >= 9); // 9 to minimalna liczba anagramów jaka zawsze
        // będzie istnieć w podanym stringu.
    }
}