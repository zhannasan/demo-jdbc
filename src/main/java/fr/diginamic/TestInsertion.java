package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.utils.ConnectDB;

public class TestInsertion {

	public static void main(String[] args) {
			FournisseurDaoJdbc dao = new FournisseurDaoJdbc();
			if(dao.exists("Maison de la peinture")==false){
				dao.insert(4, "Maison de la peinture");}
			List<Fournisseur> four = dao.extraire();
			dao.close();
	}

}
