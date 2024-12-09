package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewtonMethodTest {

    private static class TestFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return x * x - 2; // f(x) = x^2 - 2
        }
    }

    private static class TestFunctionPrime implements MathFunction {
        @Override
        public double apply(double x) {
            return 2 * x; // f'(x) = 2x
        }
    }

    @Test
    public void testApply() {
        MathFunction f = new TestFunction();
        MathFunction fPrime = new TestFunctionPrime();
        double epsilon = 0.0001;
        NewtonMethod newtonMethod = new NewtonMethod(f, fPrime, epsilon);

        // Начальное приближение
        double initialGuess = 1.0;
        double result = newtonMethod.apply(initialGuess);

        // Проверяем, что результат близок к корню sqrt(2)
        assertEquals(Math.sqrt(2), result, epsilon);
    }
}
