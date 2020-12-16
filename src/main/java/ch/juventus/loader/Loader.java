package ch.juventus.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class Loader implements PuzzleLoader {

    private final Logger logger = LoggerFactory.getLogger(Loader.class);

    protected String get(String url) {
        HttpURLConnection connection = null;
        try {
            URL path = new URL(url);
            connection = (HttpURLConnection) path.openConnection();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                logger.debug("Loaded puzzle: {}", response.toString());
                return response.toString();
            }
        } catch (IOException e) {
            logger.error("Failed to load puzzle from {}.", url);
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

}
