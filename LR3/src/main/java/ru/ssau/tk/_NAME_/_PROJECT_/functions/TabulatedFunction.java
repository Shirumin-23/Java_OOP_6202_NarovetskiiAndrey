package ru.ssau.tk._NAME_._PROJECT_.functions;

import java.lang.Iterable;
import java.util.Iterator;

public interface TabulatedFunction extends MathFunction, Iterable<Point>
{
    int getCount();
    double getX(int index);
    double getY(int index);
    void setY(int index, double value);
    int indexOfX(double x);
    int indexOfY(double y);
    double leftBound();
    double rightBound();
}
