package ch.juventus.loader;

import java.util.Random;

public class SudokuLoader extends Loader {

    public static final int LEVEL_EASY = 1;
    public static final int LEVEL_MEDIUM = 2;
    public static final int LEVEL_HARD = 3;
    public static final int[] LEVELS = {
        LEVEL_EASY,
        LEVEL_MEDIUM,
        LEVEL_HARD
    };

    private static final String PATH = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";

    public String getRandom(int level) {
        return load(level);
    }

    public String getRandom() {
        int random = new Random().nextInt(LEVELS.length);
        return load(LEVELS[random]);
    }

    private String load(int level) {
        String url = String.format(PATH, 9, level);
        return get(url);
    }

}
