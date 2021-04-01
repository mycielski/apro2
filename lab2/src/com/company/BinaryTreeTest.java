package com.company;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryTreeTest {

    @Test
    void nodesPrintingOrderTest() throws IOException {
        BinaryTree tree = new BinaryTree(1);
        for(int i = 2; i < 50 ; i++){
            tree.addNode(i);
        }
        List list = new ArrayList();
        String string = tree.toString();
        Reader inputString = new StringReader(string);
        BufferedReader reader = new BufferedReader(inputString);
        Pattern pattern = Pattern.compile("(value=([0-9]*))");
        String line = reader.readLine();
        while (line != null){
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                list.add(Integer.parseInt(matcher.group(2)));
            }
            line = reader.readLine();
        }
        System.out.println(list);
        assertTrue(list.stream().sorted().collect(Collectors.toList()).equals(list));
    }

}