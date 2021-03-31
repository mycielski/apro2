package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class zad2 {

    public static void main(String[] args) {
        System.out.println("\n~~REGULAR BINARY TREE~~");
        BinaryTree tree = new BinaryTree(5);
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(4);
        tree.addNode(1);
        tree.addNode(6);
        tree.addNode(7);
        tree.addNode(8);
        System.out.println("SORTED:");
        for (Object value : tree.getSortedValues()) System.out.println(value);
        System.out.println("NOT SORTED:");
        for (Object leaf : tree) System.out.println(leaf);

        System.out.println("\n~~DEGENERATE BINARY TREE~~");
        BinaryTree degenerateTree = new BinaryTree(8);
        degenerateTree.degenerateAddNode(3);
        degenerateTree.degenerateAddNode(2);
        degenerateTree.degenerateAddNode(4);
        degenerateTree.degenerateAddNode(5);
        degenerateTree.degenerateAddNode(6);
        degenerateTree.degenerateAddNode(7);
        degenerateTree.degenerateAddNode(1);
        System.out.println("SORTED:");
        for (Object value : degenerateTree.getSortedValues()) System.out.println(value);
        System.out.println("NOT SORTED:");
        for (Object leaf : degenerateTree) System.out.println(leaf);

    }
}

class BinaryTree implements Iterable {

    private Node root = null;
    private boolean degenerationBalancer = true;

    public BinaryTree(Object rootNodeValue) {
        this.root = new Node(rootNodeValue);
    }

    public LinkedList getSortedValues(){
        LinkedList list = new LinkedList();
        TreeIterator ti = new TreeIterator(root);
        while (ti.hasNext()) list.add(ti.next());
        return (LinkedList) list.stream().sorted(Comparator.comparing(Node::hashCode)).collect(Collectors.toCollection(LinkedList::new));
    }

    public void addNode(Object newNodeValue) {
        Node newNode = new Node(newNodeValue);
        TreeIterator ti = new TreeIterator(root);
        Node current;
        while (ti.hasNext()) {
            current = ti.next();
            if (current.hasLeftChild() && !current.hasRightChild()) {
                current.setRightChild(newNode);
                return;
            } else if (!current.hasLeftChild()) {
                current.setLeftChild(newNode);
                return;
            }
        }
    }

    public void degenerateAddNode(Object newNodeValue){
        Node newNode = new Node(newNodeValue);
        Node current = root;
        if (degenerationBalancer){
            while (current.hasLeftChild()) {
                current = current.getLeftChild();
            }
            current.setLeftChild(newNode);
        } else {
            while (current.hasRightChild()) {
                current=current.getRightChild();
            }
            current.setRightChild(newNode);
        }
        degenerationBalancer = !degenerationBalancer;
    }

    @Override
    public Iterator<Node> iterator() {
        return new TreeIterator(root);
    }

    private class TreeIterator implements Iterator {

        private final Queue<Node> queue = new LinkedList();

        public TreeIterator(Node root) {
            queue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Node next() {
            Node current = queue.poll();
            if (current.hasLeftChild()) queue.add(current.getLeftChild());
            if (current.hasRightChild()) queue.add(current.getRightChild());
            return current;
        }
    }

    protected class Node {

        private final Object value;
        private Node leftChild = null, rightChild = null;
        private boolean hasLeftChild = false, hasRightChild = false;

        public Node(Object value) {
            this.value = value;
        }


        public Object getValue() {
            return value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
            hasLeftChild = true;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
            hasRightChild = true;
        }

        public boolean hasRightChild() {
            return hasRightChild;
        }

        public boolean hasLeftChild() {
            return hasLeftChild;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder().append("Node{").append("value=").append(value);
            if (hasLeftChild()) sb.append(", ").append("leftChildValue=").append(getLeftChild().getValue());
            if (hasRightChild()) sb.append(", ").append("rightChildValue=").append(getRightChild().getValue());
            return sb.append('}').toString();
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }
}
