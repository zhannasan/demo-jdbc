package fr.diginamic.recensement.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import fr.diginamic.recensement.dao.RecensementDaoJdbc;
import fr.diginamic.recensement.utils.ValueComparator;

public class AffichageRegionsPlusPeuplees {

	public void traiter(RecensementDaoJdbc dao, Scanner scanner){
		HashMap<String, Integer> populationRegion = dao.afficher("nom_region", "region");
		Comparator<String> comparator = new ValueComparator<String, Integer>(populationRegion);
		TreeMap<String, Integer> populationRegionSorted = new TreeMap<>(comparator);
		populationRegionSorted.putAll(populationRegion);
		
		for (Entry<String, Integer> entry : populationRegionSorted.entrySet()) {
			if(entry.getKey()!=null){
				System.out.println("La population de la région "+entry.getKey() + " est " +entry.getValue()+" habitants.");
			}	
		}
	}

}
