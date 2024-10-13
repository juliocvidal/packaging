package com.mobiquity.packer.solver;

import com.mobiquity.packer.algorithm.SolverStrategy;
import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.parser.InputParser;
import com.mobiquity.packer.util.ResultFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolverTest {

    private Solver solver;

    @BeforeEach
    public void setUp() {
        // Initialize the required dependencies
        SolverStrategy strategy = SolverFactory.getSolver("backtracking");
        InputParser parser = new InputParser();
        ResultFormatter formatter = new ResultFormatter();

        solver = new Solver(strategy, parser, formatter);
    }

    @Test
    void testPackFromFile() {
        // Read the input file from resources
        String filePath = "src/test/resources/example_input";
        String result = solver.pack(filePath);

        // Expected result based on the input data
        String expectedResult = """
                4
                -
                2,7
                8,9
                """;

        // Assert the output matches the expected result
        assertEquals(expectedResult.trim(), result.trim());
    }

    @Test
    void testPackWithNullFilePath() {
        assertThrows(APIException.class, () -> solver.pack(null));
    }

    @Test
    void testPackWithEmptyFilePath() {
        assertThrows(APIException.class, () -> solver.pack(""));
    }

    @Test
    void testPackWithNonExistentFile() {
        assertThrows(APIException.class, () -> solver.pack("non_existent_file.txt"));
    }
}
