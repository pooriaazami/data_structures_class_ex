package com.yazduni.Lists;

public interface LinkedList<E> {

    void append(E data);

    void insert(int index, E data);

    E get(int index);

    E remove(int index);

    E pop();

    int size();

    E getHead();

    E getTail();

    boolean isEmpty();
}
