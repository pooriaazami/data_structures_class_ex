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
        assertEquals(8, array.capacity());
        assertEquals(0, array.size());

        array.add("JAVA");
        assertEquals(8, array.capacity());
        assertEquals(1, array.size());

        array.insert("C++", 1);
        assertEquals(8, array.capacity());
        assertEquals(2, array.size());

        array.insert("Python", 0);
        assertEquals(8, array.capacity());
        assertEquals(3, array.size());

        array.insert("Rust", 1);
        assertEquals(8, array.capacity());
        assertEquals(4, array.size());

        array.insert("Julia", 2);
        assertEquals(8, array.capacity());
        assertEquals(5, array.size());

        array.insert("Golang", 1);
        assertEquals(8, array.capacity());
        assertEquals(6, array.size());

        array.insert("PHP", 0);
        assertEquals(8, array.capacity());
        assertEquals(7, array.size());

        array.insert("C#", 7);
        assertEquals(8, array.capacity());
        assertEquals(8, array.size());

        array.insert("Assembly", 6);
        assertEquals(16, array.capacity());
        assertEquals(9, array.size());

        assertEquals("PHP", array.get(0));
        assertEquals("Python", array.get(1));
        assertEquals("Golang", array.get(2));
        assertEquals("Rust", array.get(3));
        assertEquals("Julia", array.get(4));
        assertEquals("JAVA", array.get(5));
        assertEquals("Assembly", array.get(6));
        assertEquals("C++", array.get(7));
        assertEquals("C#", array.get(8));
    }

    @Test
    void remove() {
        assertThrows(IllegalArgumentException.class, () -> {
            array.remove(0);
        });

        array.add("JAVA");
        array.add("C++");
        array.add("Python");
        array.add("Rust");
        array.add("Julia");
        array.add("Golang");
        array.add("PHP");
        array.add("C#");
        array.add("Assembly");

        assertEquals(9, array.size());
        assertEquals(16, array.capacity());

        assertEquals("JAVA", array.remove(0));
        assertEquals(8, array.size());
        assertEquals(16, array.capacity());

        assertEquals("Assembly", array.remove(7));
        assertEquals(7, array.size());
        assertEquals(16, array.capacity());

        assertEquals("Python", array.remove(1));
        assertEquals(6, array.size());
        assertEquals(16, array.capacity());

        assertEquals("Julia", array.remove(2));
        assertEquals(5, array.size());
        assertEquals(16, array.capacity());

        assertEquals("Rust", array.remove(1));
        assertEquals(4, array.size());
        assertEquals(16, array.capacity());

        assertEquals("PHP", array.remove(2));
        assertEquals(3, array.size());
        assertEquals(8, array.capacity());
    }

    @Test
    void delete() {
    }
}