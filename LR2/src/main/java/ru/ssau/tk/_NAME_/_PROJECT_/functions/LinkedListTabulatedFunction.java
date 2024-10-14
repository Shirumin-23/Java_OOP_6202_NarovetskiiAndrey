package ru.ssau.tk._NAME_._PROJECT_.functions;

public class LinkedListTabulatedFunction extends ArrayTabulatedFunction implements Insertable, Removable {
    protected static final double EPSILON = 1e-9;

    protected static class Node {
        Node next;
        Node prev;
        double x;
        double y;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private Node head;

    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        super();
        for (int i = 0; i < xValues.length; i++)
            addNode(xValues[i], yValues[i]);
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        super();
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        this.count = count;
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + i * step;
            addNode(x, source.apply(x));
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    protected Node getNode(int index) {
        Node current = head;
        if (index < count / 2) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            for (int i = count; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (Math.abs(current.x - x) < EPSILON)
                return i;
            current = current.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (Math.abs(current.y - y) < EPSILON)
                return i;
            current = current.next;
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            return 0;
        }
        if (x > rightBound()) {
            return count;
        }
        Node current = head;
        for (int i = 0; i < count - 1; i++) {
            if (current.x <= x && current.next.x > x) {
                return i;
            }
            current = current.next;
        }
        return count - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1)
            return head.y;
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1)
            return head.y;
        Node last = head.prev;
        return interpolate(x, last.prev.x, last.x, last.prev.y, last.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return head.y;
        }
        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    protected Node floorNodeOfX(double x) {
        if (x < leftBound()) {
            return head;
        }
        if (x > rightBound()) {
            return head.prev;
        }
        Node current = head;
        while (current.next != head && current.next.x <= x) {
            current = current.next;
        }
        return current;
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            Node floorNode = floorNodeOfX(x);
            if (Math.abs(floorNode.x - x) < EPSILON)
                return floorNode.y;
            return interpolate(x, floorNode.x, floorNode.next.x, floorNode.y, floorNode.next.y);
        }
    }

    public void insert(double x, double y) {
        if (this.head == null) {
            addNode(x, y);
            return;
        } else {
            Node currentNode = this.head;

            // Проверка на существование x и обновление y
            do {
                if (Math.abs(currentNode.x - x) < EPSILON) {
                    currentNode.y = y;
                    return;
                }
                currentNode = currentNode.next;
            } while (currentNode != this.head);

            // Вставка нового узла в правильное место
            currentNode = this.head;
            do {
                if ((currentNode.x < x) && (currentNode.next.x > x)) {
                    Node newNode = new Node(x, y);
                    Node last = currentNode;
                    newNode.prev = last;
                    newNode.next = currentNode.next;
                    last.next = newNode;
                    currentNode.next.prev = newNode;
                    this.count++;
                    return;
                }
                currentNode = currentNode.next;
            } while (currentNode != this.head);

            // Вставка в начало списка
            if (leftBound() > x) {
                Node newNode = new Node(x, y);
                Node last = currentNode.prev;
                newNode.prev = last;
                newNode.next = currentNode;
                last.next = newNode;
                currentNode.prev = newNode;
                this.head = newNode;
                this.count++;
                return;
            } else {
                Node newNode = new Node(x, y);
                Node last = currentNode.prev;
                newNode.prev = last;
                newNode.next = currentNode;
                last.next = newNode;
                currentNode.prev = newNode;
                this.count++;
                return;
            }
        }
    }

    @Override
    public void remove(int index) {
        Node currentNode = head;

        if (count == 1) {
            head = null;
        } else if (index == 0) {
            // Удаление первого элемента
            head = head.next;
            head.prev = currentNode.prev;
            currentNode.prev.next = head;
        } else {
            // Поиск элемента по индексу
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }

            // Обновление ссылок предыдущего и следующего узлов
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;

            // Если удаляем последний элемент
            if (index == count - 1) {
                head.prev = currentNode.prev;

            }
        }
    }
}