package com.yazduni.Lists;

public interface List<E> {

    void add();

    E remove(int index);

    E delete(E data);

    int size();

    int capacity();

    E get(int index);

    void set(E data);

}