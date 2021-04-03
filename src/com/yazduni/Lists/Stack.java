package com.yazduni.Lists;

public interface Stack<E> {

    void push(E data);

    E pop();

    E top();

    int size();

    int capacity();

}
