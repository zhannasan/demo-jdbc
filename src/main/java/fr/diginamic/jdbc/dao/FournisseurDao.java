package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.entites.Fournisseur;

public interface FournisseurDao {
	/**
	 * @return recupérer une liste des fournisseurs 
	 * à partir d'une table de la base de données distante
	 */
	List<Fournisseur> extraire();
	/**INSERT fournisseur dans la table
	 * @param fournisseur - fournisseur
	 */
	void insert(Fournisseur fournisseur);
	/**UPDATE le nom d'un fournisseur dans une table
	 * @param ancienNom
	 * @param nouveauNom
	 * @return
	 */
	int update(String ancienNom , String nouveauNom);
	/**DELETE un fournisseur de la table
	 * @param fournisseur
	 * @return
	 */
	boolean delete(Fournisseur fournisseur);
}
