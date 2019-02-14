package support.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;

import support.datas.DataProvider;

public class ReadProperties {

    /**
     * 
     * @param key
     * @return element path from object file
     */
    public static String getConfigSelenium(String key) {
        Properties obj = new Properties();
        String value = null;
        key = key.replace(" ", "-");
        String remote = System.getProperty("propertiesConfig");
        if (remote == null || "".equals(remote)) {
            value = getData(obj, key, "configSelenium.properties");
        } else {
            try {
                InputStream inputStream = new FileInputStream(remote);
                obj.load(inputStream);
                inputStream.close();
            }
            catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            value = obj.getProperty(key);
            if (StringUtils.isEmpty(value)) {
                value = getData(obj, key, "configSelenium.properties");
            }
        }
        return StringUtils.trim(value);
    }

    private static String getData(Properties obj, String key, String nameFile) {
        try {
            String file = new DataProvider().getPropertiesFile(nameFile);
            InputStream inputStream = new FileInputStream(file);
            obj.load(inputStream);
            inputStream.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        } 
        return obj.getProperty(key);
    }

    public static String getElementsAndUrls(String key) {
        Properties obj = new Properties();
        String value = null;
        key = key.replace(" ", "-");
        String remote = System.getProperty("propertiesConfig");
        if (remote == null || "".equals(remote)) {
            value = getData(obj, key, "configElementsAndBrowsers.properties");
        } else {
            try {
                InputStream inputStream = new FileInputStream(remote);
                obj.load(inputStream);
                inputStream.close();
            }
            catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            value = obj.getProperty(key);
            if (StringUtils.isEmpty(value)) {
                value = getData(obj, key, "configElementsAndBrowsers.properties");
            }
        }
        return StringUtils.trim(value);
    }

}
