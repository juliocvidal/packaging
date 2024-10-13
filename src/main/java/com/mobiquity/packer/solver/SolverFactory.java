package com.mobiquity.packer.solver;

import com.mobiquity.packer.algorithm.BacktrackingSolver;
import com.mobiquity.packer.algorithm.SolverStrategy;

public class SolverFactory {

    private SolverFactory() {
        // Prevent instantiation
    }

    public static SolverStrategy getSolver(String algorithmType) {
        if ("backtracking".equalsIgnoreCase(algorithmType)) {
            return new BacktrackingSolver();
        }
        // More strategies can be added here
        throw new IllegalArgumentException("Unknown solver strategy: " + algorithmType);
    }
}

