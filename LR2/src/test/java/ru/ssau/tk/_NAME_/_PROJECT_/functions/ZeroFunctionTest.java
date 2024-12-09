package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {

    @Test
    void testApply() {
        ZeroFunction obj = new ZeroFunction();
        Assertions.assertEquals(0, obj.apply(1));
        Assertions.assertEquals(0, obj.apply(4));
        Assertions.assertEquals(0, obj.apply(8888));
        Assertions.assertEquals(0, obj.apply(42));
    }
}