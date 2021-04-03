package com.yazduni.Lists;

public class DynamicStack<E> implements Stack<E> {
    private DynamicArray<E> array;

    public DynamicStack() {
        array = new DynamicArray<>();
    }

    @Override
    public void push(E data) {
        array.add(data);
    }

    @Override
    public E pop() {
        if (array.size() == 0)
            throw new IllegalArgumentException();

        return array.remove(array.size() - 1);
    }

    @Override
    public E top() {
        if (array.size() == 0)
            throw new IllegalArgumentException();

        return array.get(array.size() - 1);
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public int capacity() {
        return array.capacity();
    }
}
