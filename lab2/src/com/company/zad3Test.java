package com.company;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class zad3Test {

    private static BinaryTree tree = new BinaryTree(1);

    @BeforeEach
    void beforeEachTest(){
        tree.addNode(Math.random());
    }

    @RepeatedTest(50)
    void isComplete() {
        assertTrue(zad3.isComplete(zad3Test.tree));
    }
}