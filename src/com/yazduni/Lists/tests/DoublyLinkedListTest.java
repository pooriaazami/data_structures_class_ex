package com.yazduni.Lists.tests;

import com.yazduni.Lists.DoublyLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    private DoublyLinkedList<String> list;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
    }

    @org.junit.jupiter.api.Test
    void append() {
        list.append("C++");
        list.append("JAVA");
        list.append("Python");
        list.append("C#");

        assertEquals(4, list.size());
    }

    @org.junit.jupiter.api.Test
    void insertAndGet() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.get(0);
        });

        list.append("C++");
        assertEquals("C++", list.get(0));


        list.append("Java");

        assertEquals("C++", list.get(0));
        assertEquals("Java", list.get(1));

        assertThrows(IllegalArgumentException.class, () -> {
            list.get(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            list.get(2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            list.get(10);
        });
    }

    @org.junit.jupiter.api.Test
    void insert() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.insert(-1, "Java");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            list.insert(2, "Java");
        });

        list.append("C++");
        list.insert(0, "Java");

        assertEquals("Java", list.get(0));
        assertEquals("Java", list.getHead());
        assertEquals("C++", list.getTail());

        list.insert(1, "Python");
        assertEquals(list.get(0), "Java");
        assertEquals(list.get(1), "Python");
        assertEquals(list.get(2), "C++");


        list.insert(1, "PHP");
        list.insert(3, "Golang");
        list.insert(2, "Rust");

        assertEquals("Java", list.get(0));
        assertEquals("PHP", list.get(1));
        assertEquals("Rust", list.get(2));
        assertEquals("Python", list.get(3));
        assertEquals("Golang", list.get(4));
        assertEquals("C++", list.get(5));

        assertEquals("Java", list.getHead());
        assertEquals("C++", list.getTail());

        assertThrows(IllegalArgumentException.class, () -> {
            list.insert(100, "Java");
        });
    }

    @org.junit.jupiter.api.Test
    void remove() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.remove(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            list.remove(1);
        });

        list.append("Java");
        list.append("C++");
        list.append("Python");
        list.append("Golang");
        list.append("Rust");
        list.append("PHP");

        assertEquals("Java", list.remove(0));
        assertEquals(5, list.size());

        assertEquals("PHP", list.remove(4));
        assertEquals(4, list.size());

        assertEquals("Python", list.remove(1));

        assertThrows(IllegalArgumentException.class, () -> {
            list.remove(100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            list.remove(-1);
        });
    }

    @org.junit.jupiter.api.Test
    void pop() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.pop();
        });

        list.append("Java");
        list.append("C++");
        list.append("Python");

        assertEquals("Python", list.pop());
        assertEquals(2, list.size());

        assertEquals("C++", list.pop());
        assertEquals(1, list.size());

        assertEquals("Java", list.pop());
        assertEquals(0, list.size());

        assertThrows(IllegalArgumentException.class, () -> {
            list.pop();
        });
    }

    @org.junit.jupiter.api.Test
    void getHead() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.getHead();
        });

        list.append("C++");
        assertEquals("C++", list.getHead());

        list.append("Java");
        assertEquals("C++", list.getHead());
    }

    @org.junit.jupiter.api.Test
    void getTail() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.getHead();
        });

        list.append("C++");
        assertEquals("C++", list.getTail());

        list.append("Java");
        assertEquals("Java", list.getTail());
    }

    @org.junit.jupiter.api.Test
    void iteratorMovement() {
        DoublyLinkedList.ItemIterator iterator = list.getIterator();

        assertThrows(IllegalArgumentException.class, () -> {
            iterator.moveToHead();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            iterator.moveToTail();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            iterator.moveTo(1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            iterator.moveTo(10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            iterator.moveTo(-1);
        });

        list.append("Java");

        iterator.moveToHead();
        assertEquals("Java", iterator.getData());

        iterator.moveToTail();
        assertEquals("Java", iterator.getData());

        list.append("C++");
        iterator.moveToHead();
        assertEquals("Java", iterator.getData());

        iterator.moveToTail();
        assertEquals("C++", iterator.getData());


        //Java --> 0
        //C++ --> 1
        list.append("Python"); // --> 2
        list.append("Golang"); // --> 3
        list.append("PHP"); // --> 4
        list.append("Rust"); // --> 5
        list.append("Julia");// --> 6

        iterator.moveToHead();

        assertEquals("Java", iterator.getData());
        iterator.next();

        assertEquals("C++", iterator.getData());
        iterator.next();

        assertEquals("Python", iterator.getData());
        iterator.next();

        assertEquals("Golang", iterator.getData());
        iterator.next();

        assertEquals("PHP", iterator.getData());
        iterator.next();

        assertEquals("Rust", iterator.getData());
        iterator.next();

        assertEquals("Julia", iterator.getData());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            iterator.next();
        });

        iterator.prev();
        assertEquals("Rust", iterator.getData());

        iterator.prev();
        assertEquals("PHP", iterator.getData());

        iterator.prev();
        assertEquals("Golang", iterator.getData());

        iterator.prev();
        assertEquals("Python", iterator.getData());

        iterator.prev();
        assertEquals("C++", iterator.getData());

        iterator.prev();
        assertEquals("Java", iterator.getData());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            iterator.prev();
        });

        iterator.moveTo(0);
        assertEquals("Java", iterator.getData());

        iterator.moveTo(2);
        assertEquals("Python", iterator.getData());

        iterator.moveTo(5);
        assertEquals("Rust", iterator.getData());

        iterator.moveTo(6);
        assertEquals("Julia", iterator.getData());

        assertThrows(IllegalArgumentException.class, () -> {
            iterator.moveTo(10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            iterator.moveTo(-10);
        });
    }

    @org.junit.jupiter.api.Test
    void insertionAndRemove() {
        DoublyLinkedList.ItemIterator iterator = list.getIterator();

        assertThrows(NullPointerException.class, () -> {
            iterator.addPrev("Error!");
        });

        assertThrows(NullPointerException.class, () -> {
            iterator.addNext("Error!");
        });

        assertThrows(NullPointerException.class, () -> {
            iterator.removeAndMoveForward();
        });

        assertThrows(NullPointerException.class, () -> {
            iterator.removeAndMoveBackward();
        });

        list.append("Java");
        list.append("C++");

        iterator.moveToHead();
        assertEquals("Java", iterator.getData());

        iterator.addNext("Python");
        iterator.next();
        assertEquals("Python", iterator.getData());

        iterator.addPrev("Golang");
        iterator.prev();
        assertEquals("Golang", iterator.getData());

        iterator.moveToTail();
        iterator.removeAndMoveBackward();
        assertEquals("Python", iterator.getData());

        iterator.moveToHead();
        iterator.removeAndMoveForward();
        assertEquals("Golang", iterator.getData());

        assertEquals(2, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            iterator.moveToHead();
            iterator.removeAndMoveBackward();
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            iterator.moveToTail();
            iterator.removeAndMoveForward();
        });

        iterator.moveToHead();
        list.insert(1, "Julia");
        list.insert(1, "PHP");

        iterator.addNext("C#");
        assertEquals("Golang", iterator.getData());

        iterator.next();
        assertEquals("C#", iterator.getData());

        iterator.next();
        assertEquals("PHP", iterator.getData());

        iterator.next();
        assertEquals("Julia", iterator.getData());

        iterator.next();
        assertEquals("Python", iterator.getData());

        iterator.moveToHead();
        iterator.addPrev("Pascal");
        iterator.moveToHead();
        assertEquals("Pascal", iterator.getData());

        iterator.moveToTail();
        iterator.addNext("Delphi");
        iterator.moveToTail();
        assertEquals("Delphi", iterator.getData());
    }

}