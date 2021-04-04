package com.yazduni.Quera.TestcaseGenerators;

import com.yazduni.Lists.DynamicStack;
import com.yazduni.Lists.Stack;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

public class StackLogger<E> {

    private String basePath;
    private DynamicStack<E> stack;
    private int counter;

    private File inputFile;
    private File outputFile;
    private FileWriter inputFileWriter;
    private FileWriter outputFileWriter;

    public StackLogger(String basePath) {
        counter = 1;
        this.basePath = basePath;
        stack = new DynamicStack<>();

        File inputDir = new File(basePath + "/in/");
        File outputDir = new File(basePath + "/out/");

        inputDir.mkdirs();
        outputDir.mkdirs();
    }

    private void logToConsole(String res) {
        if (res.equals("ERROR\n"))
            System.out.println("ERROR");
        else if (res.equals("END"))
            System.out.println("END");
        else
            System.out.printf("[log]: %s size = %d capacity = %d\n", res, stack.size(), stack.capacity());
    }

    private void logToFile(String command, String res) throws IOException {
        inputFileWriter.append(command);

        if (res.equals("ERROR\n"))
            outputFileWriter.append(res);
        else if (!command.equals("END"))
            outputFileWriter.append(String.format("%s %d %d\n", res, stack.size(), stack.capacity()));
    }

    public void createNewTestcase() throws IOException {
        stack = new DynamicStack<>();

        String number = String.valueOf(counter++);
        inputFile = new File(basePath + "/in/input" + number + ".txt");
        outputFile = new File(basePath + "/out/output" + number + ".txt");
        inputFileWriter = new FileWriter(inputFile);
        outputFileWriter = new FileWriter(outputFile);
        inputFile.createNewFile();
        outputFile.createNewFile();
    }

    public void push(E data) throws IOException {
        stack.push(data);
        logToConsole("Null");
        logToFile("PUSH " + String.valueOf(data) + "\n", "NULL");
    }

    public void pop() throws IOException {

        String res;
        if (stack.size() == 0) {
            res = "ERROR\n";
        } else {
            res = stack.pop().toString();
        }

        logToConsole(res);
        logToFile("POP\n", res);
    }

    public void top() throws IOException {
        String res;
        if (stack.size() == 0) {
            res = "ERROR\n";
        } else {
            res = stack.top().toString();
        }

        logToConsole(res);
        logToFile("TOP\n", res);
    }

    public void end() throws IOException {
        logToConsole("END");
        logToFile("END", "");
        inputFileWriter.close();
        outputFileWriter.close();
    }
}
