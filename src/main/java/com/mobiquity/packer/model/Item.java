package com.mobiquity.packer.model;

public record Item(int index, double weight, int cost) {

    public double getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    public Integer getIndex() {
        return index;
    }
}
