package fr.diginamic.recensement.entites;

/**
 * Représents un departement avec le code de departement unique et population totale
 *  * @author janka
 */
public class Departement {
	/**code d'un departement */
	private String codeDept;
	/** population d'un departement */
	private int populationTotale;
	
	/**
	 * Un constructeur avec deux paramètres
	 * @param codeDept code d'un departement
	 * @param populationTotale population d'un departement
	 */
	public Departement(String codeDept, int populationTotale) {
		super();
		this.codeDept = codeDept;
		this.populationTotale = populationTotale;
	}
	
	public String toString(){
		return codeDept+" "+populationTotale;
	}
	
	/**
	 * @return the codeDept
	 */
	public String getCodeDept() {
		return codeDept;
	}
	/**
	 * @param codeDept the codeDept to set
	 */
	public void setCodeDept(String codeDept) {
		this.codeDept = codeDept;
	}
	/**
	 * @return the populationTotale
	 */
	public int getPopulationTotale() {
		return populationTotale;
	}
	/**
	 * @param populationTotale the populationTotale to set
	 */
	public void setPopulationTotale(int populationTotale) {
		this.populationTotale = populationTotale;
	}
}
