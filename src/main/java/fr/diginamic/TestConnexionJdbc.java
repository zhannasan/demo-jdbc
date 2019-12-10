package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class TestConnexionJdbc {

	public static void main(String[] args) {

		/*
		 * try { DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
		 * Connection connection=
		 * DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta",
		 * "root", "");
		 * 
		 * System.out.println(connection.getCatalog()); connection.close(); }
		 * catch (SQLException e) { throw new
		 * RuntimeException("Could not register driver", e); }
		 */

		try {
			ResourceBundle myConfig = ResourceBundle.getBundle("demo-jdbc");
			Set<String> keys = myConfig.keySet();
			String driver = "";
			String url = "";
			String user = "";
			String password = "";
			for (String key : keys) {
				if (key.contains("driver")) {
					driver = myConfig.getString(key);
					System.out.println(key+" = "+driver);
				} else if (key.contains("url")) {
					url = myConfig.getString(key);
					System.out.println(key+" = "+url);
				} else if (key.contains("user")) {
					user = myConfig.getString(key);
					System.out.println(key+" = "+user);
				} else if (key.contains("password")) {
					password = myConfig.getString(key);
					System.out.println(key+" = "+password);
				}
			}

			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("\r"+connection.getCatalog());
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.getStackTrace();
		}

	}

}
