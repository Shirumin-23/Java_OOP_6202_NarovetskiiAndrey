package ru.ssau.tk._NAME_._PROJECT_.io;

import ru.ssau.tk._NAME_._PROJECT_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.SqrFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.TabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        // Адрес файла для сериализации
        String filePath = "output/serialized_array_functions.bin";

        // Создание табулированной функции
        TabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 11);

        // Создание оператора для нахождения производных
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        TabulatedFunction firstDerivative = operator.derive(function);
        TabulatedFunction secondDerivative = operator.derive(firstDerivative);

        // Сериализация функций
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            FunctionsIO.serialize(bos, function);
            FunctionsIO.serialize(bos, firstDerivative);
            FunctionsIO.serialize(bos, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация функций
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            TabulatedFunction deserializedFunction = FunctionsIO.deserialize(bis);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bis);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bis);

            // Вывод значений функций в консоль
            System.out.println("Original Function: " + deserializedFunction);
            System.out.println("First Derivative: " + deserializedFirstDerivative);
            System.out.println("Second Derivative: " + deserializedSecondDerivative);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
