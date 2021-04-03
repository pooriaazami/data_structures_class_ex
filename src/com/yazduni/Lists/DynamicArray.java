package com.yazduni.Lists;

import com.yazduni.Lists.List;

public class DynamicArray<E> implements List<E> {

    private final int INITIAL_SIZE = 8;
    private final double GROW_RATE = 2;
    private final double SHRINK_RATE = 1 / GROW_RATE;

    private int capacity = INITIAL_SIZE;
    private int last_index = -1;

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
        System.arraycopy(buffer, 0, newBuffer, 0, size());
        buffer = newBuffer;
    }

    @Override
    public void add(E data) {
        if (last_index + 1 == capacity) {
            capacity *= GROW_RATE;
            reInitializeBuffer();
        }

        buffer[++last_index] = data;
    }

    @Override
    public void insert(E data, int index) {
        if (last_index + 1 == capacity) {
            capacity *= GROW_RATE;
            reInitializeBuffer();
        }

        last_index++;
        checkIndex(index);

        System.arraycopy(buffer, index, buffer, index + 1, last_index - index);
        buffer[index] = data;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E element = buffer[index];
        System.arraycopy(buffer, index + 1, buffer, index, last_index - index + 1);

        if (last_index + 1 == capacity * SHRINK_RATE * SHRINK_RATE) {
            capacity *= SHRINK_RATE;
            reInitializeBuffer();
        }

        last_index--;
        return element;
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

    @Override
    public int find(E data) {
        return 0;
    }
}
