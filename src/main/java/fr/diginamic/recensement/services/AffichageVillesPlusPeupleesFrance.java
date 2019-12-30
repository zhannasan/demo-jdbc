package fr.diginamic.recensement.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import fr.diginamic.recensement.dao.RecensementDaoJdbc;
import fr.diginamic.recensement.utils.ValueComparator;

public class AffichageVillesPlusPeupleesFrance {

	public void traiter(RecensementDaoJdbc dao, Scanner scanner){
		HashMap<String, Integer> populationRegion = dao.afficher("nom_commune", "ville");
		Comparator<String> comparator = new ValueComparator<String, Integer>(populationRegion);
		TreeMap<String, Integer> populationSorted = new TreeMap<>(comparator);
		populationSorted.putAll(populationRegion);
		
		System.out.println("Les 10 villes les plus peuplées en France:");
		for (Entry<String, Integer> entry : populationSorted.entrySet()) {
			if(entry.getKey()!=null){
				System.out.println(entry.getKey() + " - " +entry.getValue()+" d'habitants.");
			}
		}
	}
}
