package ru.ssau.tk._NAME_._PROJECT_.operations;

import ru.ssau.tk._NAME_._PROJECT_.functions.MathFunction;

public interface DifferentialOperator <T extends MathFunction> {
    T derive(T function);
}
