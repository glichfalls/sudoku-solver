package ch.juventus.importer;


import ch.juventus.exceptions.ImportException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SudokuJsonImporter extends SudokuImporter {

    private JSONParser parser = new JSONParser();

    @Override
    protected int getDimensionFromFileContent(ArrayList<String> lines) throws IOException {
        JSONObject json = parse(lines);
        return ((Long) json.get("size")).intValue();
    }

    @Override
    protected int[][] getNumbersFromFileContent(ArrayList<String> lines) throws IOException {
        JSONObject json = parse(lines);
        int[][] puzzle = new int[size][];

        JSONArray squares = (JSONArray) json.get("squares");
        Iterator iterator = squares.iterator();

        while (squares.iterator().hasNext()) {
            Object square = iterator.next();
            System.out.println(square);
        }

        return puzzle;
    }

    private JSONObject parse(ArrayList<String> lines) {
        try {
            return (JSONObject) parser.parse(lines.toString());
        } catch (ParseException e) {
            throw new ImportException("Failed to parse sudoku json: " + e.getMessage());
        }
    }

}
