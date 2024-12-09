package ru.ssau.tk._NAME_._PROJECT_.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MockTabulatedFunctionTest {

    private MockTabulatedFunction function;

    @BeforeEach
    void setUp() {
        // Инициализация объекта MockTabulatedFunction перед каждым тестом
        function = new MockTabulatedFunction(1.0, 3.0, 2.0, 4.0);
    }

    @Test
    void testGetCount() {
        assertEquals(2, function.getCount());
    }

    @Test
    void testGetX() {
        assertEquals(1.0, function.getX(0));
        assertEquals(3.0, function.getX(1));
    }

    @Test
    void testGetY() {
        assertEquals(2.0, function.getY(0));
        assertEquals(4.0, function.getY(1));
    }

    @Test
    void testSetY() {
        function.setY(0, 5.0);
        assertEquals(5.0, function.getY(0));

        function.setY(1, 6.0);
        assertEquals(6.0, function.getY(1));
    }

    @Test
    void testIndexOfX() {
        assertEquals(0, function.indexOfX(1.0));
        assertEquals(1, function.indexOfX(3.0));
        assertEquals(-1, function.indexOfX(2.0)); // Не найдено
    }

    @Test
    void testIndexOfY() {
        assertEquals(0, function.indexOfY(2.0));
        assertEquals(1, function.indexOfY(4.0));
        assertEquals(-1, function.indexOfY(3.0)); // Не найдено
    }

    @Test
    void testLeftBound() {
        assertEquals(1.0, function.leftBound());
    }

    @Test
    void testRightBound() {
        assertEquals(3.0, function.rightBound());
    }

    @Test
    void testFloorIndexOfX() {
        assertEquals(0, function.floorIndexOfX(0.5)); // Меньше x0
        assertEquals(1, function.floorIndexOfX(3.5)); // Больше x1
        assertEquals(0, function.floorIndexOfX(2.0)); // Между x0 и x1
    }

    @Test
    void testExtrapolateLeft() {
        double expected = 2.0 + (0.0 - 1.0) * (4.0 - 2.0) / (3.0 - 1.0);
        assertEquals(expected, function.extrapolateLeft(0.0), 0.001);
    }

    @Test
    void testExtrapolateRight() {
        double expected = 4.0 + (4.0 - 3.0) * (4.0 - 2.0) / (3.0 - 1.0);
        assertEquals(expected, function.extrapolateRight(4.0), 0.001);
    }

    @Test
    void testInterpolate() {
        double expected = function.interpolate(2.0, 0); // Интерполяция по x
        assertEquals(3.0, expected, 0.001); // Ожидаемое значение по интерполяции
    }
}