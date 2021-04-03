package com.yazduni.Quera;

import com.yazduni.Quera.TestcaseGenerators.StackLogger;

import java.io.IOException;

public class StackLoggerMain {
    public static void main(String[] args) {
        StackLogger<String> stackLogger = new StackLogger<>("./testcases");

        try {
            stackLogger.createNewTestcase();

            stackLogger.top();
            stackLogger.pop();
            stackLogger.push("JAVA");
            stackLogger.pop();
            stackLogger.push("C++");
            stackLogger.push("Python");
            stackLogger.top();
            stackLogger.pop();
            stackLogger.top();
            stackLogger.end();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
