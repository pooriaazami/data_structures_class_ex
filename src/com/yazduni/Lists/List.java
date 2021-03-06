package com.yazduni.Lists;

public interface List<E> {

    void add(E data);

    void insert(E data, int index);

    E remove(int index);

    int delete(E data);

    int size();

    int capacity();

    E get(int index);

    void set(int index, E data);

    int find(E data);

}
