package com.company;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class zad2 {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree("First");
        tree.addNode("Second");
        tree.addNode(3);
        tree.addNode('Q');

        for (Object leaf : tree) System.out.println(leaf);
    }
}

class BinaryTree implements Iterable {

    private Node root = null;
    private final Queue<Node> q = new LinkedList<>();

    public BinaryTree(Object rootNodeValue) {
        this.root = new Node(rootNodeValue);
    }

    public void addNode(Object newNodeValue) {
        Node newNode = new Node(newNodeValue);
        Iterator<Node> iterator = iterator();
        while (iterator.hasNext()) {
            Node current = iterator().next();
            if (!current.hasLeftChild()) {
                current.setLeftChild(newNode);
                break;
            } else if( !current.hasRightChild()){
                current.setRightChild(newNode);
                break;
            }
        }
        iterator.next().setLeftChild(newNode);
    }

    @Override
    public Iterator<Node> iterator() {
        q.add(root);
        Iterator<Node> it = new Iterator<Node>() {
            @Override
            public boolean hasNext() {
                return !q.isEmpty();
            }

            @Override
            public Node next() {
                if (q.peek() != null) {
                    if (q.peek().hasLeftChild) q.add(q.peek().getLeftChild());
                    if (q.peek().hasRightChild) q.add(q.peek().getRightChild());
                    return q.remove();
                }
                return null;
            }
        };
        return it;
    }

    class Node {

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
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}

/*
class BinaryTree<Node> implements Iterable<BinaryTree.Node> {

    private final Node root;
    private Node current;
    private Queue<Node> q;

    public BinaryTree(Object first) {
        root = new Node(first);
    }

    public void addNode(Object o) {
        Node newNode = new Node(o);
        Iterator<Node> iterator = iterator();
        while(iterator.hasNext()) iterator.next();
        iterator.next().setLeftChild(newNode);
    }

    @Override
    public Iterator<Node> iterator() {
        q.add(root);
        Iterator<Node> it = new Iterator<Node>() {

            @Override
            public boolean hasNext() {
                return q.peek() != null;
            }

            @Override
            public Node next() {
                if(q.peek().getLeftChild()!=null) q.add(q.peek().getLeftChild());
                if(q.peek().getRightChild()!=null) q.add(q.peek().getRightChild());
                return q.remove();
            }
        };
        return it;
    }

    /*
    class TreeIterator implements Iterator<Node> {

        private Node current;
        private Queue<Node> q;

        public TreeIterator(Node root) {
            current = root;
            q.add(root);
            if(root.getLeftChild()!= null) q.add(root.getLeftChild());
            if(root.getRightChild() != null) q.add(root.getRightChild());
        }

        @Override
        public boolean hasNext() {
            return q.peek() != null;
        }

        @Override
        public Node next() {
            current = q.remove();
            if(current.getLeftChild()!= null) q.add(current.getLeftChild());
            if(current.getRightChild()!=null)q.add(current.getRightChild());
            return current;
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

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
*/