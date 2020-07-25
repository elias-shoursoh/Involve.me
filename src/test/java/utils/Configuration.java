package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* Read data from config.properties file */
public class Configuration {
	// Load a properties file and retrieve the property value.
	public static String readProperty(String key) {
		String value = "";
		try (InputStream input = new FileInputStream(".\\src\\test\\java\\data\\config.properties")) {
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			// get the property value
			value = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return value;
	}
}
