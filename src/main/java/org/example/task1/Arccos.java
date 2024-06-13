package org.example.task1;

public class Arccos {
    public static double evaluate(double x) {
        if (Math.abs(x) > 1) {
            throw new IllegalArgumentException("X must be in range [-1, 1] for arccos");
        }

        double result = Math.PI / 2 - x, term = x;
        int n = 1;
        while (n < 10000) {
            term *= x * x * (2 * n - 1) * (2 * n - 1) / (2 * n) / (2 * n + 1);
            result -= term;
            n++;
        }
        return result;
    }
}
