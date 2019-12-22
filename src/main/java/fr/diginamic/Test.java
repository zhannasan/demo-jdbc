package fr.diginamic;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.List;

import fr.diginamic.entites.Article;
import fr.diginamic.entites.Fournisseur;
import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

public class Test {

	public static void main(String[] args) {
		
		
		//System.out.println(dao.exists("FDM SA"));

		/*Enumeration<Driver> loadedDrivers = DriverManager.getDrivers();
	    
	    while(loadedDrivers.hasMoreElements()){     
	    	try {
                Driver driver = loadedDrivers.nextElement();
                System.out.println(driver.getClass().getCanonicalName());
                DriverManager.deregisterDriver(driver);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
	    }*/

	}

}
