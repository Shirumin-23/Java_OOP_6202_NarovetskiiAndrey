package ru.ssau.tk._NAME_._PROJECT_.functions;

public class ConstantFunction implements MathFunction
{
    private final double cons;
    public ConstantFunction(double cons)
    {
        this.cons = cons;
    }
    @Override
    public double apply(double x)
    {
        return cons;
    }
    public double GetConstant()
    {
        return cons;
    }
}
