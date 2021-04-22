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

    public void buildFromExpression(String expression) {
        ArrayList<Token> nodes = Tokenizer.tokenize(expression);
        nodes = Calculator.translateToPostOrder(nodes);

        for (Token token : nodes) {

        }
    }

    private int getHeight(Node node) {
        if (node == null)
            return 0;

        return 1 + getHeight(node.getLeft()) + getHeight(node.getRight());
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
