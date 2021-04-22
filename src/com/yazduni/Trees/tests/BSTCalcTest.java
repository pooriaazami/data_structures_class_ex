package com.yazduni.Trees.tests;

import com.yazduni.Trees.BSTCalc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTCalcTest {

    @Test
    void buildFromExpression() {
        BSTCalc calc = new BSTCalc("1+2*3");
        calc.printTree();
        assertEquals(3, calc.getHeight());

        calc = new BSTCalc("12.5*65.47+34/56-348/2323+236.34");
        calc.printTree();
        assertEquals(5, calc.getHeight());
    }
}