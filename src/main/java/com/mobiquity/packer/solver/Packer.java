package com.mobiquity.packer.solver;

import com.mobiquity.packer.algorithm.SolverStrategy;
import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.model.Item;
import com.mobiquity.packer.parser.InputParser;
import com.mobiquity.packer.util.ResultFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Packer {

    private final SolverStrategy strategy;
    private final InputParser inputParser;
    private final ResultFormatter resultFormatter;

    public Packer(SolverStrategy strategy, InputParser inputParser, ResultFormatter resultFormatter) {
        this.strategy = strategy;
        this.inputParser = inputParser;
        this.resultFormatter = resultFormatter;
    }

    /**
     * Reads the input file, parses each test case, solves it, and returns formatted results.
     *
     * @param filePath Path to the input file.
     * @return String representing the results of all test cases.
     * @throws APIException if file reading or parsing fails.
     */
    public String pack(String filePath) throws APIException {
        List<String> lines = readFromFile(filePath);
        StringBuilder output = new StringBuilder();

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                // Parse the input line into a weight limit and list of items
                double weightLimit = inputParser.parseWeightLimit(line);
                List<Item> items = inputParser.parseItems(line);

                // Solve the problem for the current test case, returning the indices of selected items
                List<Integer> selectedIndices = strategy.solve(items, weightLimit);

                // Map indices back to Item objects for result formatting
                List<Item> selectedItems = selectedIndices.stream()
                        .map(index -> items.stream()
                                .filter(item -> Objects.equals(item.getIndex(), index))
                                .findFirst()
                                .orElse(null)) // Handle potential missing items (although they shouldn't be missing)
                        .toList();

                // Format the result and append it to the output
                output.append(resultFormatter.format(selectedItems)).append("\n");
            }
        }

        return output.toString().trim(); // Remove trailing newline
    }

    private static List<String> readFromFile(String filePath) throws APIException {
        List<String> lines;
        if (filePath == null || filePath.isEmpty()) {
            throw new APIException("File path must not be null or empty");
        }
        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new APIException("Error reading file", e);
        }
        return lines;
    }
}
