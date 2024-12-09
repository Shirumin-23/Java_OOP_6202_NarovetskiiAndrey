package ru.ssau.tk._NAME_._PROJECT_.functions.factory;

import ru.ssau.tk._NAME_._PROJECT_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory{
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
