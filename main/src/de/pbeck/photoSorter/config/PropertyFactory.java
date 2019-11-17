package de.pbeck.photoSorter.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFactory {

    private static Properties properties;

    private static String configPath = "./app.properties";

    private static String logDir = PhotoSorterProperty.LOG_DIRECTORY.getKey();


    public static String readProperty(PhotoSorterProperty property) {
        if (properties == null) {
            initProperties();
        }
        String propertyValue = properties.getProperty(property.getKey());
        return propertyValue != null ? propertyValue : property.getDefaultValue();
    }

    public static Properties getProperties() {
        if (properties == null) {
            initProperties();
        }
        return properties;
    }

    public static String getConfigPath() {
        return configPath;
    }

    private static void initProperties() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(configPath)) {
            properties.load(fileInputStream);
        } catch (IOException ex) {
            System.out.println("Problem loading PhotoSorterProperty from " + configPath + " :" + ex.getCause());
        }
    }
}

