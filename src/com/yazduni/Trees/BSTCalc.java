package com.yazduni.Trees;

import com.yazduni.utils.Calculator;
import com.yazduni.utils.Token;
import com.yazduni.utils.Tokenizer;

import java.util.ArrayList;
import java.util.Stack;

public class BSTCalc {

    private Node root;

    public BSTCalc() {
        this.root = null;
    }

    public BSTCalc(String expression) {
        buildFromExpression(expression);
    }

    public void buildFromExpression(String expression) {
        ArrayList<Token> nodes = Tokenizer.tokenize(expression);
        nodes = Calculator.translateToPostOrder(nodes);
        Stack<Node> stack = new Stack<>();

        for (Token token : nodes) {
            if (token.isNumber()) {
                Node node = new Node(token, Node.TYPE.NUMBER);
                stack.push(node);
            } else {
                Node node1 = stack.pop();
                Node node2 = stack.pop();

                Node newNode = new Node(token, node1, node2, Node.TYPE.OPERATOR);
                stack.push(newNode);
            }
        }

        this.root = stack.pop();
    }

    private int getHeight(Node node) {
        if (node == null)
            return 0;

        if (node.isLeaf())
            return 1;

        return 1 + getHeight(node.getLeft()) + getHeight(node.getRight());
    }

    public int getHeight() {
        return getHeight(this.root);
    }


    private static class Node {
        private Token _value;
        private TYPE _type;

        private Node _left;
        private Node _right;

        public Node(Token value, TYPE type) {
            this._value = value;
            this._type = type;
        }

        public Node(Token value, Node left, Node right, TYPE type) {
            this._value = value;
            this._left = left;
            this._right = right;
            this._type = type;
        }

        public Node getLeft() {
            return this._left;
        }

        public Node getRight() {
            return this._right;
        }

        public void setLeft(Node node) {
            this._left = node;
        }

        public void setRight(Node node) {
            this._left = node;
        }

        public boolean isLeaf() {
            return this._left == null && this._right == null;
        }

        public enum TYPE {
            NUMBER(0), OPERATOR(1);

            private int value;

            TYPE(int value) {
                this.value = value;
            }

            public int getValue() {
                return this.value;
            }
        }
    }

}
