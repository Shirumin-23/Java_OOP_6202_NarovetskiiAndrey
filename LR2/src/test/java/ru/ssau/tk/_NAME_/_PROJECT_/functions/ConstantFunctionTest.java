package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConstantFunctionTest {

    @Test
    public void testApply() {
        double constantValue = 5.0;
        ConstantFunction constantFunction = new ConstantFunction(constantValue);

        // Проверяем, что apply возвращает константное значение
        for (double x = -10; x <= 10; x++) {
            assertEquals(constantValue, constantFunction.apply(x), 0.0001);
        }
    }

    @Test
    public void testGetConstant() {
        double constantValue = 7.5;
        ConstantFunction constantFunction = new ConstantFunction(constantValue);

        // Проверяем, что GetConstant возвращает правильное значение
        assertEquals(constantValue, constantFunction.GetConstant(), 0.0001);
    }
}