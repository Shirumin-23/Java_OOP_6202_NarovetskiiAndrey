package ru.ssau.tk._NAME_._PROJECT_.operations;

import ru.ssau.tk._NAME_._PROJECT_.functions.TabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk._NAME_._PROJECT_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._NAME_._PROJECT_.operations.TabulatedFunctionOperationService;
import ru.ssau.tk._NAME_._PROJECT_.functions.Point;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;
    public TabulatedDifferentialOperator(){
        this.factory = new ArrayTabulatedFunctionFactory();
    }
    public TabulatedFunctionFactory getFactory(){return factory;}
    public void setFactory(TabulatedFunctionFactory factory){this.factory = factory;}
    @Override
    public TabulatedFunction derive(TabulatedFunction function){
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];

        for (int i = 0; i < points.length - 1; i++) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }

        xValues[points.length - 1] = points[points.length - 1].x;
        yValues[points.length - 1] = (points[points.length - 1].y - points[points.length - 2].y) / (points[points.length - 1].x - points[points.length - 2].x); // Последняя точка
        return factory.create(xValues, yValues);
    }
}
