package fr.diginamic.recensement.services;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.diginamic.recensement.dao.RecensementDaoJdbc;
import fr.diginamic.recensement.utils.ValueComparator;

public class AffichageVillesPlusPeupleesDept {
	public void traiter(RecensementDaoJdbc dao, Scanner scanner) {
		System.out.println("Veuillez choisir un departement.");
		String dept = scanner.nextLine();

		HashMap<String, Integer> populationDept = dao.afficherVille("code_departement", dept);
		Comparator<String> comparator = new ValueComparator<String, Integer>(populationDept);
		TreeMap<String, Integer> populationDeptSorted = new TreeMap<>(comparator);
		populationDeptSorted.putAll(populationDept);

		if (populationDept != null) {
			System.out.println("Les 10 villes les plus peuplées du département " + dept);
			for (Entry<String, Integer> entry : populationDept.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue() + " d'habitants.");
			}
		}
	}
}
