package com.yazduni.Quera.TestcaseGenerators;

import com.yazduni.Lists.DynamicStack;
import com.yazduni.Lists.Stack;

public class StackLogger<E> {

    private String basePath;
    private DynamicStack<E> stack;

    public StackLogger(String basePath) {
        this.basePath = basePath;
        stack = new DynamicStack<>();
    }

    private void logToConsole(String res) {
        System.out.printf("[log]: %s size = %d capacity = %d\n", res, stack.size(), stack.capacity());
    }

    public void push(E data) {
        stack.push(data);
        logToConsole("Null");
    }

    public void pop() {
        logToConsole(stack.pop().toString());
    }

    public void top() {
        logToConsole(stack.top().toString());
    }
}
