package com.company;

import com.company.BinaryTree.Node;


public class zad3 {

    public static void main(String[] args) {
        BinaryTree BSTInvalidTree = new BinaryTree(5);
        BSTInvalidTree.addNode(2);
        BSTInvalidTree.addNode(3);
        BSTInvalidTree.addNode(4);
        BSTInvalidTree.addNode(1);
        BSTInvalidTree.addNode(6);
        BSTInvalidTree.addNode(7);
        BSTInvalidTree.addNode(8);

        System.out.println(isBSTValid(BSTInvalidTree));

        BinaryTree BSTValidTree = new BinaryTree(4);
        BSTValidTree.addNode(2);
        BSTValidTree.addNode(6);
        BSTValidTree.addNode(1);
        BSTValidTree.addNode(3);
        BSTValidTree.addNode(5);
        BSTValidTree.addNode(7);

        System.out.println(isBSTValid(BSTValidTree));
    }

    public static boolean isBSTValid(BinaryTree tree) {
        Node current;
        for (Object leaf : tree) {
            current = (Node) leaf;
            if (current.hasLeftChild() && (Integer) current.getLeftChild().getValue() > (Integer) current.getValue())
                return false;
            if (current.hasRightChild() && (Integer) current.getRightChild().getValue() < (Integer) current.getValue())
                return false;
        }
        return true;
    }
}
