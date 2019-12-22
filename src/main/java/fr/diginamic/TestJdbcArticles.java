package fr.diginamic;

import java.util.List;

import fr.diginamic.entites.Article;
import fr.diginamic.entites.Fournisseur;
import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

public class TestJdbcArticles {

	public static void main(String[] args) {
		ArticleDaoJdbc dao = new ArticleDaoJdbc();
		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		
		List<Article> articles = dao.extraire();
		List<Fournisseur> fournisseurs = daoF.extraire();
		Fournisseur fournisseur = null;
		if (daoF.exists("La Maison de la Peinture") == true) {
			fournisseur = new Fournisseur(daoF.findIdFournisseur("La Maison de la Peinture"), "La Maison de la Peinture");
		} else {
			daoF.insert(fournisseurs.size() + 1, "La Maison de la Peinture");
			List<Fournisseur> fournAfterAdded = daoF.extraire();
			fournisseur = fournAfterAdded.get(fournAfterAdded.size()-1);
		}
		
		dao.insert(new Article(articles.size()+1, "M01", "Peinture blanche 1L", 12.5, fournisseur));
		dao.insert(new Article(articles.size()+2, "M02", "Peinture rouge mate 1L", 15.5, fournisseur));
		dao.insert(new Article(articles.size()+3, "M03", "Peinture noire laquée 1L", 17.8, fournisseur));
		dao.insert(new Article(articles.size()+4, "M04", "Peinture bleu mate 1L", 15.5, fournisseur));
		
		dao.updatePrix(0.75,"%mate%");
		
		List<Article> articlesAdded = dao.extraire();
		dao.extraireAvgPrix();
		dao.delete("%peinture%");
		daoF.delete("%Maison%Peinture%");
		
		List<Article> articlesAfterDelete = dao.extraire();
		List<Fournisseur> fournAfterDelete = daoF.extraire();
		dao.close();
		
	}

}
