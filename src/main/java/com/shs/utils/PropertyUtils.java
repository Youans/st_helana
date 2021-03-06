/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Youans
 */
public class PropertyUtils {

    public static String loadPropertyValue(String propertyFile, String key) {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("./Config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop.getProperty(key);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}
