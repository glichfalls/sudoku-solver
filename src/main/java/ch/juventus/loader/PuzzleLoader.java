package ch.juventus.loader;

public interface PuzzleLoader {

    /**
     * Get random puzzle data JSON format as string with the selected difficulty
     * @param level the difficulty
     * @return decoded JSON string
     */
    String getRandom(int level);

    /**
     * Get random puzzle data JSON format as string
     * @return decoded JSON string
     */
    String getRandom();

}
