package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class PropHelper {

    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass().getSimpleName());
    }

    public static String getPropertyValue(String valueName) {
        if (System.getProperty(valueName) != null) {
            String value = System.getProperty(valueName);
            if (!value.equals("")) {
                return System.getProperty(valueName);
            }
        }
        return getPropValueFromFile(valueName);
    }

    private static String getPropValueFromFile(String propertyName) {
        ResourceBundle configuration;
        configuration = ResourceBundle.getBundle("config");
        return configuration.getString(propertyName);
    }

}
