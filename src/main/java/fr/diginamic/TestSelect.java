package fr.diginamic;

import java.util.List;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;


public class TestSelect {

	public static void main(String[] args) {
		FournisseurDaoJdbc dao = new FournisseurDaoJdbc();
		List<Fournisseur> fourn = dao.extraire();
		dao.close();
	}

}
