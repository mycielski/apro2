package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zad1 {

    public static void main(String[] args) throws IOException {
        Stack<String> stack = new Stack<String>();
        Properties properties = new Properties();
        properties.load(new FileReader("test1.txt"));
        File test1 = new File("test1fail.xml");
        Scanner scanner = new Scanner(test1);
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\<((?!\\!).*?)\\>");
        Matcher matcher;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            matcher = pattern.matcher(line);
            while (matcher.find()) {
                String string = matcher.group(1).replaceAll("\\/", "");
                if (!(string.contains(properties.getProperty("e1")) || string.contains(properties.getProperty("e2")) || string.contains(properties.getProperty("e3")))) continue;
                if (stack.size()==0 || !matcher.group(1).equals("/" + stack.peek())) {
                    stack.push(matcher.group(1));
                } else if (matcher.group(1).equals("/" + stack.peek())) {
                    stack.pop();
                }
            }
        }
        if(stack.size()!=0){
            for (int i = 0; i < stack.size(); i++){
                for (int j = i+1; j < stack.size(); j++){
                    if (("/" + stack.elementAt(i)).equals(stack.elementAt(j))){
                        stack.remove(i);
                        stack.remove(j-1);
                        i=-1;
                        break;
                    }
                }
            }
            System.out.println("Wystąpiło " + stack.size() + " błędów. Oto błędne znaczniki:");
            System.out.println(stack);
        } else System.out.println("Nie znaleziono błędów.");
    }
}
