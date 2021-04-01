package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class zad3Test {

    @Test
    void isBSTValid() {
        BinaryTree BSTInvalidTree = new BinaryTree(5);
        BSTInvalidTree.addNode(2);
        BSTInvalidTree.addNode(3);
        BSTInvalidTree.addNode(4);
        BSTInvalidTree.addNode(1);
        BSTInvalidTree.addNode(6);
        BSTInvalidTree.addNode(7);
        BSTInvalidTree.addNode(8);

        Assertions.assertFalse(zad3.isBSTValid(BSTInvalidTree));

        BinaryTree BSTValidTree = new BinaryTree(4);
        BSTValidTree.addNode(2);
        BSTValidTree.addNode(6);
        BSTValidTree.addNode(1);
        BSTValidTree.addNode(3);
        BSTValidTree.addNode(5);
        BSTValidTree.addNode(7);

        Assertions.assertTrue(zad3.isBSTValid(BSTValidTree));

    }
}