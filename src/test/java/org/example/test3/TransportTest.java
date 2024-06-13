package org.example.test3;

import org.example.task3.Ship;
import org.example.task3.Point;
import org.example.task3.RecordTransport;
import org.example.task3.Transport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TransportTest {

    public static Stream<Arguments> provideTransport() {
        return Stream.of(
                Arguments.of(new RecordTransport()),
                Arguments.of(new Ship())
        );
    }
    @ParameterizedTest
    @MethodSource("provideTransport")
    public void testIllegalPoints(Transport transport) {
        Point a = new Point("here");
        Point b = new Point("Not here");
        assertThrows(IllegalArgumentException.class, () -> {
            transport.eta(a, b);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "Здесь, Дамогран, 4.99",
            "Дамогран, Здесь, 4.99",
            "Здесь, Здесь, 0",
    })
    public void testRecordTransportEq(String from, String to, double expected) {
        Point src = new Point(from);
        Point dst = new Point(to);

        assertEquals(expected, (new RecordTransport()).eta(src, dst), 0.000001);
    }

    @Test
    public void testRecordTransport() {
        assertTrue((new RecordTransport()).eta(new Point("Здесь"), new Point("Дамогран")) > 4.8);
        assertTrue((new RecordTransport()).eta(new Point("Здесь"), new Point("Дамогран")) < 5);
    }

    @ParameterizedTest
    @CsvSource({
            "Здесь, Дамогран, 500000",
            "Дамогран, Здесь, 500000",
            "Здесь, Здесь, 0",
    })
    public void testShip(String from, String to, double expected) {
        Point src = new Point(from);
        Point dst = new Point(to);

        assertEquals(expected, (new Ship()).eta(src, dst), 0.000001);
    }
}
