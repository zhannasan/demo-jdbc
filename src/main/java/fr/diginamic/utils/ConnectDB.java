package fr.diginamic.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Set;

public class ConnectDB {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static String fileName;
	private static Connection connection;

	
	static boolean loaded(String driver){
		    boolean isLoaded = false;
		    Enumeration<Driver> loadedDrivers = DriverManager.getDrivers(); 
		    while(loadedDrivers.hasMoreElements()){     
		        Driver loadDriver = loadedDrivers.nextElement();
		        if(loadDriver.getClass().getName().equals(driver)){
		        	isLoaded = true;
		            break;
		        }
		        else{
		        	try {
						Class.forName(driver);
						break;
					} catch (ClassNotFoundException e) {
						e.getStackTrace();
						System.out.println("Class Driver not found");
						isLoaded=false;
					}
		        }
		    }
		    return isLoaded;
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
			System.out.println("Driver loaded: "+loaded(driver));
			connection = DriverManager.getConnection(url, user, password);
			
			System.out.println("\rConnected to the database " + connection.getCatalog());

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect");

		}
		return connection;
	}
}
