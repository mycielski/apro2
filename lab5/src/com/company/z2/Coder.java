package com.company.z2;

public class Coder {

    /* Flags:
     ACC_STATIC

   static String code(java.lang.String)  */

    public static void main(String[] args) {
        System.out.println(code("00PYe8m")); // 4949819010257110
    }

    public static String code(String param1) {
        char c;
        int i;
        int i1;
        int i2;
        String s;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder1;

        stringBuilder = new StringBuilder();
        i1 = 0;
        while (true) {
            i2 = i1;
            i = param1.length();
            if (i <= i2) break;
            stringBuilder1 = stringBuilder;
            c = param1.charAt(i1);
            stringBuilder1.append(c + '\1');
            i1 = i1 + 1;
        }
        s = stringBuilder.toString();
        return s;
    }
}
