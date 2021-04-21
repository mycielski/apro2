package com.company.z2;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(final String[] array) {
        if (array.length != 1) {
            System.out.println("Wrong password!");
            return;
        }
        final String[] split = array[0].split("_");
        final int int1 = Integer.parseInt(new SimpleDateFormat("MM").format(Date.from(Instant.now())));
        if (split[0].length() == 7 && split[1].length() == 2) {
            if (split[0].equals(Coder.code("00PYe8m")) && Integer.parseInt(split[1]) == int1) {
                System.out.println("Good guess");
            } else {
                System.out.println("Wrong password!");
            }
        } else {
            System.out.println("Wrong password!");
        }
    }
}
