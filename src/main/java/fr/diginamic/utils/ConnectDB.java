package fr.diginamic.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class ConnectDB {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static String fileName;
	private static Connection connection;

	
	static boolean connected(Boolean b){
		//TODO redo with checking if class is initialized and not by imposing
		if (b == false) {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.getStackTrace();
				System.out.println("Class Driver not found");
			}
			return true;
		}return false;
		
	}

	public static Connection connectTo(String fileName) {
		try {
			ResourceBundle myConfig = ResourceBundle.getBundle(fileName);
			Set<String> keys = myConfig.keySet();

			for (String key : keys) {
				if (key.contains("driver")) {
					driver = myConfig.getString(key);
					System.out.println(key + " = " + driver);
				} else if (key.contains("url")) {
					url = myConfig.getString(key);
					System.out.println(key + " = " + url);
				} else if (key.contains("user")) {
					user = myConfig.getString(key);
					System.out.println(key + " = " + user);
				} else if (key.contains("password")) {
					password = myConfig.getString(key);
					System.out.println(key + " = " + password);
				}
			}

			connection = DriverManager.getConnection(url, user, password);
			connected(true);
			System.out.println("\rConnected to the database " + connection.getCatalog());

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect");

		}
		return connection;
	}
}
