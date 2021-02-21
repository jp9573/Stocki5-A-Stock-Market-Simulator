package com.csci5308.stocki5.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class main {
    public static void main(String[] args) {
        main app = new main();
        Properties prop = app.loadPropertiesFile("config.properties");
        prop.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    public Properties loadPropertiesFile(String filePath) {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Unable to load properties file : " + filePath);
        }
        return prop;
    }
}
