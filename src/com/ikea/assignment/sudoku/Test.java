package com.ikea.assignment.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {

		int boxNum; // marking Sudoku's 1-9 boxes
		int rowNum;
		int colNum;
		int k = 0;
		Map<Integer, SudokuCell> sudokuCellsMap = new HashMap<>();

		for (int i = 0; i < SudokuConstants.SUDOKU_SIZE; i++) {

			for (int j = 0; j < SudokuConstants.SUDOKU_SIZE; j++) {

				boxNum = ((i / 3) * 3 + (j / 3) + 1);
				rowNum = i + 1;
				colNum = +j + 1;

				Map<String, List<Integer>> numberCheckingLists = new HashMap<>();
				String boxKeyNum = "b" + boxNum;
				String rowKeyNum = "r" + rowNum;
				String colKeyNum = "c" + colNum;

				List<Integer> list = numberCheckingLists.computeIfAbsent(boxKeyNum, lst -> new ArrayList<>());
				list.add(9);
				list.add(9);
				list.add(9);
				if (i % 3 == 0)
					list.add(9);

				System.out.println(numberCheckingLists.get(boxKeyNum).size());
				int cellAddrKey = 100 * boxNum + 10 * rowNum + colNum;
				SudokuCell sc2 = sudokuCellsMap.computeIfAbsent(cellAddrKey, sc0 -> new SudokuCell());
				sc2.cellAddrKey = cellAddrKey;
				sc2.cellValue = ++k;
				System.out.println(k + "." + (i + 1) + ":" + (j + 1) + "=" + sudokuCellsMap.get(cellAddrKey).cellValue);
			}
		}
	}

}
