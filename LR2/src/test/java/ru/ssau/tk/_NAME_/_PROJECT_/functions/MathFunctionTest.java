package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathFunctionTest {

    @Test
    public void testAndThen() {
        MathFunction unitFunction = new UnitFunction();
        MathFunction zeroFunction = new ZeroFunction();

        // Создаем композицию функций: UnitFunction -> ZeroFunction
        MathFunction compositeFunction = unitFunction.andThen(zeroFunction);

        // Проверяем, что результат применения композиции равен 0
        assertEquals(0, compositeFunction.apply(5), "Композиция функций должна возвращать 0");
        assertEquals(0, compositeFunction.apply(-10), "Композиция функций должна возвращать 0");
        assertEquals(0, compositeFunction.apply(0), "Композиция функций должна возвращать 0");
    }
}
