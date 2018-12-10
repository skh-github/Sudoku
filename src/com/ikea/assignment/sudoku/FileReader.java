package com.ikea.assignment.sudoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is to read the Sudoku puzzles from a text file
 * 
 * @author khavsyed
 *
 */
public class FileReader {

	public static List<String> readInputLines(String fileName) {
		try {
			return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
		} catch (IOException io) {
			io.printStackTrace();
		}
		return null;
	}
}
