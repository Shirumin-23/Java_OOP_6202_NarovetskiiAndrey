package ru.ssau.tk._NAME_._PROJECT_.functions;

import ru.ssau.tk._NAME_._PROJECT_.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk._NAME_._PROJECT_.exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction
{
    protected int count;
    protected abstract int floorIndexOfX(double x);
    protected abstract double extrapolateLeft(double x);
    protected abstract double extrapolateRight(double x);
    protected abstract double interpolate(double x, int floorIndex);
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY)
    {
        return leftY + (rightY - leftY)*(x - leftX) / (rightX - leftX);
    }

    @Override
    public double apply(double x)
    {
        if(x < this.leftBound())
            return extrapolateLeft(x);
        else if (x > rightBound())
            return extrapolateRight(x);
        else
        {
            if (indexOfX(x) != -1)
                return getY(indexOfX(x));
            else
                return interpolate(x, floorIndexOfX(x));
        }
    }

    public static void checkLengthIsTheSame (double[] xValues, double[] yValues){
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Length of xValues and yValues are different");
        }
    }

    public static void checkSorted (double[] xValues){
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1]) {
                throw new ArrayIsNotSortedException("Array xValues is not sorted.");
            }
        }
    }

    @Override
    public  String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName())
                .append(" size = ")
                .append(getCount())
                .append("\n");

        for (Point point : this) {
            sb.append("[")
                    .append(point.x)
                    .append("; ")
                    .append(point.y)
                    .append("]\n");
        }

        return sb.toString();
    }
}