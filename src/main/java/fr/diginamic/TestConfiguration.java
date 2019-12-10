package fr.diginamic;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class TestConfiguration {

	public static void main(String[] args) {
		ResourceBundle myConfig = ResourceBundle.getBundle("demo-jdbc");
			
		Enumeration<String> keys = myConfig.getKeys();
		
		while (keys.hasMoreElements()) {
			String key=keys.nextElement();
	         System.out.println(key+" "+myConfig.getString(key));
	      }
		
	}

}
