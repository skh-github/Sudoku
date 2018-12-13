package com.ikea.assignment.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to parse input into a Sudoku grid
 * 
 * @author khavsyed
 *
 */
public class SudokuGrid {

	public int[][] parse(String line) {

		int[][] grid = new int[SudokuConstants.SUDOKU_SIZE][SudokuConstants.SUDOKU_SIZE];

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

	public void parse2(int[][] grid, Map<Integer, SudokuCell> sudokuCellsMap) {
		int boxNum; // marking Sudoku's 1-9 boxes
		int rowNum;
		int colNum;
		for (int i = 0; i < SudokuConstants.SUDOKU_SIZE; i++) {

			for (int j = 0; j < SudokuConstants.SUDOKU_SIZE; j++) {

				boxNum = ((i / 3) * 3 + (j / 3) + 1);
				rowNum = i + 1;
				colNum = +j + 1;

				int cellValue = grid[i][j];
				if (cellValue == 0) {
					continue;
				}
				int cellAddrKey = 100 * boxNum + 10 * rowNum + colNum;
				SudokuCell sc = sudokuCellsMap.computeIfAbsent(cellAddrKey, sc0 -> new SudokuCell());
				sc.cellAddrKey = cellAddrKey;
				sc.cellValue = cellValue;

				Map<String, List<Integer>> numberCheckingLists = new HashMap<>();
				String boxKeyNum = "b" + boxNum;
				String rowKeyNum = "r" + rowNum;
				String colKeyNum = "c" + colNum;

				List<Integer> boxList = numberCheckingLists.computeIfAbsent(boxKeyNum, lst -> new ArrayList<>());
				boxList.add(cellValue);
				List<Integer> rowList = numberCheckingLists.computeIfAbsent(rowKeyNum, lst -> new ArrayList<>());
				rowList.add(cellValue);
				List<Integer> colList = numberCheckingLists.computeIfAbsent(colKeyNum, lst -> new ArrayList<>());
				colList.add(cellValue);

			}
		}

	}

}
