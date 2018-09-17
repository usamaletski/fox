package supportMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileRead {
    public static Map<String, String> applicationProperties() throws IOException {
        return getProperties("src/main/resources/config.properties");
    }

    private static Map<String, String> getProperties(String fileName) throws IOException {
        Properties prop = new Properties();
        prop.load(readFile(fileName));
        Map<String, String> properties = new HashMap<>();
        Enumeration<Object> KeyValues = prop.keys();
        while (KeyValues.hasMoreElements()) {
            String key = (String) KeyValues.nextElement();
            String value = prop.getProperty(key);
            properties.put(key, System.getProperty(key, value));
        }
        return properties;
    }

    private static FileInputStream readFile(String file) throws FileNotFoundException {
        return new FileInputStream(new File(file));
    }
}