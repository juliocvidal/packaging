package com.mobiquity.packer;

import com.mobiquity.packer.algorithm.BacktrackingSolver;
import com.mobiquity.packer.parser.InputParser;
import com.mobiquity.packer.solver.Solver;
import com.mobiquity.packer.util.ResultFormatter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar package-challenge.jar <file-path>");
            System.exit(1);
        }

        String filePath = args[0];

        try {
            Solver solver = new Solver(
                    new BacktrackingSolver(),
                    new InputParser(),
                    new ResultFormatter()
            );

            String result = solver.pack(filePath);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
