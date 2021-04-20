package com.company.z1;

public class ConcatenatorStringBuilder {
    public static void main(String[] args) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("String1");
        stringBuilder.append("String2");
        String string;
        string = stringBuilder.toString();
        System.out.println(string);
    }
}
