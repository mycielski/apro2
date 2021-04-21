package com.company.z2;

public class Coder {

    public static void main(String[] args){
        System.out.println(code("00PYe8m"));
    }
    public static String code(final String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            sb.append((char)(s.charAt(i) + '\u0001'));
        }
        return sb.toString();
    }
}