package com.myioc.loaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.myioc.utils.StringConst.FILE_PROPERTIES_PATH;
import static com.myioc.utils.StringConst.EXCEPTION_FILE_PROPERTIES_NOT_FOUND;
import static com.myioc.utils.StringConst.EXCEPTION_LOADING_FILE_PROPERTIES;

public class PropertyLoader {

    static Properties properties = new Properties();

    static {
        loadProperties(FILE_PROPERTIES_PATH);
    }

    private static void loadProperties(String propertiesFile) {
        try (InputStream inputStreamProperties = new FileInputStream(propertiesFile)) {
            properties.load(inputStreamProperties);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(EXCEPTION_FILE_PROPERTIES_NOT_FOUND + e);
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_LOADING_FILE_PROPERTIES + e);
        }
    }

    public static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

}