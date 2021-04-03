package com.yazduni.Quera;

import com.yazduni.Quera.TestcaseGenerators.StackLogger;

public class StackLoggerMain {
    public static void main(String[] args) {
        StackLogger<String> stackLogger = new StackLogger<>("");

        stackLogger.push("Java");
        stackLogger.push("C++");
        stackLogger.push("Python");
        stackLogger.top();
        stackLogger.pop();
        stackLogger.pop();
        stackLogger.top();
        stackLogger.push("C");
    }
}
