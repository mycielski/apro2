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

    @Test
    void getSortedValuesListFromDegenerateTree() {
        BinaryTree degenerateTree = new BinaryTree(8);
        degenerateTree.degenerateAddNode(3);
        degenerateTree.degenerateAddNode(2);
        degenerateTree.degenerateAddNode(4);
        degenerateTree.degenerateAddNode(5);
        degenerateTree.degenerateAddNode(6);
        degenerateTree.degenerateAddNode(7);
        degenerateTree.degenerateAddNode(1);
        for (int i = 1; i < degenerateTree.getSortedValuesList().size(); i++) {
            assertTrue(degenerateTree.getNodeValue(degenerateTree.getSortedValuesList().get(i)) > degenerateTree.getNodeValue(degenerateTree.getSortedValuesList().get(i - 1)));
        }
    }

}