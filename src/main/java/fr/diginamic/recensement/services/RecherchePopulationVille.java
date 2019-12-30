package fr.diginamic.recensement.services;

import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import fr.diginamic.recensement.dao.RecensementDaoJdbc;

public class RecherchePopulationVille {

	public void traiter(RecensementDaoJdbc dao, Scanner scanner){
		System.out.println("Veuillez choisir une ville.");
		String commune = scanner.nextLine();
		Map<String, Integer> populationVille = dao.recherchePopulation("ville","population_totale", "nom_commune", commune);
		for (Entry<String, Integer> entry : populationVille.entrySet()) {
			System.out.println("La population de "+entry.getKey() + " est " +entry.getValue());
		}
		System.out.println();
	}
}
