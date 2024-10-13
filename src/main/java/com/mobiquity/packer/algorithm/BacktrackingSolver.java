package com.mobiquity.packer.algorithm;

import com.mobiquity.packer.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BacktrackingSolver implements SolverStrategy {

    private List<Integer> bestSelection;
    private double maxCost;

    public List<Integer> solve(List<Item> items, double weightLimit) {
        bestSelection = new ArrayList<>();
        maxCost = 0.0;

        backtrack(items, 0, 0.0, 0.0, new ArrayList<>(), weightLimit);

        return bestSelection;
    }

    private void backtrack(List<Item> items, int index, double currentWeight, double currentCost, List<Integer> currentSelection, double weightLimit) {
        // Base case: if we have processed all items
        if (index == items.size()) {
            // Check if the current selection is better
            if (currentCost > maxCost || (currentCost == maxCost && currentWeight < getWeight(bestSelection, items))) {
                maxCost = currentCost;
                bestSelection = new ArrayList<>(currentSelection);
            }
            return;
        }

        // Include the current item if it does not exceed the weight limit
        Item currentItem = items.get(index);
        if (currentWeight + currentItem.getWeight() <= weightLimit) {
            currentSelection.add(currentItem.getIndex());
            backtrack(items, index + 1, currentWeight + currentItem.getWeight(), currentCost + currentItem.getCost(), currentSelection, weightLimit);
            currentSelection.removeLast(); // backtrack
        }

        // Exclude the current item
        backtrack(items, index + 1, currentWeight, currentCost, currentSelection, weightLimit);
    }

    private double getWeight(List<Integer> selectedItems, List<Item> items) {
        double totalWeight = 0.0;
        for (Integer index : selectedItems) {
            for (Item item : items) {
                if (Objects.equals(item.getIndex(), index)) {
                    totalWeight += item.getWeight();
                    break;
                }
            }
        }
        return totalWeight;
    }
}
