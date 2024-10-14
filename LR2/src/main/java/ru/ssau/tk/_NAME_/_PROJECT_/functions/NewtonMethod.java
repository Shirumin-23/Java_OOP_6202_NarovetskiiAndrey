package ru.ssau.tk._NAME_._PROJECT_.functions;

public class NewtonMethod implements MathFunction {
    private final MathFunction f;
    private final MathFunction fPrime;
    private final double epsilon;

    public NewtonMethod(MathFunction f, MathFunction fPrime, double epsilon) {
        this.f = f;
        this.fPrime = fPrime;
        this.epsilon = epsilon;
    }

    @Override
    public double apply(double x) {
        double xNext = x - f.apply(x) / fPrime.apply(x);
        while (Math.abs(xNext - x) > epsilon) {
            x = xNext;
            xNext = x - f.apply(x) / fPrime.apply(x);
        }
        return xNext;
    }
}
