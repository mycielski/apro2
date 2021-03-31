package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryTreeTest {

    @Test
    void getSortedValuesList() {
        BinaryTree tree = new BinaryTree(5);
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(4);
        tree.addNode(1);
        tree.addNode(6);
        tree.addNode(7);
        tree.addNode(8);
        for (int i = 1; i < tree.getSortedValuesList().size(); i++) {
            assertTrue(tree.getNodeValue(tree.getSortedValuesList().get(i)) > tree.getNodeValue(tree.getSortedValuesList().get(i - 1)));
        }
    }
}