# Architectural Decision Record (ADR) for Packer

## ADR-001: Use of the Backtracking Algorithm for Solving the Package Challenge

**Date:** 2024-10-13  
**Status:** Accepted

### Context
The goal of this project is to create a solution for the Package Challenge, which involves determining the optimal combination of items to include in a package, adhering to a weight limit while maximizing the total cost.

### Decision
We decided to implement a **Backtracking Algorithm** to solve the knapsack problem for the following reasons:
1. **Correctness**: The backtracking algorithm ensures that all combinations of items are explored, guaranteeing the identification of the optimal solution.
2. **Simplicity**: This approach is straightforward to implement, making it easier to maintain and understand compared to more complex algorithms (e.g., dynamic programming).
3. **Flexibility**: It allows for easy modifications to the logic if the constraints or requirements change in the future.

### Consequences
- The performance may not be optimal for large datasets, but given the constraints (up to 15 items), this approach is feasible.
- The solution is clear and easy to follow, aiding in debugging and future development.

---

## ADR-002: Design of Modular Classes

**Date:** 2024-10-13  
**Status:** Accepted

### Context
To create a maintainable and extensible codebase, we needed to adopt a modular design pattern.

### Decision
We structured the project into several classes, each serving a distinct purpose:
1. **Item**: Represents an individual item with properties such as index, weight, and cost.
2. **SolverStrategy**: An interface that defines the method signature for solving the package problem.
3. **BacktrackingSolver**: Implements the SolverStrategy interface using a backtracking approach to find the optimal item combination.
4. **InputParser**: Responsible for parsing input strings, extracting weight limits, and creating Item objects.
5. **ResultFormatter**: Formats the results into a readable string output.

### Consequences
- Each class has a single responsibility, making it easier to manage and test.
- The modular approach facilitates future enhancements, such as adding new solving strategies or modifying the input format.

---

## ADR-003: Implementation of Input Parsing

**Date:** 2024-10-13  
**Status:** Accepted

### Context
Input parsing is critical for transforming raw input data into usable objects.

### Decision
We used a regex-based approach within the `InputParser` class to extract weight limits and item details. The decision included:
- Using regular expressions to validate and extract items efficiently.
- Returning an empty list when no items are found instead of throwing an exception.

### Consequences
- The parsing logic is robust, handling edge cases such as empty item lists gracefully.
- It maintains clarity and separation of concerns by keeping input parsing independent from the solving logic.

---

## ADR-004: Testing Strategy

**Date:** 2024-10-13  
**Status:** Accepted

### Context
Testing is essential for ensuring that the solution meets its requirements and behaves as expected.

### Decision
We adopted the following testing strategies:
1. **JUnit 5**: Used for unit testing classes to ensure their correctness.
2. **Integration Tests**: Conducted to verify that the entire workflow from parsing input to solving the package problem works as intended.

### Consequences
- Tests are focused on ensuring that individual components work correctly, allowing for easier debugging.
- Integration tests provide confidence that the overall system behaves correctly when all parts work together.

---

## ADR-005: Exception Handling

**Date:** 2024-10-13  
**Status:** Accepted

### Context
Robust error handling is essential for production-ready applications.

### Decision
We implemented custom exception handling in the `InputParser` class and throughout the project:
- Use of dedicated exceptions for specific error cases (e.g., parsing errors).
- Graceful handling of erroneous input, ensuring that the application provides meaningful feedback rather than crashing.

### Consequences
- Improved user experience by providing clear error messages and preventing application crashes.
- Enhanced maintainability by separating error handling from business logic.

---

This document serves as a record of the decisions made during the design and implementation phases of the Package Challenge solution. Future changes should consider the implications of these decisions and strive to maintain the architecture's integrity.
