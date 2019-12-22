package fr.diginamic.entites;

public class Article {
	private int id;
	private String ref;
	private String designation;
	private double prix;
	private Fournisseur fournisseur;

	/**
	 * Constructeur d'article avec des paramètres suivants:
	 * 
	 * @param id
	 *            - le ID d'article (int)
	 * @param ref
	 *            - la référence d'article (String)
	 * @param designation
	 *            - la designation d'article (String)
	 * @param prix
	 *            - le prix d'article (double)
	 * @param fournisseur
	 *            - le fournisseur d'article(id comme la clé étrangère)
	 */
	public Article(int id, String ref, String designation, double prix, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.prix = prix;
		this.fournisseur = fournisseur;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref
	 *            the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return this.id + " " + this.ref + " " + this.designation + " " + this.prix + "\u20AC " + fournisseur.getId();
	}

	/**
	 * @return the fournisseur
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	/**
	 * @param fournisseur
	 *            the fournisseur to set
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

}
