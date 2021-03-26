package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class zad2 {

    public static void main(String[] args){
        BinaryTree tree = new BinaryTree("First");
        tree.addNode("Second");
    }
}

class BinaryTree implements Iterable{

    private Node root;

    public BinaryTree(Object first) {
        this.root = new Node(first);
    }

    public void addNode(Object o){
        Node newNode = new Node(o);

    }

    @Override
    public Iterator iterator() {
        return null;
    }

    class TreeIterator implements Iterator{

        @Override
        public boolean hasNext() {
            try {
                return next() != null;
            } catch (NoSuchElementException e){
                return false;
            }
        }

        @Override
        public Node next() {
            return null;
        }
    }

    class Node {
        private Node leftChild = null, rightChild = null;
        private Object value;

        public Node(Object value) {
            this.value = value;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }
}
