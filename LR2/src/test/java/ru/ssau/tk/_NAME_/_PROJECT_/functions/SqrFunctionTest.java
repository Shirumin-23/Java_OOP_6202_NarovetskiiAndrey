package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        SqrFunction sqrFunction = new SqrFunction();

        // Тестируем различные значения
        assertEquals(0.0, sqrFunction.apply(0.0), 0.0001);
        assertEquals(1.0, sqrFunction.apply(1.0), 0.0001);
        assertEquals(4.0, sqrFunction.apply(2.0), 0.0001);
        assertEquals(9.0, sqrFunction.apply(3.0), 0.0001);
        assertEquals(16.0, sqrFunction.apply(4.0), 0.0001);
        assertEquals(25.0, sqrFunction.apply(5.0), 0.0001);
        assertEquals(1.0, sqrFunction.apply(-1.0), 0.0001); // Проверка на отрицательные числа
        assertEquals(Double.NaN, sqrFunction.apply(Double.NaN), 0.0001); // Проверка на NaN
    }
}