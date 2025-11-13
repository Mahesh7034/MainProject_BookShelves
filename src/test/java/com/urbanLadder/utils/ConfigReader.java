package com.urbanLadder.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties = new Properties();

	static {

		try {

			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");

			properties.load(fis);

			fis.close();

			System.out.println("Config properties loaded successfully.");

		} catch (IOException e) {

			System.out.println("Error: Unable to load config.properties file.");

			e.printStackTrace(); // Print detailed error message for debugging

		}

	}

	// Fetch value by key from loaded properties

	public static String getProperty(String key) {

		return properties.getProperty(key);

	}

}
