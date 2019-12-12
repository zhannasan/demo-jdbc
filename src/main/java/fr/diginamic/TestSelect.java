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

public class TestSelect {

	public static void main(String[] args) {
		FournisseurDaoJdbc dao = new FournisseurDaoJdbc();
		List<Fournisseur> fourn = dao.extraire();
		dao.close();

	}

}
