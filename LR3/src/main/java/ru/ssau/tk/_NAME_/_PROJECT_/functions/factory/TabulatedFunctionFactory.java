package ru.ssau.tk._NAME_._PROJECT_.functions.factory;

import ru.ssau.tk._NAME_._PROJECT_.functions.StrictTabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.TabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.UnmodifiableTabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues){
        return new StrictTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createUnmodifiable(double[] xValues, double[] yValues){
        return new UnmodifiableTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues){
        return new UnmodifiableTabulatedFunction(new StrictTabulatedFunction(create(xValues, yValues)));
    }
}
