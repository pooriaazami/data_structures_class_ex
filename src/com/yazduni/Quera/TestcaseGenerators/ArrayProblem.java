package com.yazduni.Quera.TestcaseGenerators;

import com.yazduni.Lists.Array;
import com.yazduni.Quera.ArrayReshape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class ArrayProblem {

    private ArrayList<Integer> numbers;
    private ArrayList<ArrayList<Integer>> shapes;
    private Array<Integer> array;

    public void reset() {
        ArrayReshape arrayReshape = new ArrayReshape();

        shapes = arrayReshape.generateShapes();
        numbers = arrayReshape.generateRandomArray(shapes.get(0).get(0));
        array = new Array<>(numbers);
    }

    public ArrayProblem() {
        reset();
    }


    private void writeArrayToFile(ArrayList<Integer> array, String path) throws IOException {
        File file = new File(path);

        try (
                FileWriter fileWriter = new FileWriter(file, true)
        ) {
            for (int i = 0; i < array.size(); i++)
                fileWriter.write(array.get(i) + "\t");
            fileWriter.write("\n");
        }
    }

    private void writeReverseArrayToFile(ArrayList<Integer> array, String path) throws IOException {
        File file = new File(path);

        try (
                FileWriter fileWriter = new FileWriter(file, true)
        ) {
            for (int i = array.size() - 1; i >= 0; i--)
                fileWriter.write(array.get(i) + "\t");
            fileWriter.write("\n");
        }
    }

    private void writeNumberToFile(int number, String path) throws IOException {
        File file = new File(path);

        try (
                FileWriter fileWriter = new FileWriter(file, true)
        ) {
            fileWriter.write(number + "\t");
            fileWriter.write("\n");
        }
    }

    private void singleArrayTestCase(String inputPath, String outputPath, int index) throws IOException {
        Random random = new Random();

        ArrayList<Integer> loc1 = new ArrayList<>();
        ArrayList<Integer> loc2 = new ArrayList<>();

        array.reshape(shapes.get(index));
        loc1.add(random.nextInt(shapes.get(index).get(0)));
        loc1.add(random.nextInt(shapes.get(index).get(1)));

        loc2.add(random.nextInt(shapes.get(index).get(0)));
        loc2.add(random.nextInt(shapes.get(index).get(1)));

        writeReverseArrayToFile(shapes.get(index), inputPath);
        writeReverseArrayToFile(loc1, inputPath);
        writeReverseArrayToFile(loc2, inputPath);

        writeNumberToFile(array.get(loc1), outputPath);
        writeNumberToFile(array.get(loc2), outputPath);
    }

    public void createTestCase(String basePath, int index) throws IOException {
        String inputPath = basePath + "in/input" + String.valueOf(index) + ".txt";
        String outputPath = basePath + "out/output" + String.valueOf(index) + ".txt";

        writeArrayToFile(shapes.get(0), inputPath);
        writeArrayToFile(numbers, inputPath);

        singleArrayTestCase(inputPath, outputPath, 1);
        singleArrayTestCase(inputPath, outputPath, 2);
    }


}
