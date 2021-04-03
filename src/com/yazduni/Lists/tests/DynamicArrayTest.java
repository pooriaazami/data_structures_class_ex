package com.yazduni.Lists.tests;

import com.yazduni.Lists.DynamicArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    private DynamicArray<String> array;

    @BeforeEach
    void initialize() {
        array = new DynamicArray<>();
    }

    @Test
    void add() {
        assertEquals(8, array.capacity());
        assertEquals(0, array.size());

        array.add("JAVA");
        assertEquals(8, array.capacity());
        assertEquals(1, array.size());

        array.add("C++");
        assertEquals(8, array.capacity());
        assertEquals(2, array.size());

        array.add("Python");
        assertEquals(8, array.capacity());
        assertEquals(3, array.size());

        array.add("Rust");
        assertEquals(8, array.capacity());
        assertEquals(4, array.size());

        array.add("Julia");
        assertEquals(8, array.capacity());
        assertEquals(5, array.size());

        array.add("Golang");
        assertEquals(8, array.capacity());
        assertEquals(6, array.size());

        array.add("PHP");
        assertEquals(8, array.capacity());
        assertEquals(7, array.size());

        array.add("C#");
        assertEquals(8, array.capacity());
        assertEquals(8, array.size());

        array.add("Assembly");
        assertEquals(16, array.capacity());
        assertEquals(9, array.size());

        assertEquals("JAVA", array.get(0));
        assertEquals("C++", array.get(1));
        assertEquals("Python", array.get(2));
        assertEquals("Rust", array.get(3));
        assertEquals("Julia", array.get(4));
        assertEquals("Golang", array.get(5));
        assertEquals("PHP", array.get(6));
        assertEquals("C#", array.get(7));
        assertEquals("Assembly", array.get(8));

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