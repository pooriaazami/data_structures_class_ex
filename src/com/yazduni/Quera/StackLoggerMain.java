package com.yazduni.Quera;

import com.yazduni.Quera.TestcaseGenerators.StackLogger;

import java.io.IOException;
import java.util.Scanner;

public class StackLoggerMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StackLogger<Integer> stackLogger = new StackLogger<>("./testcases");

        int n = input.nextInt();
        input.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("-----------------------------------------------------------------");
            String command;
            try {
                stackLogger.createNewTestcase();
                do {
                    command = input.nextLine();

                    if (command.contains(" ")) {
                        String[] sep = command.split(" ");
                        stackLogger.push(Integer.parseInt(sep[1]));
                    } else {
                        switch (command) {
                            case "TOP":
                                stackLogger.top();
                                break;
                            case "POP":
                                stackLogger.pop();
                                break;
                            case "END":
                                stackLogger.end();
                        }
                    }
//
                } while (!command.equals("END"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
