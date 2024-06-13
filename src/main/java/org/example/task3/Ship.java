package org.example.task3;

import java.util.Objects;

public class Ship extends Transport {
    public Ship() {
        super("Корабль");
    }

    @Override
    public double eta(Point a, Point b) {
        if (Objects.equals(a.name, b.name)) {
            return 0;
        }
        if (Objects.equals(a.name, "Здесь")) {
            if (Objects.equals(b.name, "Дамогран")) {
                return 500000;
            }
        }
        if (Objects.equals(b.name, "Здесь")) {
            if (Objects.equals(a.name, "Дамогран")) {
                return 500000;
            }
        }
        throw new IllegalArgumentException();
    }
}
