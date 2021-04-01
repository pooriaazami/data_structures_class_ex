package com.yazduni.Quera;

import com.yazduni.Lists.DoublyLinkedList;

public class TypeMachine {

    private DoublyLinkedList<Character> text;
    private DoublyLinkedList.ItemIterator iterator;

    private void initialize() {
        text = new DoublyLinkedList<>();
        iterator = text.getIterator();

        text.append('\0');
    }

    public TypeMachine() {
        initialize();
    }

    public void reset() {
        initialize();
    }

    private void executeCommand(char c) {
        switch (c) {
            case 'B':
            case 'b':
                iterator.removeAndMoveBackward();
                break;
            case 'D':
            case 'd':
                iterator.removeAndMoveForward();
                break;
            case 'H':
            case 'h':
                iterator.moveToHead();
                break;
            case 'E':
            case 'e':
                iterator.moveToTail();
                break;
            case '<':
                iterator.prev();
                break;
            case '>':
                iterator.next();
                break;
            default:
                iterator.addNext(c);
                iterator.next();
        }
    }

    public void processInput(String input) {
        iterator.moveToHead();

        for (int i = 0; i < input.length(); i++)
            executeCommand(input.charAt(i));


    }

    @Override
    public String toString() {
        if (text.size() == 1) {
            return "There is no text";
        }

        String textStr = "";
        DoublyLinkedList.ItemIterator printIterator = text.getIterator(0);
        while (printIterator.hasNext()) {
            printIterator.next();
            textStr += printIterator.getData();
        }

        return textStr;
    }
}
