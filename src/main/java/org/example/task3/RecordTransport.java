package org.example.task3;

import java.util.Objects;

public class RecordTransport extends Transport {

    public RecordTransport() {
        super("Рекорд");
    }

    @Override
    public double eta(Point a, Point b) {
        if (Objects.equals(a.name, b.name)) {
            return 0;
        }
        if (Objects.equals(a.name, "Здесь")) {
            if (Objects.equals(b.name, "Дамогран")) {
                return 4.99;
            }
        }
        if (Objects.equals(b.name, "Здесь")) {
            if (Objects.equals(a.name, "Дамогран")) {
                return 4.99;
            }
        }
        throw new IllegalArgumentException();
    }
}

