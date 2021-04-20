package com.company.z2;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Main {

    /* Flags:
     ACC_PUBLIC
     ACC_STATIC

   public static void main(java.lang.String[])  */

    public static void main(String[] param1) {
        PrintStream printStream;
        String[] strings;
        Instant instant;
        Date date;
        String s;
        int i;
        int i1;
        boolean b;
        SimpleDateFormat simpleDateFormat;
        String s1;

        if (param1.length != 1) {
            printStream = System.out;
            printStream.println("Wrong password!");
            return;
        }
        strings = param1[0].split("_");
        instant = Instant.now();
        date = Date.from(instant);
        simpleDateFormat = new SimpleDateFormat("MM");
        s = simpleDateFormat.format(date);
        i = Integer.parseInt(s);
        i1 = strings[0].length();
        if ((i1 == 7) && (i1 = strings[1].length(),i1 == 2)){
            s1 = strings[0];
            s = Coder.code("00PYe8m");
            b = s1.equals(s);
            if ((b) && (i1 = Integer.parseInt(strings[1]),i1 == i)){
                printStream = System.out;
                printStream.println("Good guess");
                return;
            }
            printStream = System.out;
            printStream.println("Wrong password!");
            return;
        }
        printStream = System.out;
        printStream.println("Wrong password!");
        return;
    }
}
