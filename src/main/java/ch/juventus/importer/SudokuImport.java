package ch.juventus.importer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SudokuImport {

    public String[] read(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        ArrayList<String> sudoku = new ArrayList<String>();
        int data = reader.read();
        while (data != -1) {
            char character = (char) data;
            System.out.print(character);
            data = reader.read();
            sudoku.add(character);
        }
        reader.close();
    }

}
