package com.yazduni.Quera.TestcaseGenerators;

import com.yazduni.Quera.TypeMachine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Q1 {

    private int count;

    private String rootPath;
    private String inputPath;
    private String outputPath;

    private TypeMachine typeMachine;


    public Q1() {
        count = 1;

        rootPath = "./testcases";
        inputPath = rootPath + "/in";
        outputPath = rootPath + "/out";

        typeMachine = new TypeMachine();

        createFolders();
    }

    private void createFolders() {
        File inputsDirectory = new File(inputPath);
        File outputsDirectory = new File(outputPath);

        inputsDirectory.mkdirs();
        outputsDirectory.mkdirs();
    }

    public void createTestcase(String input) {
        File inputFile = new File(inputPath + "/input" + count + ".txt");
        File outputFile = new File(outputPath + "/output" + count + ".txt");

        try (
                FileWriter inputFileWriter = new FileWriter(inputFile);
                FileWriter outputFileWriter = new FileWriter(outputFile)
        ) {
            inputFile.createNewFile();
            outputFile.createNewFile();

            typeMachine.reset();
            inputFileWriter.write(input);
            typeMachine.processInput(input);

            String answer = typeMachine.toString();

            outputFileWriter.write(answer);
            System.out.println(answer);

            count++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
