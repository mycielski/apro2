package com.company;

import com.company.BinaryTree.Node;

import java.util.LinkedList;

public class zad3 {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(1);
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(4);
        tree.addNode(5);
        tree.addNode(6);
        tree.addNode(7);
        tree.addNode(8);
        tree.degenerateAddNode(9);
        tree.degenerateAddNode(10);
        tree.degenerateAddNode(11);
        System.out.println(isComplete(tree));
    }

    public static boolean isComplete(BinaryTree tree) {
        Node current;
        LinkedList<Node> list = new LinkedList<>();
        boolean potentiallyIncomplete = false;
        for (Object leaf : tree) {
            current = (Node) leaf;
            if (current.hasRightChild() && !current.hasLeftChild()) return false;
            if (!potentiallyIncomplete && current.hasLeftChild()) {
                list.add(current.getLeftChild());
                if (current.hasRightChild()) {
                    list.add(current.getRightChild());
                } else {
                    potentiallyIncomplete = true;
                }
            } else {
                if (current.hasRightChild() || current.hasLeftChild()) return false;
            }
        }
        return true;
    }
}
