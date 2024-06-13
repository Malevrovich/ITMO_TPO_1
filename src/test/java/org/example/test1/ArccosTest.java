package org.example.test1;

import org.example.task1.Arccos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArccosTest {

    private final static double EPS = 0.01;

    @ParameterizedTest
    @CsvSource({
            "-1, 3.14159265",
            "1, 0",
            "-0.98, 2.94125781",
            "0.98, 0.20033484",
            "-0.8, 2.49809154",
            "0.8, 0.64350111",
            "-0.5, 2.0943951",
            "0.5, 1.04719755",
            "0, 1.57079633"
    })
    void testArccosWithinRange(double x, double expected) {
        assertEquals(expected, Arccos.evaluate(x), EPS);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.1,
            -1.1,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    void testArccosOutsideRange(double x) {
        assertThrows(IllegalArgumentException.class, () -> Arccos.evaluate(x));
    }
}
