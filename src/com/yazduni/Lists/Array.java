package com.yazduni.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Array<E> {

    private int ndim;
    private int length;
    private int[] shape;
    private int[] indexMap;

    private E[] buffer;

    private int calculateLength(int[] arrayShape) {
        int ans = 1;
        for (int i = 0; i < arrayShape.length; i++)
            ans *= arrayShape[i];

        return ans;
    }

    private void fillIndexMax() {
        this.indexMap = new int[this.ndim];

        this.indexMap[0] = 0;

        if (this.ndim > 1)
            this.indexMap[1] = this.shape[0];

        for (int i = 2; i < this.ndim; i++)
            this.indexMap[i] = this.indexMap[i - 1] * this.shape[i - 1];

    }

    public Array(E[] data) {
        this.buffer = data;
        this.length = buffer.length;
        this.ndim = 1;

        this.shape = new int[]{this.length};
        fillIndexMax();
    }

    public Array(int... shape) {
        this.shape = shape;
        this.length = calculateLength(this.shape);
        this.ndim = shape.length;

        this.buffer = (E[]) new Object[this.length];
        fillIndexMax();
    }

    public Array(ArrayList<E> data, ArrayList<Integer> shapes) {
        this.shape = IntStream.range(0, shapes.size()).map(i -> shapes.get(i)).toArray();
        this.length = calculateLength(this.shape);
        this.ndim = shape.length;

        this.buffer = (E[]) data.toArray();
        fillIndexMax();
    }

    public Array(ArrayList<E> data) {
        this.shape = new int[]{data.size()};
        this.length = calculateLength(this.shape);
        this.ndim = shape.length;

        this.buffer = (E[]) data.toArray();
        fillIndexMax();
    }

    public Array(E[] data, int[] shape) {
        this.buffer = data;
        this.shape = shape;
        this.ndim = shape.length;
        this.length = calculateLength(this.shape);

        fillIndexMax();
    }

    public boolean reshape(int[] shape) {
        if (calculateLength(shape) == this.length) {
            this.shape = shape;
            this.ndim = shape.length;
            fillIndexMax();
        }

        return false;
    }

    public boolean reshape(ArrayList<Integer> inputShape) {
        int[] arrayShape = IntStream.range(0, inputShape.size()).map(i -> inputShape.get(i)).toArray();

        if (calculateLength(arrayShape) == this.length) {
            this.shape = arrayShape;
            this.ndim = arrayShape.length;
            fillIndexMax();

            return true;
        }

        return false;
    }

    public int getNdim() {
        return ndim;
    }

    public int getLength() {
        return length;
    }

    private int getIndex(int[] indices) {
        int ans = indices[0];
        for (int i = 0; i < this.ndim; i++)
            ans += this.indexMap[i] * indices[i];

        if (ans < 0 || ans >= this.length)
            throw new ArrayIndexOutOfBoundsException();

        return ans;
    }

    private int getIndex(ArrayList<Integer> indices) {
        int ans = indices.get(0);
        for (int i = 0; i < this.ndim; i++)
            ans += this.indexMap[i] * indices.get(i);

        if (ans < 0 || ans >= this.length)
            throw new ArrayIndexOutOfBoundsException();

        return ans;
    }

    public E get(int... indices) {
        return this.buffer[getIndex(indices)];
    }

    public E get(ArrayList<Integer> indices) {
        return this.buffer[getIndex(indices)];
    }

    public void set(E data, int... indices) {
        this.buffer[getIndex(indices)] = data;
    }
}
