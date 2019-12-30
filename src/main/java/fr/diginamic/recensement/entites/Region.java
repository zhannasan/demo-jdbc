package fr.diginamic.recensement.entites;

/**
 * Représent un région avec son code, nom et population
 * @author janka
 */
public class Region {
	/** code d'un région */
	private String codeRegion;
	/** nom d'un région */
	private String nomRegion;
	/** population totale d'un région */
	private int populationTotale;

	/**
	 * Constructeur du région avec ses trois paramètres
	 * @param codeRegion - code d'un région
	 * @param nomRegion - nom d'un région
	 * @param populationTotale - population totale d'un région
	 */
	public Region(String codeRegion, String nomRegion, int populationTotale) {
		super();
		this.codeRegion = codeRegion;
		this.nomRegion = nomRegion;
		this.populationTotale = populationTotale;
	}
	
	/**
	 * @return the codeRegion
	 */
	public String getCodeRegion() {
		return codeRegion;
	}

	/**
	 * @param codeRegion
	 *            the codeRegion to set
	 */
	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}

	/**
	 * @return the nomRegion
	 */
	public String getNomRegion() {
		return nomRegion;
	}

	/**
	 * @param nomRegion
	 *            the nomRegion to set
	 */
	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

	/**
	 * @return the populationTotale
	 */
	public int getPopulationTotale() {
		return populationTotale;
	}

	/**
	 * @param populationTotale
	 *            the populationTotale to set
	 */
	public void setPopulationTotale(int populationTotale) {
		this.populationTotale = populationTotale;
	}

}
