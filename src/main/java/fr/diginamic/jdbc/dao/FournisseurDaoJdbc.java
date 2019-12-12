package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.utils.ConnectDB;

public class FournisseurDaoJdbc implements FournisseurDao {
	private static Statement statement;
	private static Connection connection;
	private static ResultSet cursor;
	
	/**
	* 
	*/
	public FournisseurDaoJdbc() {
		try {
			connection = ConnectDB.connectTo("demo-jdbc");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Fournisseur> extraire() {
		ArrayList<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
		try {
			cursor = statement.executeQuery("SELECT ID, Nom FROM fournisseur");
			while (cursor.next()) {
				int id = cursor.getInt("ID");
				String nom = cursor.getString("Nom");
				Fournisseur fournisseur = new Fournisseur(id, nom);
				fournisseurs.add(fournisseur);
			}
			for (Fournisseur f : fournisseurs) {
				System.out.println(f.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fournisseurs;
	}

	public void insert(Fournisseur fournisseur) {
		try {
			System.out.println("INSERT INTO fournisseur (ID, Nom) values (" + fournisseur.getId() + ", '"
					+ fournisseur.getNom() + "')");
			statement.executeUpdate("INSERT INTO fournisseur (ID, Nom) values (" + fournisseur.getId() + ", '"
					+ fournisseur.getNom() + "')");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int update(String ancienNom, String nouveauNom) {
		try {
			statement.executeUpdate("UPDATE fournisseur SET Nom = '" + nouveauNom + "' WHERE Nom='" + ancienNom + "'");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean delete(Fournisseur fournisseur) {
		try {
			statement.executeUpdate("DELETE FROM fournisseur WHERE Nom = '" + fournisseur.getNom() + "'");
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return false;
	}
	
	/*public boolean exists(String nomFournisseur){
		boolean exist = false;
		try {
			cursor = statement.executeQuery("SELECT ID, Nom FROM fournisseur");
			while (cursor.next()) {
				int id = cursor.getInt("ID");
				String nom = cursor.getString("Nom");
				if(nom.equalsIgnoreCase(nomFournisseur)){
					exist=true;
				}else{
					exist=false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}*/

	public static void close() {
		try {
			if (cursor != null) {
				cursor.close();
			} else if (statement != null) {
				statement.close();
			} else if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
