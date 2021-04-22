package com.yazduni.Trees.tests;

import com.yazduni.Trees.BSTCalc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTCalcTest {

    @Test
    void buildFromExpression() {
        BSTCalc calc = new BSTCalc("1+2*3");
        assertEquals(3, calc.getHeight());
    }
}