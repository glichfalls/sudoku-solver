package ch.juventus.loader;

public class RandomSudokuLoader extends Loader {

    private static final String PATH = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";

    public String getSudoku(int size, int level) {
        String url = String.format(PATH, size, level);
        return get(url);
    }

}
