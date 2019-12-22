package fr.diginamic.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbManager {
	private static ResourceBundle bundle = ResourceBundle.getBundle("demo-jdbc"); 
	private static DbManager instance = new DbManager(); 
	private ComboPooledDataSource cpds;

	/**
	 * Constructeur privé appelé une seule fois lors de la création du singleton
	 * et qui initialise le pool de connexions.
	 */
	private DbManager() {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(bundle.getString("database.driver"));
			cpds.setJdbcUrl(bundle.getString("database.url"));
			cpds.setUser(bundle.getString("database.user"));
			cpds.setPassword(bundle.getString("database.password"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException("Impossible de se connecter à la base de données.");
		}
	}

	/**
	 * Récupère une connexion
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			return instance.cpds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de récupérer une nouvelle connexion sur la base de données.");
		}
	}

	public static void executeUpdate(String sql) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				System.out.println("Rollback en échec.");
			}
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				conn.commit();
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("Fermeture des ressources en échec.");
			}
		}
	}
}
