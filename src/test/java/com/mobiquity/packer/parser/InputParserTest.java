package com.mobiquity.packer.parser;

import com.mobiquity.packer.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputParserTest {

    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @Test
    void testParseWeightLimit() {
        String input = "81 : (1,53.38,€45) (2,88.62,€98)";
        double weightLimit = inputParser.parseWeightLimit(input);
        assertEquals(81.0, weightLimit);
    }

    @Test
    void testParseItems() {
        String input = "81 : (1,53.38,€45) (2,88.62,€98)";
        List<Item> items = inputParser.parseItems(input);

        Item first = items.getFirst();
        assertEquals(2, items.size());
        assertEquals(1, first.getIndex());
        assertEquals(53.38, first.getWeight());
        assertEquals(45, first.getCost());
    }

    @Test
    void testParseNoItems() {
        String input = "8 :";
        List<Item> items = inputParser.parseItems(input);
        assertTrue(items.isEmpty());
    }
}

