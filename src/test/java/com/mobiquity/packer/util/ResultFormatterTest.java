package com.mobiquity.packer.util;

import com.mobiquity.packer.model.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultFormatterTest {

    @Test
    void formatWithItems() {
        ResultFormatter formatter = new ResultFormatter();
        List<Item> items = List.of(new Item(1, 10, 20), new Item(2, 15, 25), new Item(3, 20, 30));
        String result = formatter.format(items);
        assertEquals("1,2,3", result);
    }

    @Test
    void formatWithNoItems() {
        ResultFormatter formatter = new ResultFormatter();
        List<Item> items = List.of();
        String result = formatter.format(items);
        assertEquals("-", result);
    }

    @Test
    void formatWithSingleItem() {
        ResultFormatter formatter = new ResultFormatter();
        List<Item> items = List.of(new Item(1, 10, 20));
        String result = formatter.format(items);
        assertEquals("1", result);
    }

}
