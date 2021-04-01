package com.yazduni.Quera.tests;

import com.yazduni.Quera.TypeMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeMachineTest {

    TypeMachine typeMachine;

    @BeforeEach
    void setUp() {
        typeMachine = new TypeMachine();
    }

    @Test
    void testcase1() {
        assertEquals("There is no text", typeMachine.toString());
    }

    @Test
    void testcase2() {
        typeMachine.processInput("123456789");
        assertEquals("123456789", typeMachine.toString());
    }

    @Test
    void testcase3() {
        typeMachine.processInput("123H12");
        assertEquals("12123", typeMachine.toString());
    }

    @Test
    void testcase4() {
        typeMachine.processInput("123H12E123");
        assertEquals("12123123", typeMachine.toString());
    }

    @Test
    void testcase5() {
        typeMachine.processInput("123<123<12>456H1230+E112");
        assertEquals("1230+12121234563112", typeMachine.toString());
    }

    @Test
    void testcase6() {
        typeMachine.processInput("789<<89HDd>123>B");
        assertEquals("91239", typeMachine.toString());
    }

    @Test
    void testcase7() {
        typeMachine.processInput("123BBB123BEEE456<<<DDDBB");
        assertEquals("There is no text", typeMachine.toString());
    }
}