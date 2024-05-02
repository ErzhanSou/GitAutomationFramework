package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties;

    static {
        String path = "src/test/resources/configurations.properties";
        try {
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("File not found" + path);
            e.printStackTrace();

        }
    }
    public static String getProperty(String key){
        return  properties.getProperty(key);
    }
}
