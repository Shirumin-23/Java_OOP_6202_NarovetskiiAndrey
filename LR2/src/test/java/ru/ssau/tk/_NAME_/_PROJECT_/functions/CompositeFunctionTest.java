package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeFunctionTest {

    private static class FirstFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return x + 1; // f(x) = x + 1
        }
    }

    private static class SecondFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return 2 * x; // g(x) = 2x
        }
    }

    @Test
    public void testApply() {
        MathFunction f = new FirstFunction();
        MathFunction g = new SecondFunction();
        CompositeFunction compositeFunction = new CompositeFunction(f, g);

        // Проверяем, что результат композиции g(f(x)) равен 2(x + 1)
        double input = 3.0;
        double expected = 2 * (input + 1); // g(f(3)) = 2(3 + 1) = 8
        double result = compositeFunction.apply(input);

        assertEquals(expected, result, 0.0001);
    }
}