package com.yazduni.Quera.tests;

import com.yazduni.Quera.TestcaseGenerators.ArrayProblem;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void printArrays(ArrayList<Integer> numbers) {
        System.out.println("---------------------------------");
        for (int j = 0; j < numbers.size(); j++) {
            System.out.print(numbers.get(j) + "\t");
        }
        System.out.println();
    }

    public static void printDoubleArray(ArrayList<ArrayList<Integer>> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            printArrays(numbers.get(i));
        }
    }

    public static void main(String[] args) {
        ArrayProblem arrayProblem;
        try {
            for (int i = 3; i <= 20; i++) {
                arrayProblem = new ArrayProblem();
                arrayProblem.createTestCase("./testcases/", i);
                System.out.printf("#%d created\n", i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
