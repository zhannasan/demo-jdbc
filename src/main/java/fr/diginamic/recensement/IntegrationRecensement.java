package fr.diginamic.recensement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.utils.DbManager;

public class IntegrationRecensement {
	private static final Logger LOG = LoggerFactory.getLogger(IntegrationRecensement.class);
	
	public static void main(String[] args) {
		Connection connection =null;
		Statement statement = null;
		int batchSize = 128;//Execution Time: 5438 ms
		
		try {
			ClassLoader classLoader = new IntegrationRecensement().getClass().getClassLoader();
			File file = new File(classLoader.getResource("recensement population 2016.csv").getFile());
			
			
			connection = DbManager.getConnection();
			connection.setAutoCommit(false);
			
			DatabaseMetaData md = connection.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			boolean[] exists = new boolean[3];
			while (rs.next()) {
				if (rs.getString(3).equalsIgnoreCase("ville")) {
					exists[0] = true;
				}
				if (rs.getString(3).equalsIgnoreCase("departement")) {
					exists[1] = true;
				}
				if (rs.getString(3).equalsIgnoreCase("region")) {
					exists[2] = true;
				}
				LOG.info(rs.getString(3));
			}
			rs.close();
			long start = System.currentTimeMillis();
			statement = connection.createStatement();
			if (exists[0] == false) {
				
				String createVille = "CREATE TABLE ville("
						+ "ID int(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,"
						+ "code_region int(10),"
						+ "nom_region varchar(50),"
						+ "code_departement varchar(50),"
						+ "nom_commune varchar(50),"
						+ "population_totale int(10));";
				statement.executeUpdate(createVille);
				LOG.info("Créé la table ville.");
				String alterVille= "ALTER TABLE ville ADD CONSTRAINT uc UNIQUE(code_departement, nom_commune);";
				statement.execute(alterVille);
			}
			LOG.info("Insertion des données dans la table ville");
			String insertString = "INSERT IGNORE INTO ville (code_region, nom_region, code_departement, nom_commune, population_totale) VALUES (?,?,?,?,?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertString); 
			
			Reader fr = new FileReader(URLDecoder.decode(file.toString(), "UTF-8")); 
			BufferedReader lineReader = new BufferedReader(fr);
            String line = null;
            
            lineReader.readLine(); 
			
			int i = 0;
			while ((line = lineReader.readLine()) != null) {
					String[] columns = line.split(";");
					String codeRegion = columns[0];
					String nomRegion = columns[1];
					String codeDept = columns[2];
					String nomCommune = columns[3];
					String population = columns[4];
					String pop = population.trim().replaceAll(" ", "");
				
				insertStatement.setInt(1, Integer.parseInt(codeRegion));
				insertStatement.setString(2, nomRegion);
				insertStatement.setString(3, codeDept);
				insertStatement.setString(4, nomCommune);
				insertStatement.setInt(5, Integer.parseInt(pop));
				insertStatement.addBatch();
				
				if(i% batchSize==0){
					insertStatement.executeBatch();
				}
			}
			insertStatement.executeBatch();
			connection.commit();

			lineReader.close();
			fr.close();
			long end = System.currentTimeMillis();
			LOG.info("Temps de création table ville : " + (end - start) + "ms");
			
			//-----fusion Lyon arrondissements-----
			String insertUpdate = "INSERT IGNORE INTO ville (SELECT SUM(id), code_region, nom_region, code_departement, 'Lyon', SUM(population_totale) FROM ville WHERE nom_commune LIKE '%Lyon%arrond%')";
			statement.executeUpdate(insertUpdate);
			LOG.info(insertUpdate);
			String deleteUpdate = "DELETE FROM ville WHERE nom_commune LIKE '%lyon%arrondiss%'";
			statement.executeUpdate(deleteUpdate);
			LOG.info(deleteUpdate);
			// ----- table departement------
			long startD = System.currentTimeMillis();
			if (exists[1] == false) {
				String createDept = "CREATE TABLE departement("
						+ "code_departement varchar(50),"
						+ "population_totale int(10));";
				statement.executeUpdate(createDept);
				LOG.info("Créé la table departement.");
				String alterDept = "ALTER TABLE departement ADD CONSTRAINT ud UNIQUE(code_departement);";
				statement.execute(alterDept);
			}
			String insertDept = "INSERT IGNORE INTO departement SELECT code_departement, SUM(population_totale) FROM ville GROUP BY code_departement ASC";
			PreparedStatement insertStatementDept = connection.prepareStatement(insertDept);
			insertStatementDept.executeUpdate();
			connection.commit();
			long endD = System.currentTimeMillis();
			LOG.info("Temps de création table departement : " + (endD - startD) + "ms");
	
			//----- table region------
			long startR = System.currentTimeMillis();
			if (exists[1] == false) {
				String createRegion = "CREATE TABLE region("
						+ "code_region int(10),"
						+ "nom_region varchar(50),"
						+ "population_totale int(10));";
				statement.executeUpdate(createRegion);
				LOG.info("Créé la table region.");
				String alterDept = "ALTER TABLE region ADD CONSTRAINT ur UNIQUE(code_region);";
				statement.execute(alterDept);
			}
			String insertRegion = "INSERT IGNORE INTO region SELECT code_region,nom_region, SUM(population_totale) FROM ville GROUP BY code_region";
			PreparedStatement insertStatementRegion = connection.prepareStatement(insertRegion);
			insertStatementRegion.executeUpdate();
			connection.commit();
			long endR = System.currentTimeMillis();
			LOG.info("Temps de création table region : " + (endR - startR) + "ms");

			connection.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			try {
				connection.rollback();
			} catch (SQLException ex) {
				LOG.error(ex.getMessage(), ex);
			}
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (connection != null || statement != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

}
