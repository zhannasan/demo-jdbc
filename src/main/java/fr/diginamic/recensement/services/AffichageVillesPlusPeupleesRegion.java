package fr.diginamic.recensement.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import fr.diginamic.recensement.dao.RecensementDaoJdbc;
import fr.diginamic.recensement.utils.ValueComparator;

public class AffichageVillesPlusPeupleesRegion {
	public void traiter(RecensementDaoJdbc dao, Scanner scanner) {
		System.out.println("Veuillez choisir une région.");
		String region = scanner.nextLine();
		if (StringUtils.isNumeric(region)) {
			HashMap<String, Integer> populationRegion = dao.afficherVille("code_region", region);
			Comparator<String> comparator = new ValueComparator<String, Integer>(populationRegion);
			TreeMap<String, Integer> populationRegionSorted = new TreeMap<>(comparator);
			populationRegionSorted.putAll(populationRegion);

			if (populationRegionSorted != null) {
				System.out.println("Les 10 villes les plus peuplées de la région " + region);
				for (Entry<String, Integer> entry : populationRegionSorted.entrySet()) {
					System.out.println(entry.getKey() + " - " + entry.getValue() + " d'habitants.");
				}
			}
		} else {
			HashMap<String, Integer> populationRegion = dao.afficherVille("nom_region", region);
			Comparator<String> comparator = new ValueComparator<String, Integer>(populationRegion);
			TreeMap<String, Integer> populationRegionSorted = new TreeMap<>(comparator);
			populationRegionSorted.putAll(populationRegion);

			if (populationRegionSorted != null) {
				System.out.println("Les 10 villes les plus peuplées de la région " + region);
				for (Entry<String, Integer> entry : populationRegionSorted.entrySet()) {
					System.out.println(entry.getKey() + " - " + entry.getValue() + " d'habitants.");
				}
			}
		}
	}

}
