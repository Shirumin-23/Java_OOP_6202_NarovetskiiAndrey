package ru.ssau.tk._NAME_._PROJECT_.functions;

public interface MathFunction {
    double apply(double x);
    default CompositeFunction andThen(MathFunction afterFunction)
    {
        return new CompositeFunction(this, afterFunction);
    }
}