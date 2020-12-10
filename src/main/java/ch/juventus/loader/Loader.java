package ch.juventus.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class Loader {

    private Logger logger = LoggerFactory.getLogger(Loader.class);
    private HttpURLConnection connection = null;

    protected String get(String url) {
        try {
            URL path = new URL(url);
            connection = (HttpURLConnection) path.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (IOException e) {
            logger.error("Failed to load puzzle from " + url);
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

}
