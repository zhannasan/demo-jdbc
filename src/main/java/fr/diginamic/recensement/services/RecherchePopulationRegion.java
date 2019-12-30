package fr.diginamic.recensement.services;

import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import fr.diginamic.recensement.dao.RecensementDaoJdbc;

public class RecherchePopulationRegion {
	public void traiter(RecensementDaoJdbc dao, Scanner scanner){
		System.out.println("Veuillez choisir une région.");
		String region = scanner.nextLine();
		if(StringUtils.isNumeric(region)){
			Map<String, Integer> populationDept = dao.recherchePopulation("region","population_totale", "code_region", region);
			for (Entry<String, Integer> entry : populationDept.entrySet()) {
				if(entry.getKey()!=null){
					System.out.println("La population de la région "+entry.getKey() + " est " +entry.getValue()+" habitants.");
				}
			}
		}else{
			Map<String, Integer> populationDept = dao.recherchePopulation("region","population_totale", "nom_region", region);
			if(populationDept!=null){
				for (Entry<String, Integer> entry : populationDept.entrySet()) {
					System.out.println("La population de la région "+entry.getKey() + " est " +entry.getValue()+" habitants.");
				}
			}
		}
	}
}
