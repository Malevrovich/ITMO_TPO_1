package org.example.test2;

import org.example.task2.BPlusTree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BPlusTreeTest {
    private final static int DEGREE = 6;

    public static Stream<Arguments> provideInsert() {
        List<Integer> aList = Arrays.asList(1, 3, 6, 12, 15, 366, 368, 1000000);
        List<ValueGenerator> bList = List.of(new DifferentKeyGenerator(), new ConstGenerator());
        List<ValueGenerator> cList = Arrays.asList(new ConstGenerator(), new DifferentGenerator());

        return aList.stream().flatMap(a ->
                bList.stream().flatMap(b ->
                        cList.stream().flatMap(c ->
                                Stream.of(Arguments.of(a, b, c))
                        )
                )
        );

    }

    public static Stream<Arguments> provideDelete() {
        List<Integer> aList = Arrays.asList(1, 3, 6, 12, 15, 366, 368);
        List<ValueGenerator> bList = List.of(new DifferentKeyGenerator());
        List<ValueGenerator> cList = Arrays.asList(new ConstGenerator(), new DifferentGenerator());

        return aList.stream().flatMap(a ->
                bList.stream().flatMap(b ->
                        cList.stream().flatMap(c ->
                                Stream.of(Arguments.of(a, b, c))
                        )
                )
        );

    }

    interface ValueGenerator {
        double generate(int x);
    }

    static class ConstGenerator implements ValueGenerator {
        @Override
        public double generate(int x) {
            return 3.1415;
        }
    }

    static class DifferentGenerator implements ValueGenerator {
        @Override
        public double generate(int x) {
            return 3.1415 + x;
        }
    }

    static class DifferentKeyGenerator implements ValueGenerator {
        @Override
        public double generate(int x) {
            return x;
        }
    }

    @ParameterizedTest
    @MethodSource("provideInsert")
    void insertSearch(int number, ValueGenerator keyGenerator, ValueGenerator valueGenerator) {
        BPlusTree tree = new BPlusTree(DEGREE);

        for (int i = 0; i < number; i++) {
            int key = (int) keyGenerator.generate(i);
            double value = valueGenerator.generate(key);
            tree.insert(key, value);
        }

        for (int i = 0; i < number; i++) {
            int key = (int) keyGenerator.generate(i);
            assertEquals(tree.search(key), valueGenerator.generate(key));
        }
    }

    @ParameterizedTest
    @MethodSource("provideDelete")
    void deleteHalfSearch(int number, ValueGenerator keyGenerator, ValueGenerator valueGenerator) {
        BPlusTree tree = new BPlusTree(DEGREE);

        for (int i = 0; i < number; i++) {
            int key = (int) keyGenerator.generate(i);
            System.out.println("Generating " + key + " = " + valueGenerator.generate(key));
            tree.insert(key, valueGenerator.generate(key));
        }

        for (int i = 0; i < number / 2; i++) {
            int key = (int) keyGenerator.generate(i);
            System.out.println("Deleting " + key + " = " + valueGenerator.generate(key));
            tree.delete(key);
        }

        for (int i = 0; i < number / 2; i++) {
            int key = (int) keyGenerator.generate(i);
            assertNull(tree.search(key));
        }

        for (int i = number / 2; i < number; i++) {
            int key = (int) keyGenerator.generate(i);
            assertEquals(tree.search(key), valueGenerator.generate(key));
        }
    }

    @Test
    public void deleteEmpty() {
        BPlusTree tree = new BPlusTree(DEGREE);

        assertThrows(IllegalArgumentException.class, () -> {
            tree.delete(4);
        });
    }

}
