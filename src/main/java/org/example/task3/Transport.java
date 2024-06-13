package org.example.task3;

public abstract class Transport {
    public String name;

    public Transport(String name) {
        this.name = name;
    }

    public abstract double eta(Point a, Point b);
}
