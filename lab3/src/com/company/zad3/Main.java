package com.company.zad3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class Main {

    public static void main(String[] args) {
        float loadFactor;
        loadFactor = 0.25f;
        hashMapTest(loadFactor);

        loadFactor = 0.75f;
        hashMapTest(loadFactor);

        loadFactor = 0.95f;
        hashMapTest(loadFactor);
    }

    public static void hashMapTest(float loadFactor) {
        System.out.println("\nLoad factor = " + loadFactor);

        long timer, start, finish;

        // measuring the performance of KeyTst hashmap
        HashMap<KeyTst, String> mapTst = new HashMap<KeyTst, String>(1024, loadFactor);
        ArrayList<KeyTst> keyTstSearchList = new ArrayList<>();
        timer = 0;
        for (int i = 0; i < 1048576; i++) {
            KeyTst put = new KeyTst(generateRandomString(), generateRandomString(), generateRandomInt(10, 100),
                    generateRandomInt(140, 205), generateRandomFloatForShoeSize(), generateRandomDoubleForWeight());
            start = System.nanoTime();
            mapTst.put(put, generateRandomString());
            finish = System.nanoTime();
            timer += finish - start;
            if (i % 10 == 0) keyTstSearchList.add(put); // saving 104858 keys for later use when testing the
            // search function of my hashmap
        }
        System.out.println("Umieszczenie 1048576 rekordów typu KeyTst -> String w hashmapie zajęło " + timer +
                " nanosekund.");
        start = System.nanoTime();
        for (KeyTst key : keyTstSearchList) mapTst.get(key);
        finish = System.nanoTime();
        timer = finish - start;
        System.out.println("Wyszukanie 104858 rekordów z hashmapy zajęło " + timer + " nanosekund.\n");

        // measuring the performance of KeyStd hashmap
        HashMap<KeyStd, String> mapStd = new HashMap<KeyStd, String>(1024, loadFactor);
        ArrayList<KeyStd> keyStdSearchList = new ArrayList<>();
        timer = 0;
        for (int i = 0; i < 1048576; i++) {
            KeyStd put = new KeyStd(generateRandomString(), generateRandomString(), generateRandomInt(10, 100),
                    generateRandomInt(140, 205), generateRandomFloatForShoeSize(), generateRandomDoubleForWeight());
            start = System.nanoTime();
            mapStd.put(put, generateRandomString());
            finish = System.nanoTime();
            timer += finish - start;
            if (i % 10 == 0) keyStdSearchList.add(put);// saving 104858 keys for later use when testing the
            // search function of my hashmap
        }
        System.out.println("Umieszczenie 1048576 rekordów typu KeyStd -> String w hashmapie zajęło " + timer +
                " nanosekund.");
        start = System.nanoTime();
        for (KeyStd key : keyStdSearchList) mapStd.get(key);
        finish = System.nanoTime();
        timer = finish - start;
        System.out.println("Wyszukanie 104858 rekordów z hashmapy zajęło " + timer + " nanosekund" +
                ".\n--------------------------------------------------------------\n");
    }


    public static double generateRandomDoubleForWeight() {
        int min = 40;
        int max = 140;
        return (Math.random() * (max - min)) + min;
    }

    public static float generateRandomFloatForShoeSize() {
        int min = 32;
        int max = 56;
        return (float) ((Math.random() * (max - min)) + min);
    }

    public static int generateRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String generateRandomString() {
        int leftLimit = 97; //the letter 'a'
        int rightLimit = 122; // the letter 'z'
        int targetStringLength = 10;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}

class Key {
    protected String firstName, lastName;
    protected int age, height;
    protected float shoeNumber;
    protected double weight;

    public Key(String firstName, String lastName, int age, int height, float shoeNumber, double weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.shoeNumber = shoeNumber;
        this.weight = weight;
    }

}


class KeyStd extends Key {
    public KeyStd(String firstName, String lastName, int age, int height, float shoeNumber, double weight) {
        super(firstName, lastName, age, height, shoeNumber, weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, height, shoeNumber, weight);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

class KeyTst extends Key {
    public KeyTst(String firstName, String lastName, int age, int height, float shoeNumber, double weight) {
        super(firstName, lastName, age, height, shoeNumber, weight);
    }

    @Override
    public int hashCode() {
        double i = shoeNumber * age * weight / (height * height);
        String name = firstName + i + lastName;
        byte[] array = name.getBytes();
        Checksum checksum = new Adler32();
        checksum.update(array, 0, array.length);
        return (int) checksum.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
// ...
}