package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {

//		File file = new File("./Configuration/config.properties");
		try {

			FileInputStream fis = new FileInputStream(new File("./Configuration/config.properties"));
			prop = new Properties();

			prop.load(fis);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	public String getApplicationUrl() {
		return prop.getProperty("baseURL");
	}

	public String getUsername() {
		return prop.getProperty("username");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}

	public String getChromepath() {
		return prop.getProperty("chromepath");
	}

	public String getFirefoxpath() {
		return prop.getProperty("firefoxpath");
	}

	public String getEdgepath() {
		return prop.getProperty("edgepath");
	}
}
