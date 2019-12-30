package fr.diginamic.recensement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.recensement.IntegrationRecensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.utils.DbManager;

public class RecensementDaoJdbc implements RecensementDao {
	private static final Logger LOG = LoggerFactory.getLogger(IntegrationRecensement.class);
	private static Statement statement;
	private static Connection connection;
	private static ResultSet cursor;

	/**
	 * 
	 */
	public RecensementDaoJdbc() {
		try {
			connection = DbManager.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOG.error(e1.getMessage(), e1);
			}
		} 
	}

	@Override
	public HashMap<String, Integer> afficher(String searchParam, String searchTable) {
		HashMap<String, Integer> extractPop = new HashMap<>();
		try {
			String sql = "SELECT population_totale, "+ searchParam +" FROM "+searchTable+" ORDER BY population_totale DESC LIMIT 10;";
			cursor = statement.executeQuery(sql);
			while (cursor.next()) {
				String searchResult = cursor.getString(searchParam);
				int population = cursor.getInt("population_totale");
				extractPop.put(searchResult, population);
			}
			cursor.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOG.error(e1.getMessage(), e1);
			}
		}
		return extractPop;
	}
	
	//SELECT code_departement, population_totale, nom_commune FROM ville where code_departement like '%34%'  
	public HashMap<String, Integer> afficherVille(String rechercheParam, String input) {
		HashMap<String, Integer> population = new HashMap<>();
		String select = "SELECT population_totale, nom_commune FROM ville WHERE lower(" + rechercheParam
				+ ") LIKE ? ORDER BY population_totale DESC LIMIT 10";
		try {
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, "%" + input.toLowerCase() + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					int pop = rs.getInt("population_totale");
					String nomInput = rs.getString("nom_commune");
					population.put(nomInput, pop );
				} while (rs.next());
			
			} else {
				System.out.println("Saisie inconnue. Veuillez re-essayer.");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOG.error(e1.getMessage(), e1);
			}
		}return population;
	}

	@Override
	public void insert(Ville ville) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public Map<String, Integer> recherchePopulation(String geoEntity, String recherchePop, String rechercheParam, String input) {
		Map<String, Integer> population = new HashMap<>();
		String select = "SELECT " + recherchePop + ", " + rechercheParam + " FROM "+geoEntity+" WHERE lower(" + rechercheParam
				+ ") LIKE ?";
		try {
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, "%" + input.toLowerCase() + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					int pop = rs.getInt(recherchePop);
					String nomInput = rs.getString(rechercheParam);
					population.put(nomInput, pop);
				} while (rs.next());
			} else {
				System.out.println("Saisie inconnue. Veuillez re-essayer.");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOG.error(e1.getMessage(), e1);
			}
		}return population;
	}

	public void close() {
		try {
			if (cursor != null) {
				cursor.close();
			} else if (statement != null) {
				statement.close();
			} else if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
