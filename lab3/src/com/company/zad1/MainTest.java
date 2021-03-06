package com.company.zad1;

import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    /**
     * Method to permutate the given string
     * @param str string to permutate
     * @return string shuffled
     */
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
        StringBuilder sb = new StringBuilder();
        sb.append("qwertyuiop").append(shuffle(charSetToPermutate)).append("asdf").append(shuffle(charSetToPermutate)).append("ghjkl");
        assertTrue(Main.countSubstringAnagrams(sb.toString()) >= 9); // 9 to minimalna liczba anagramów jaka zawsze
        // będzie istnieć w podanym stringu.
    }
}