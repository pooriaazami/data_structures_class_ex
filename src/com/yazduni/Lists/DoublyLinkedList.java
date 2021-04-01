package com.yazduni.Lists;

public class DoublyLinkedList<E> implements LinkedList<E> {

    private class Node {
        E data;
        Node _next;
        Node _prev;

        public Node(E data, Node next, Node prev) {
            this.data = data;
            this._next = next;
            this._prev = prev;
        }

        public Node(E data) {
            this(data, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getNext() {
            return _next;
        }

        public void setNext(Node _next) {
            this._next = _next;
        }

        public Node getPrev() {
            return _prev;
        }

        public void setPrev(Node _prev) {
            this._prev = _prev;
        }
    }

    private Node head;
    private Node tail;
    private int length;

    public DoublyLinkedList() {
        head = new Node();
        tail = new Node();
        this.length = 0;

        head.setNext(tail);
        tail.setPrev(head);
    }

    private Node getNode(int index) {
        if (index < 0 || index >= length)
            throw new IllegalArgumentException();

        Node temp = head;
        for (int i = 0; i <= index; i++)
            temp = temp.getNext();

        return temp;
    }

    private void insertBefore(Node node, E data) {
        Node newNode = new Node(data, node, node.getPrev());

        node.getPrev().setNext(newNode);
        node.setPrev(newNode);

        length++;
    }

    private void remove(Node node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());

        node.setNext(null);
        node.setPrev(null);

        length--;
    }

    public ItemIterator getIterator() {
        return new ItemIterator();
    }

    public ItemIterator getIterator(int index) {
        return new ItemIterator(index);
    }

    @Override
    public void append(E data) {
        insertBefore(tail, data);
    }

    @Override
    public void insert(int index, E data) {
        Node place = getNode(index);
        insertBefore(place, data);
    }

    @Override
    public E get(int index) {
        return getNode(index).getData();
    }

    @Override
    public E remove(int index) {
        Node node = getNode(index);
        remove(node);
        return node.getData();
    }

    @Override
    public E pop() {
        return remove(length - 1);
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public E getHead() {
        if (length == 0)
            throw new IllegalArgumentException();
        return head.getNext().getData();
    }

    @Override
    public E getTail() {
        if (length == 0)
            throw new IllegalArgumentException();
        return tail.getPrev().getData();
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    public class ItemIterator implements Iterator {

        private Node pointer;

        public ItemIterator() {

        }

        public ItemIterator(int index) {
            pointer = getNode(index);

        }

        public void moveTo(int index) {
            pointer = getNode(index);
        }

        public void moveToHead() {
            if (length == 0)
                throw new IllegalArgumentException();

            pointer = head.getNext();
        }

        public void moveToTail() {
            if (length == 0)
                throw new IllegalArgumentException();

            pointer = tail.getPrev();
        }

        @Override
        public Object getData() {
            return pointer.getData();
        }

        @Override
        public void next() {
            if (pointer.getNext() == tail)
                throw new IndexOutOfBoundsException();

            pointer = pointer.getNext();
        }

        @Override
        public void prev() {
            if (pointer.getPrev() == head)
                throw new IndexOutOfBoundsException();

            pointer = pointer.getPrev();
        }

        @Override
        public boolean hasNext() {
            return pointer.getNext() != tail;
        }

        @Override
        public boolean hasPrev() {
            return pointer.getPrev() != head;
        }

        public void addNext(E data) {
            if (pointer == null)
                throw new NullPointerException();

            insertBefore(pointer.getNext(), data);
        }

        public void addPrev(E data) {
            if (pointer == null)
                throw new NullPointerException();

            insertBefore(pointer, data);
        }

        public void removeAndMoveForward() {
            if (pointer == null)
                throw new NullPointerException();

            if (pointer == tail.getPrev())
                throw new IndexOutOfBoundsException();

            pointer = pointer.getNext();
            remove(pointer.getPrev());
        }

        public void removeAndMoveBackward() {
            if (pointer == null)
                throw new NullPointerException();

            if (pointer == head.getNext())
                throw new IndexOutOfBoundsException();

            pointer = pointer.getPrev();
            remove(pointer.getNext());
        }

    }
}
