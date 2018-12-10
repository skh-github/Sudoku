package com.ikea.assignment.sudoku;

/**
 * This class takes the input Soduku grids and solves the puzzle using the
 * general <a href="https://en.wikipedia.org/wiki/Backtracking" > backtracking
 * </a> algorithm recursively.
 * 
 * More info on <a href="https://en.wikipedia.org/wiki/Sudoku"> Sudoku </a>
 * 
 * 
 * 
 * @author khavsyed
 *
 */
public class SudokuSolver {

	public int[][] grid = new int[SudokuConstants.SUDOKU_SIZE][SudokuConstants.SUDOKU_SIZE];

	/**
	 * Looping through the given row to check if a possible number already exists in
	 * the row?
	 * 
	 * @param row
	 * @param number
	 * @return
	 */
	private boolean isInRow(int row, int number) {
		for (int i = 0; i < SudokuConstants.SUDOKU_SIZE; i++)
			if (grid[row][i] == number)
				return true;

		return false;
	}

	/**
	 * Looping through the column to check if a possible number already exists in
	 * the column?
	 * 
	 * @param col
	 * @param number
	 * @return
	 */
	private boolean isInCol(int col, int number) {
		for (int i = 0; i < SudokuConstants.SUDOKU_SIZE; i++)
			if (grid[i][col] == number)
				return true;

		return false;
	}

	/**
	 * Looping through the 3*3 box to check if a possible number already exists in
	 * the box?
	 * 
	 * @param row
	 * @param col
	 * @param number
	 * @return
	 */
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;

		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (grid[i][j] == number)
					return true;

		return false;
	}

	/**
	 * Method delegating the row check, column check & box check for given number
	 * 
	 * @param row
	 * @param col
	 * @param number The possible number that can meet the Sudoku rules for a cell
	 * @return
	 */
	private boolean isValid(int row, int col, int number) {
		return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
	}

	/**
	 * The key method that is invoke recursively to find the best number using
	 * backtracking (elimination technique)
	 * 
	 * @return
	 */
	public boolean solve() {
		for (int row = 0; row < SudokuConstants.SUDOKU_SIZE; row++) {
			// for each row & column...
			for (int col = 0; col < SudokuConstants.SUDOKU_SIZE; col++) {
				// find empty cell to overwrite with the correct number
				if (grid[row][col] == SudokuConstants.EMPTY_CELL_VALUE) {
					// loop through the possible numbers 1-9
					for (int number = 1; number <= SudokuConstants.SUDOKU_SIZE; number++) {
						if (isValid(row, col, number)) {
							// so it satisfies the row/col/box rules of Sudoku
							grid[row][col] = number;

							if (solve()) { // invoking solve() method backtracking recursively
								return true;
							} else { // if not a solution, we empty the cell and we continue
								grid[row][col] = SudokuConstants.EMPTY_CELL_VALUE;
							}
						}
					}

					return false; // we return false
				}
			}
		}

		return true; // sudoku solved
	}

	public void display() {
		for (int i = 0; i < SudokuConstants.SUDOKU_SIZE; i++) {
			for (int j = 0; j < SudokuConstants.SUDOKU_SIZE; j++) {
				System.out.print(" " + grid[i][j]);
			}

			System.out.println();
		}

		System.out.println();
	}

}
