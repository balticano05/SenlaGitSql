package com.myioc.loaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.myioc.utils.StringConst.*;

public class PropertyLoader{

    private static Map<String, String> properties = new HashMap<String, String>();

    static{
        loadProperties(FILE_PROPERTIES);
    }

    private static void loadProperties(String propertiesFile){
        Properties properties = new Properties();

        try(InputStream inputStreamProperties = new FileInputStream(propertiesFile)){

            if (inputStreamProperties != null) {
                properties.load(inputStreamProperties);

                for (String key : properties.stringPropertyNames()) {
                    PropertyLoader.properties.put(key, properties.getProperty(key));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(ERROR_FILE_PROPERTIES_NOT_FOUND+e);
        } catch (IOException e) {
            throw new RuntimeException( ERROR_WITH_LOADING_FILE_PROPERTIES+e);
        }
    }

    public static Map<String, String> getProperties() {
        return properties;
    }
}
