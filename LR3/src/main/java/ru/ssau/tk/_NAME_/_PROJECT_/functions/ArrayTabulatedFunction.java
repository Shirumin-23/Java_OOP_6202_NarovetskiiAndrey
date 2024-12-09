package ru.ssau.tk._NAME_._PROJECT_.functions;

import ru.ssau.tk._NAME_._PROJECT_.exceptions.InterpolationException;

import java.io.Serial;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.Serializable;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable
{
    @Serial
    private static final long serialVersionUID = -257690217202668589L;
    public ArrayTabulatedFunction(){}
    protected double[] xValues;
    protected double[] yValues;
    protected int count;
    public ArrayTabulatedFunction(double[] xValues, double[] yValues)
    {
        if (xValues.length < 2 || yValues.length < 2) {throw new IllegalArgumentException("Table length is less than the minimum of 2 points.");}
        if (xValues.length != yValues.length) {throw new IllegalArgumentException("error, xValues and yValues must have the same length");}
        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues,count);
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
    }
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count)
    {
        if (count < 2) {throw new IllegalArgumentException("Table length is less than the minimum of 2 points");}
        if (xFrom > xTo)
        {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];
        if (xFrom == xTo)
        {
            Arrays.fill(xValues, xFrom);
            Arrays.fill(yValues, source.apply(xFrom));
        }
        else
        {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++)
            {
                xValues[i] = xFrom + i * step;
                yValues[i] = source.apply(xValues[i]);
            }
        }
    }
    @Override
    public int getCount(){return count;}
    @Override
    public double getX(int index){
        if(index < 0 || index >= count)
            throw new IllegalArgumentException("Index out of");
        return xValues[index];
    }
    @Override
    public double getY(int index){
        if(index < 0 || index >= count)
            throw new IllegalArgumentException("Index out of");
        return yValues[index];}
    @Override
    public void setY(int index, double value){yValues[index] = value;}
    @Override
    public int indexOfX(double x)
    {
        for (int i = 0; i < count; i++)
        {
            if (xValues[i] == x)
                return i;
        }
        return -1;
    }
    @Override
    public int indexOfY(double y)
    {
        for (int i = 0; i < count; i++)
        {
            if (yValues[i] == y)
                return i;
        }
        return -1;
    }
    @Override
    public double leftBound(){return xValues[0];}
    @Override
    public double rightBound(){return xValues[count - 1];}
    @Override
    protected int floorIndexOfX(double x)
    {
        if (x < xValues[0])
        {
            throw new IllegalArgumentException("x is less than the left bound");
        }
        for (int i = 0; i < count; i++)
        {
            if (xValues[i] <= x && xValues[i+1] > x) return i;
        }
        return count - 1;
    }
    @Override
    protected double extrapolateLeft(double x)
    {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }
    @Override
    protected double extrapolateRight(double x)
    {
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }
    @Override
    protected double interpolate(double x, int floorIndex)
    {
        if (x < xValues[floorIndex] || x > xValues[floorIndex + 1]) {
            throw new InterpolationException("x is out of interpolation range.");
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }
    @Override
    public void insert(double x,double y){
        int index=indexOfX(x);
        if(index == -1){
            int bef;
            int aft;
            double[] newArrX= new double[count+1];
            double[] newArrY= new double[count+1];
            if(x<leftBound()){
                bef=0;
            }
            else if(x>rightBound()){
                bef=count;
            }
            else{
                bef=floorIndexOfX(x)+1;
            }
            newArrX[bef]=x;
            newArrY[bef]=y;
            for(int i = 0;i<bef;i++){
                newArrX[i]=this.xValues[i];
                newArrY[i]=this.yValues[i];
            }
            for(int i = bef;i<count;i++){
                newArrX[i+1]=this.xValues[i];
                newArrY[i+1]=this.yValues[i];
            }
            count++;
            this.xValues=newArrX;
            this.yValues=newArrY;
        }
        else{
            this.yValues[index]=y;
        }
    }
    @Override
    public void remove(int index) {
        // Создаем новые массивы меньшего размера
        double[] newXValues = new double[count - 1];
        double[] newYValues = new double[count - 1];

        // Копируем элементы до удаляемого индекса
        System.arraycopy(xValues, 0, newXValues, 0, index);
        System.arraycopy(yValues, 0, newYValues, 0, index);

        // Копируем элементы после удаляемого индекса
        if (index < count - 1) {
            System.arraycopy(xValues, index + 1, newXValues, index, count - index - 1);
            System.arraycopy(yValues, index + 1, newYValues, index, count - index - 1);
        }

        // Обновляем массивы и счетчик
        xValues = newXValues;
        yValues = newYValues;
        count--;
    }
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }
}
