package fr.diginamic;

import java.util.ResourceBundle;

public class TestConfiguration {

	public static void main(String[] args) {
		ResourceBundle myConfig = ResourceBundle.getBundle("demo-jdbc");
		String driverName = myConfig.getString("database.driver");
		System.out.println(driverName);
	}

}
