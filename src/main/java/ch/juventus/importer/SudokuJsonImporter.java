package ch.juventus.importer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class SudokuJsonImporter extends SudokuImporter {

    private final JSONParser parser = new JSONParser();

    @Override
    protected int getDimension(ArrayList<String> lines) throws IOException {
        try {
            return Long.valueOf(parse(lines).get("size").toString()).intValue();
        } catch (ClassCastException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    protected int getDimension(String puzzle) throws IOException {
        try {
            return Long.valueOf(parse(puzzle).get("size").toString()).intValue();
        } catch (ClassCastException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    protected int[][] getNumbers(String puzzle) throws IOException {
        return getNumbersFromJsonArray((JSONArray) parse(puzzle).get("squares"));
    }

    @Override
    protected int[][] getNumbers(ArrayList<String> lines) throws IOException {
        return getNumbersFromJsonArray((JSONArray) parse(lines).get("squares"));
    }

    private int[][] getNumbersFromJsonArray(JSONArray squares) {
        int[][] numbers = new int[size][size];
        for (Object sq : squares) {
            JSONObject square = (JSONObject) sq;
            int x = Integer.parseInt(square.get("x").toString());
            int y = Integer.parseInt(square.get("y").toString());
            int value = Integer.parseInt(square.get("value").toString());
            numbers[x][y] = value;
        }
        return numbers;
    }

    private JSONObject parse(ArrayList<String> lines) throws IOException {
        try {
            StringBuilder json = new StringBuilder();
            for(String line : lines) {
                json.append(line);
            }
            return (JSONObject) parser.parse(json.toString());
        } catch (ParseException e) {
            throw new IOException("Failed to parse sudoku json: " + e.getMessage());
        }
    }

    private JSONObject parse(String puzzle) throws IOException {
        try {
            return (JSONObject) parser.parse(puzzle);
        } catch (ParseException e) {
            throw new IOException("Failed to parse sudoku json: " + e.getMessage());
        }
    }

}
