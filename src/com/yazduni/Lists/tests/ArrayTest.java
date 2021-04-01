package com.yazduni.Lists.tests;

import com.yazduni.Lists.Array;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {

    @Test
    void reshape() {
        int length = 32;
        Integer[] buffer = new Integer[length];
        for (int i = 0; i < length; i++)
            buffer[i] = i;

        Array<Integer> array = new Array(buffer);

        for (int i = 0; i < length; i++)
            assertEquals(i, array.get(i));

        array.reshape(new int[]{8, 4});

        for (int i = 0; i < length; i++) {
            assertEquals(i, array.get(i % 8, i / 8));
        }
    }

    @Test
    void get() {
        int length = 32;
        Integer[] buffer = new Integer[length];
        for (int i = 0; i < length; i++)
            buffer[i] = i;

        Array<Integer> array = new Array(buffer);

        for (int i = 0; i < length; i++)
            assertEquals(i, array.get(i));

        array = new Array<>(buffer, new int[]{8, 4});

        for (int i = 0; i < length; i++) {
            assertEquals(i, array.get(i % 8, i / 8));
        }


        ArrayList<Integer> bufferArray = new ArrayList<>();
        for (int i = 0; i < length; i++)
            bufferArray.add(i);

        array = new Array(buffer);

        for (int i = 0; i < length; i++)
            assertEquals(i, array.get(i));

        ArrayList<Integer> test = new ArrayList<>();
        test.add(8);
        test.add(4);

        array = new Array<>(bufferArray, test);

        for (int i = 0; i < length; i++) {
            assertEquals(i, array.get(i % 8, i / 8));
        }
    }

}