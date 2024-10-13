package com.mobiquity.packer.algorithm;

import com.mobiquity.packer.model.Item;

import java.util.List;

public interface SolverStrategy {
    List<Integer> solve(List<Item> items, double maxWeight);
}
