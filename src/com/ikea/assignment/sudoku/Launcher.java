package com.ikea.assignment.sudoku;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Launcher {

	public static void main(String[] args) {
		SudokuSolver sudokuSol = new SudokuSolver();
		List<String> inLines = FileReader.readInputLines(SudokuConstants.INPUT_FILE_NAME);
		SudokuGrid grid = new SudokuGrid();
		Map<Integer, SudokuCell> sudokuCellsMap = new HashMap<>();

		inLines.forEach(line -> {
			sudokuSol.grid = grid.parse(line);
			grid.parse2(sudokuSol.grid, sudokuCellsMap);
			System.out.println("Given input:");
			sudokuSol.display();
			if (sudokuSol.solve()) {
				System.out.println("Sudoku solved:");
				sudokuSol.display();
			} else {
				System.out.println("Sudoku can't be solved!");
			}
		});
	}
}