package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.entites.Article;

public interface ArticleDao {
	/**
	 * @return recupérer une liste des articles 
	 * à partir d'une table de la base de données distante
	 */
	List<Article> extraire();
	
	/**
	 * preparedStatement INSERT insérer un article dans la table 'article'
	 * @param article - article avec un constructeur article(int id, 
	 * String référence, String designation, double prix, int id fournisseur)
	 */
	void insert(Article article);
	
	/**
	 * preparedStatement UPDATE mettre à jour le prix d'article avec une designation dans la table
	 * @param prix le prix d'article (double)
	 * @param designation - designation entière ou partielle d'article (String)
	 * @return
	 */
	void updatePrix(double prix, String designation);
	
	/**
	 * preparedStatement DELETE supprimer un article avec une designation de la table
	 * @param designation - designation entière ou partielle de l'article (String) à supprimer
	 * @return
	 */
	boolean delete(String designation);

	
}
