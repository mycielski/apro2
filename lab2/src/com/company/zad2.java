package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class zad2 {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree("First");
        tree.addNode("Second");
    }
}

class BinaryTree implements Iterable {

    private final Node root;

    public BinaryTree(Object first) {
        this.root = new Node(first);
    }

    public void addNode(Object o) {
        Node newNode = new Node(o);
        TreeIterator treeIterator = new TreeIterator(root);

    }

    @Override
    public Iterator iterator() {
        //TreeIterator treeIterator = new TreeIterator();
        return null;
    }

    class TreeIterator implements Iterator<Node> {

        private Node current, parent;

        public TreeIterator(Node root) {
            current = root;
            parent = root;
        }

        @Override
        public boolean hasNext() {
            try {
                return next() != null;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        @Override
        public Node next() {

            return current;
        }

        public Node last(){
            while (current.getRightChild() != null){
                current = current.getRightChild();
                if (current.getLeftChild() == null) return current;
            }
            return current.getLeftChild();
        }
    }

    class Node {
        private Node leftChild = null, rightChild = null;
        private final Object value;

        public Node(Object value) {
            this.value = value;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public Object getValue() {
            return value;
        }
    }
}
