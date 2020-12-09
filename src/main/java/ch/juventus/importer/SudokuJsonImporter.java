package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class SudokuJsonImporter extends SudokuImporter {

    private JSONParser parser = new JSONParser();

    @Override
    protected int getDimensionFromFileContent(ArrayList<String> lines) {
        JSONObject json = parse(lines);
        return ((Long) json.get("size")).intValue();
    }

    @Override
    protected int[][] getNumbersFromFileContent(ArrayList<String> lines) {
        JSONObject json = parse(lines);
        int[][] puzzle = new int[size][size];
        JSONArray squares = (JSONArray) json.get("squares");
        for (Object sq : squares) {
            JSONObject square = (JSONObject) sq;
            int x = Integer.parseInt(square.get("x").toString());
            int y = Integer.parseInt(square.get("y").toString());
            int value = Integer.parseInt(square.get("value").toString());
            puzzle[x][y] = value;
        }
        return puzzle;
    }

    private JSONObject parse(ArrayList<String> lines) {
        try {
            StringBuilder json = new StringBuilder();
            for(String line : lines) {
                json.append(line);
            }
            return (JSONObject) parser.parse(json.toString());
        } catch (ParseException e) {
            throw new ImportException("Failed to parse sudoku json: " + e.getMessage());
        }
    }

}
