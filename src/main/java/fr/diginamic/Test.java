package fr.diginamic;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import fr.diginamic.entites.Fournisseur;

public class Test {

	public static void main(String[] args) {
		Fournisseur fournisseur = new Fournisseur(4,"Maison de la Peinture");
		System.out.println("L’Espace C"+"'"+"réation");
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
