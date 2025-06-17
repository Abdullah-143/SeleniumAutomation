package org.qpros.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtil {

    private static Properties properties = new Properties();

    public static String getProperty(String filepath, String key) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}

