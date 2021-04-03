package com.yazduni.Lists.tests;

import com.yazduni.Lists.List;

public class DynamicArray<E> implements List<E> {

    private final int INITIAL_SIZE = 8;
    private final double GROW_RATE = 1.5;
    private final double SHRINK_RATE = 1 / GROW_RATE;

    private int capacity = INITIAL_SIZE;
    private int last_index = 0;

    private E[] buffer;

    private void checkIndex(int index) {
        if (index < 0 || index > last_index)
            throw new IllegalArgumentException("Invalid value for index");
    }

    public DynamicArray() {
        buffer = (E[]) new Object[INITIAL_SIZE];
    }

    private void reInitializeBuffer() {
        E[] newBuffer = (E[]) new Object[(int) (capacity)];
        System.arraycopy(buffer, 0, newBuffer, 0, capacity);
    }

    @Override
    public void add(E data) {
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
