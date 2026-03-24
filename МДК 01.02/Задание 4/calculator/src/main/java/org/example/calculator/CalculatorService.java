package org.example.calculator;

public class CalculatorService {
    private double memory = 0;


    public double add(double a, double b) {
        return a + b;
    }
    public double subtract(double a, double b) {
        return a - b;
    }
    public double multiply(double a, double b) {
        return a * b;
    }
    public double divide(double a, double b) {
        if (b == 0);
        return a / b;
    }

    public double power(double a, double b) {
        return Math.pow(a, b);
    }
    public double sqrt(double a) {
        if (a < 0);
        return Math.sqrt(a);
    }

    public long factorial(int n) {
        if (n < 0);
        if (n > 20);
        long res = 1;
        for (int i = 1; i <= n; i++) res *= i;
        return res;
    }

    public void memSave(double v) {
        this.memory = v;
    }
    public double memRead() {
        return this.memory;
    }
    public void memAdd(double v) {
        this.memory += v;
    }
    public void memClear() {
        this.memory = 0;
    }
}
