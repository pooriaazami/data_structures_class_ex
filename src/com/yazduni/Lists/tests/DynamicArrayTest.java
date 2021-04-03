package com.yazduni.Lists.tests;

import com.yazduni.Lists.DynamicArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    private DynamicArray<String> array;

    @BeforeAll
    void initialize() {
        array = new DynamicArray<>();
    }

    @Test
    void add() {
        assertEquals(8, array.capacity());
        assertEquals(0, array.size());
    }

    @Test
    void insert() {
    }

    @Test
    void remove() {
    }

    @Test
    void delete() {
    }
}