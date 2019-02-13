package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            properties = new Properties();
            properties.load(Files.newBufferedReader(Paths.get("resources", "application.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
