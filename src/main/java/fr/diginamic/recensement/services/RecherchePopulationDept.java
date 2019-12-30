package fr.diginamic.recensement.services;

import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import fr.diginamic.recensement.dao.RecensementDaoJdbc;

public class RecherchePopulationDept {

	public void traiter(RecensementDaoJdbc dao, Scanner scanner){
		System.out.println("Veuillez choisir un departement.");
		String dept = scanner.nextLine();
		Map<String, Integer> populationDept = dao.recherchePopulation("departement","population_totale", "code_departement", dept);
		if(populationDept!=null){
		for (Entry<String, Integer> entry : populationDept.entrySet()) {
			System.out.println("La population du departement "+entry.getKey() + " est " +entry.getValue()+" habitants.");
		}
		}else{
		System.out.println("");}
	}
}
