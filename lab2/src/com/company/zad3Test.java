package com.company;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class zad3Test {

    private static BinaryTree completeTree = new BinaryTree(1);
    private static BinaryTree incompleteTree = new BinaryTree(1);

    @BeforeAll
    static void beforeAllTest(){
        for (int i = 2; i < 10; i++) {
            completeTree.addNode(i);
            incompleteTree.degenerateAddNode(i);
        }
    }
    @BeforeEach
    void beforeEachTest(){
        completeTree.addNode(Math.random());
        incompleteTree.addNode(Math.random());

    }

    @RepeatedTest(20)
    void isComplete() {
        assertTrue(zad3.isComplete(zad3Test.completeTree));
        assertFalse(zad3.isComplete(zad3Test.incompleteTree));
    }
}