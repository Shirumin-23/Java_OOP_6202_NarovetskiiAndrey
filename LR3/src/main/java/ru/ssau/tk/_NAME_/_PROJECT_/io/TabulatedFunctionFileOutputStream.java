package ru.ssau.tk._NAME_._PROJECT_.io;

import ru.ssau.tk._NAME_._PROJECT_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.SqrFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.TabulatedFunction;
import ru.ssau.tk._NAME_._PROJECT_.functions.factory.TabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args){
        String arrayFunctionFilePath = "output/array function.bin";
        String linkedListFunctionFilePath = "output/linked list function.bin";
        try (
                BufferedOutputStream arrayFunctionWriter = new BufferedOutputStream(new FileOutputStream(arrayFunctionFilePath));
                BufferedOutputStream linkedListFunctionWriter = new BufferedOutputStream(new FileOutputStream(linkedListFunctionFilePath))
        ) {
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 5);
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{1.123,2.1221,3.99999}, new double[]{1.23, 44, 90});
            FunctionsIO.writeTabulatedFunction(arrayFunctionWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListFunctionWriter, linkedListFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException{
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int count = dataInputStream.readInt();
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count; i++){
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }
        return factory.create(xValues,yValues);
    }
}
