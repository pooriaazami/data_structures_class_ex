package com.yazduni.Trees;

import com.yazduni.utils.Token;
import com.yazduni.utils.Tokenizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class TestcaseGenerator {

    private int counter;

    private String basePath;

    private File inputFile;
    private File outputFile;

    private FileWriter inputFileWriter;
    private FileWriter outputFileWriter;

    public TestcaseGenerator(String basePath) {
        this.counter = 0;
        this.basePath = basePath;

        File dir = new File(basePath);
        File in = new File(basePath + "\\in");
        File out = new File(basePath + "\\out");

        dir.mkdir();
        in.mkdir();
        out.mkdir();
    }

    private String createInputPath() {
        return String.format("%s\\in\\input%d.txt", this.basePath, counter);
    }

    private String createOutputPath() {
        return String.format("%s\\out\\output%d.txt", this.basePath, counter);
    }

    private void createInputFile(String input) throws IOException {
        this.inputFile = new File(createInputPath());
        this.inputFileWriter = new FileWriter(this.inputFile);
        this.inputFile.createNewFile();

        this.inputFileWriter.write(input);
        this.inputFileWriter.close();
    }

    private void createOutputFile(int depth) throws IOException {
        this.outputFile = new File(createOutputPath());
        this.outputFileWriter = new FileWriter(this.outputFile);
        this.outputFile.createNewFile();

        this.outputFileWriter.write(String.valueOf(depth));
        this.outputFileWriter.close();
    }

    private String createInput(String expression) {
        ArrayList<Token> tokens = Tokenizer.tokenize(expression);
        String ans = "";

        for (Token t : tokens) {
            ans += t.getValue();
            ans += " ";
        }

        ans.strip();

        return ans;
    }

    public void createTestcase(String input) throws IOException {
        this.counter++;

        System.out.println(input);
        BSTCalc bstCalc = new BSTCalc(input);
        bstCalc.printTree();
        int ans = bstCalc.getHeight();
        System.out.println(ans);

        createInputFile(createInput(input));
        createOutputFile(ans);
    }


}

public class BSTTestCaseGenerator {
    public static void main(String[] args) {
        TestcaseGenerator testcaseGenerator = new TestcaseGenerator("bst_testcases");

        try {
            testcaseGenerator.createTestcase("1+2.5*3");
            testcaseGenerator.createTestcase("2.5+5.678/537");
            testcaseGenerator.createTestcase("3.4565*4.556+12.65*34");
            testcaseGenerator.createTestcase("0+0.5*5.67+756");
            testcaseGenerator.createTestcase("5.76*4567\\347+66*465+456");
            testcaseGenerator.createTestcase("1+2+3+4+5+6+7+8+9+10");
            testcaseGenerator.createTestcase("1*2+3*4+5*6+7*8");
            testcaseGenerator.createTestcase("1\\2+5.4*456+563.45-4567");
            testcaseGenerator.createTestcase("1+2*3+4*5+6*7+8");
            testcaseGenerator.createTestcase("12*34+56*78\\635+456-364+576");
            testcaseGenerator.createTestcase("12*65.34\\455+7465.764");
            testcaseGenerator.createTestcase("45.76+246\\456*123-867*643+678");
            testcaseGenerator.createTestcase("1*2*3\\4*5\\6\\7*7*8");
            testcaseGenerator.createTestcase("1+2\\3-4");
            testcaseGenerator.createTestcase("34*47.54\\675+764-734");
            testcaseGenerator.createTestcase("53*45.6543\\3459-65");
            testcaseGenerator.createTestcase("34*345-123\\457*78458-475.764*634.67");
            testcaseGenerator.createTestcase("2*3*5.6*794+2.4*56.4*4.6*3545");
            testcaseGenerator.createTestcase("1+2\\3-34.556*34+2*3*5.6*794+2.4*56.4*4.6*3545");
            testcaseGenerator.createTestcase("1+2*3+4\\56*457.345*3457*4975-546.56+3456*4567");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
