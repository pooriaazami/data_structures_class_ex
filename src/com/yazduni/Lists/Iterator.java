package com.yazduni.Lists;

public interface Iterator<E> {

    E getData();

    void next();

    void prev();

    boolean hasNext();

    boolean hasPrev();
}
