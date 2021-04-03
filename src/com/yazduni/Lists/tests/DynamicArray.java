package com.yazduni.Lists.tests;

import com.yazduni.Lists.List;

public class DynamicArray<E> implements List<E> {

    private final int INITIAL_SIZE = 8;
    private final double GROW_RATE = 1.5;
    private final double SHRINK_RATE = 1 / GROW_RATE;

    private E[] buffer;

    public DynamicArray() {
        buffer = (E[]) new Object[INITIAL_SIZE];
    }

    private void shrink() {
    }

    private void grow() {
    }

    @Override
    public void add() {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E delete(E data) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(E data) {

    }
}
