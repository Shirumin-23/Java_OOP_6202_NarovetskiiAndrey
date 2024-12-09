package ru.ssau.tk._NAME_._PROJECT_.operations;

import ru.ssau.tk._NAME_._PROJECT_.exceptions.InconsistentFunctionsException;
import ru.ssau.tk._NAME_._PROJECT_.functions.Point;
import ru.ssau.tk._NAME_._PROJECT_.functions.TabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._NAME_._PROJECT_.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            points[i++] = point;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);

        if (pointsA.length != pointsB.length) {
            throw new InconsistentFunctionsException("Functions have different number of points.");
        }
        int length = a.getCount();
        double[] xValues = new double[length];
        double[] yValues = new double[length];

        for (int i = 0; i < length; i++) {
            xValues[i] = pointsA[i].x;
            if (xValues[i] != pointsB[i].x) {
                throw new InconsistentFunctionsException("Functions have different x values.");
            }
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }
        return factory.create(xValues,yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);//сложение
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);//вычитание
    }

    public TabulatedFunction multiplication(TabulatedFunction a, TabulatedFunction b){
        return doOperation(a, b, (u,v) -> u * v);//умножение
    }

    public TabulatedFunction division(TabulatedFunction a, TabulatedFunction b){
        return doOperation(a, b, (u,v) -> u / v);//деление
    }
}
