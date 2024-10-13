package com.mobiquity.packer.parser;

import com.mobiquity.packer.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern ITEM_PATTERN = Pattern.compile("\\((\\d+),(\\d+\\.?\\d*),€(\\d+)\\)");

    /**
     * Parses the weight limit from a line of input.
     *
     * @param line The input line (e.g., "81 : (1,53.38,€45) (2,88.62,€98)...").
     * @return The weight limit as a double.
     */
    public double parseWeightLimit(String line) {
        return Double.parseDouble(line.split(":")[0].trim());
    }

    /**
     * Parses the list of items from a line of input.
     *
     * @param line The input line.
     * @return A list of Item objects.
     */
    public List<Item> parseItems(String line) {
        List<Item> items = new ArrayList<>();
        String[] itemsPartArray = line.split(":");

        // Check if the itemsPart is empty
        if (itemsPartArray.length < 2) {
            return items; // Return an empty list if no items are present
        }
        String itemsPart = itemsPartArray[1].trim();

        Matcher matcher = ITEM_PATTERN.matcher(itemsPart);

        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1));
            double weight = Double.parseDouble(matcher.group(2));
            int cost = Integer.parseInt(matcher.group(3));

            items.add(new Item(index, weight, cost));
        }

        return items;
    }
}
