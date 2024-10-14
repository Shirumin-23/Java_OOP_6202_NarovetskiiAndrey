package ru.ssau.tk._NAME_._PROJECT_.functions;

public class SimpleIterationMethod implements MathFunction{
    private final MathFunction g;
    private final double epsilon;

    public SimpleIterationMethod(MathFunction g, double epsilon) {
        this.g = g;
        this.epsilon = epsilon;
    }

    @Override
    public double apply(double x) {
        double xNext = g.apply(x);
        while (Math.abs(xNext - x) > epsilon) {
            x = xNext;
            xNext = g.apply(x);
        }
        return xNext;
    }
}
