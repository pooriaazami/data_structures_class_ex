package com.yazduni.Quera.TestcaseGenerators;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter #testcases: \t");
        int count = input.nextInt();

        Q1 typeMachineTestcaseGenerator = new Q1();
        for (int i = 1; i <= count; i++) {
            System.out.println("--- # " + i + " ----------------------------------------------");
            String inputData = input.next();
            typeMachineTestcaseGenerator.createTestcase(inputData);
        }
    }
}
