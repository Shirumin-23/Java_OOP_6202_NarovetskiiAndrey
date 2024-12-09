package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        IdentityFunction identityFunction = new IdentityFunction();

        // Тестируем различные значения
        assertEquals(0.0, identityFunction.apply(0.0), 0.0001);
        assertEquals(1.0, identityFunction.apply(1.0), 0.0001);
        assertEquals(-1.0, identityFunction.apply(-1.0), 0.0001);
        assertEquals(42.0, identityFunction.apply(42.0), 0.0001);
        assertEquals(Double.NaN, identityFunction.apply(Double.NaN), 0.0001);
    }
}