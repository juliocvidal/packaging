package com.mobiquity.packer.util;

import com.mobiquity.packer.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ResultFormatter {

    /**
     * Formats the selected items into a comma-separated string of their indices.
     *
     * @param items The list of selected items.
     * @return The formatted string (e.g., "2,7" or "-" if no items are selected).
     */
    public String format(List<Item> items) {
        if (items.isEmpty()) {
            return "-";
        }
        return items.stream()
                .map(item -> String.valueOf(item.getIndex()))
                .collect(Collectors.joining(","));
    }
}
