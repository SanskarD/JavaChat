package org.sanskar.chatapp.utils;

import java.util.ResourceBundle;

public class ConfigReader {
	private ConfigReader() {};
	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	public static String getValue(String key) {
		return rb.getString(key);
	}
}
