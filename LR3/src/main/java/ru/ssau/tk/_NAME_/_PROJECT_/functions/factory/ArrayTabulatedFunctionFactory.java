package ru.ssau.tk._NAME_._PROJECT_.functions.factory;

import ru.ssau.tk._NAME_._PROJECT_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
