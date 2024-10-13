package com.mobiquity.packer.algorithm;

import com.mobiquity.packer.model.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BacktrackingSolverTest {

    @Test
    void testKnapsackSolver() {
        SolverStrategy solver = new BacktrackingSolver();
        List<Item> items = List.of(
                new Item(1, 53.38, 45),
                new Item(2, 88.62, 98),
                new Item(3, 78.48, 3),
                new Item(4, 72.30, 76),
                new Item(5, 30.18, 9),
                new Item(6, 46.34, 48)
        );
        double maxWeight = 81.0;

        List<Integer> result = solver.solve(items, maxWeight);
        assertEquals(List.of(4), result);
    }
}

