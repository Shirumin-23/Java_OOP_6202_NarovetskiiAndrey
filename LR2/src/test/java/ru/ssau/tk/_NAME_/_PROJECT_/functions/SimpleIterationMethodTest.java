package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleIterationMethodTest {

    private static class TestFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return Math.sqrt(x + 2); // g(x) = sqrt(x + 2)
        }
    }

    @Test
    public void testApply() {
        MathFunction g = new TestFunction();
        double epsilon = 0.0001;
        SimpleIterationMethod simpleIterationMethod = new SimpleIterationMethod(g, epsilon);

        // Начальное приближение
        double initialGuess = 1.0;
        double result = simpleIterationMethod.apply(initialGuess);

        // Проверяем, что результат близок к фиксированной точке 2
        assertEquals(2.0, result, epsilon);
    }
}