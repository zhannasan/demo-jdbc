package fr.diginamic.recensement.dao;

import java.util.Map;

import fr.diginamic.recensement.entites.Ville;

public interface RecensementDao {
	
	Map<String, Integer> afficher(String searchParam, String searchTable);
	void insert(Ville ville);
	
	void update();
}
