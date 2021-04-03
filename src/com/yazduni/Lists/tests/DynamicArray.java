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
        if (last_index + 1 == capacity) {
            capacity *= GROW_RATE;
            reInitializeBuffer();
        }

        buffer[last_index++] = data;
    }

    @Override
    public void insert(E data, int index) {

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
        return last_index + 1;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return buffer[index];
    }

    @Override
    public void set(int index, E data) {
        checkIndex(index);

        buffer[index] = data;

    }
}
