package com.yazduni.Quera;

import java.util.ArrayList;
import java.util.Scanner;

class Token implements Comparable {

    private final String value;
    private final Priorities priorities;
    private final Type type;

    public Token(String value, Priorities priorities) {
        this.value = value;
        this.priorities = priorities;

        if (priorities == Priorities.NUMBER)
            this.type = Type.NUMBER;
        else
            this.type = Type.FUNCTION;
    }

    public String getValue() {
        return value;
    }

    public Priorities getPriorities() {
        return priorities;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Token)) {
            throw new IllegalArgumentException("You can compare token with token");
        }

        Token token = (Token) o;
        if (token.type != this.type) {
            throw new IllegalArgumentException("You can only compare tokens with same type");
        }

        if (this.getPriorities().getValue() < token.getPriorities().getValue())
            return 1;
        else if (this.getPriorities().getValue() > token.getPriorities().getValue())
            return -1;

        return 0;

    }

    public boolean isNumber() {
        return this.type.value == Type.NUMBER.value;
    }

    public boolean isLeftParenthesis() {
        return this.value.equals("(");
    }

    public boolean isRightParenthesis() {
        return this.value.equals(")");
    }

    public enum Priorities {
        FUNCTION(1), PRODUCT(2), SUM(3), NUMBER(4), PARENTHESES(5);

        private int value;

        Priorities(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    private enum Type {
        NUMBER(1), FUNCTION(2);
        private int value;

        Type(int value) {
            this.value = value;
        }
    }

    public double parseToDouble() {
        if (!this.isNumber()) {
            throw new IllegalArgumentException("You can not parse this token to double");
        }

        return Double.parseDouble(this.value);
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", priorities=" + priorities +
                ", type=" + type +
                '}';
    }
}

class Tokenizer {

    public static boolean isNumber(char c) {
        return (c >= '0' && c <= '9') || c == '.';
    }

    public static boolean isParenthesis(char c) {
        return c == '(' || c == ')';
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '\\' || c == '^' || c == '/';
    }

    public static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static void processBuffer(String buffer, boolean isBufferNumeric, ArrayList<Token> tokens) {
        if (buffer.length() > 0) {
            if (buffer.equals("pi")) {
                tokens.add(new Token("3.141592653589793", Token.Priorities.NUMBER));
                return;
            }
            if (buffer.equals("e")) {
                tokens.add(new Token("2.718281828459045", Token.Priorities.NUMBER));
                return;
            }

            if (isBufferNumeric) {
                tokens.add(new Token(buffer, Token.Priorities.NUMBER));
            } else {
                tokens.add(new Token(buffer, Token.Priorities.FUNCTION));
            }
        }
    }

    public static ArrayList<Token> tokenize(String expression) {
        ArrayList<Token> ans = new ArrayList<>();

        String buffer = "";
        boolean isBufferNumeric = true;
        int len = expression.length();
        for (int i = 0; i < len; i++) {

            if (expression.charAt(i) == ' ')
                continue;

            if (isParenthesis(expression.charAt(i))) {
                processBuffer(buffer, isBufferNumeric, ans);
                buffer = "";
                ans.add(new Token(String.valueOf(expression.charAt(i)), Token.Priorities.PARENTHESES));
            } else if (isOperator(expression.charAt(i))) {
                processBuffer(buffer, isBufferNumeric, ans);
                buffer = "";
                switch (expression.charAt(i)) {
                    case '+' -> ans.add(new Token("+", Token.Priorities.SUM));
                    case '-' -> {
                        if (ans.size() == 0 || !ans.get(ans.size() - 1).isNumber()) {
                            isBufferNumeric = true;
                            buffer += "-";
                        } else
                            ans.add(new Token("-", Token.Priorities.SUM));
                    }
                    case '*' -> ans.add(new Token("*", Token.Priorities.PRODUCT));
                    case '/', '\\' -> ans.add(new Token("\\", Token.Priorities.PRODUCT));
                    case '^' -> ans.add(new Token("^", Token.Priorities.FUNCTION));
                }
            } else if (isLetter(expression.charAt(i))) {
                if (buffer.length() > 0 && isBufferNumeric)
                    throw new IllegalArgumentException("Invalid expression");

                buffer += expression.charAt(i);
                isBufferNumeric = false;
            } else if (isNumber(expression.charAt(i))) {
                if (buffer.length() > 0 && !isBufferNumeric)
                    throw new IllegalArgumentException("Invalid expression");

                buffer += expression.charAt(i);
                isBufferNumeric = true;
            } else {
                throw new IllegalArgumentException("Invalid expression");
            }

        }

        processBuffer(buffer, isBufferNumeric, ans);

        return ans;
    }
}

interface AbstractStack<E> {

    void add(E data);

    E pop();

    int size();

    boolean isEmpty();

}

class Stack<E> implements AbstractStack<E> {

    @Override
    public void add(E data) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

class Calculator {

    public static ArrayList<Token> translateToPostOrder(ArrayList<Token> inOrder) {
        return null;
    }

    public static double calculatePostOrder(ArrayList<Token> postOrder) {
        return 0;
    }

    public static double calculate(String expression) {
        ArrayList<Token> tokenizedExpression = Tokenizer.tokenize(expression);
        ArrayList<Token> postOrderExpression = translateToPostOrder(tokenizedExpression);

        return calculatePostOrder(postOrderExpression);
    }


}

public class CalculatorTemplate {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String expression = input.nextLine();
        double answer = Calculator.calculate(expression);

        System.out.println(answer);

    }
}