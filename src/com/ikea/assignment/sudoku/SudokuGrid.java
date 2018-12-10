package com.ikea.assignment.sudoku;

/**
 * A class to parse input into a Sudoku grid
 * 
 * @author khavsyed
 *
 */
public class SudokuGrid {

	public int[][] grid = new int[SudokuConstants.SUDOKU_SIZE][SudokuConstants.SUDOKU_SIZE];

	public int[][] parse(String line) {

			String[] split = line.split("");
			int expectedInputLen = (SudokuConstants.SUDOKU_SIZE * SudokuConstants.SUDOKU_SIZE);
			// since Sudoku is 2D array the input should be 81 = 9*9 (sudoku.SUDOKU_SIZE=9)
			if (split.length != expectedInputLen) {
				throw new RuntimeException(
						"Expected input line length is :" + expectedInputLen + " but found:" + split.length);
			}

			int row = 0;
			int col = 0;

			for (String str : split) {
				if (str != null && !str.trim().isEmpty()) {
					// check if it is a number?
					if (str.matches("-?\\d+(\\.\\d+)?")) {
						grid[row][col] = Integer.parseInt(str);
					} else {
						grid[row][col] = SudokuConstants.EMPTY_CELL_VALUE;
					}
					col++;
					if (col == SudokuConstants.SUDOKU_SIZE) {
						// start a new row
						col = 0;
						row++;
					}

				} else {
					throw new RuntimeException("Invalid input character->'" + str + "'");
				}
			}
			return grid;
	}
}
