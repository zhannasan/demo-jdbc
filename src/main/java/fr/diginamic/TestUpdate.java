package fr.diginamic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.utils.ConnectDB;

public class TestUpdate {

	public static void main(String[] args) {
		FournisseurDaoJdbc dao = new FournisseurDaoJdbc();
		if(dao.exists("L'Esprit")==true){
			dao.updatePrep( "L'Esprit", "Maison de Peinture");
		}
		List<Fournisseur> fourn2 = dao.extraire();
		dao.close();

	}

}
