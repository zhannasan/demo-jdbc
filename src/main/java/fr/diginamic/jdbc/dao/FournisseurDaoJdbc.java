package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.utils.DbManager;

public class FournisseurDaoJdbc implements FournisseurDao {
	private static Statement statement;
	private static Connection connection;
	private static ResultSet cursor;

	/**établissement d'une connexion et création statement
	* 
	*/
	public FournisseurDaoJdbc() {
		try {
			connection = DbManager.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.diginamic.jdbc.dao.FournisseurDao#extraire()
	 */
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
			System.out.println("\r");
			cursor.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fournisseurs;
	}

	/* (non-Javadoc)
	 * @see fr.diginamic.jdbc.dao.FournisseurDao#insert(fr.diginamic.entites.Fournisseur)
	 */
	public void insert(Fournisseur fournisseur) {
		try {
			statement.executeUpdate("INSERT INTO fournisseur (ID, Nom) values (" + fournisseur.getId() + ", '"
					+ fournisseur.getNom() + "')");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see fr.diginamic.jdbc.dao.FournisseurDao#update(java.lang.String, java.lang.String)
	 */
	public int update(String ancienNom, String nouveauNom) {
		try {
			statement.executeUpdate("UPDATE fournisseur SET Nom = '" + nouveauNom + "' WHERE Nom='" + ancienNom + "'");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.diginamic.jdbc.dao.FournisseurDao#delete(fr.diginamic.entites.Fournisseur)
	 */
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

	
	/**
	 * @param nomFournisseur
	 * @return
	 */
	public boolean exists(String nomFournisseur) {
		boolean exist = false;
		try {
			cursor = statement.executeQuery("SELECT Nom FROM fournisseur");
			while (cursor.next()) {
				String nom = cursor.getString("Nom");
				if (nom.equals(nomFournisseur)) {
					exist = true;
					break;
				}
			}
			cursor.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	/**Fermer cursor, statement et connexion apres utilisation 
	 * 
	 */
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

	/** UPDATE avec PreparedStatement
	 * @param id ID de fournisseur à 
	 * @param nom Nom de fournisseur à inserer
	 */
	public void insert(int id, String nom) {
		try {
			PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO fournisseur (ID, Nom) values (?, ?)");
			insertStatement.setInt(1, id);
			insertStatement.setString (2, nom);
			insertStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** DELETE avec PreparedStatement
	 * @param nom Nom de fournisseur à effacer
	 * @return boolean true si effacé
	 */
	public boolean delete(String nom) {
		boolean deleted = false;
		try {
			PreparedStatement insertStatement = connection.prepareStatement("DELETE FROM fournisseur WHERE Nom LIKE ?");
			insertStatement.setString (1, nom);
			insertStatement.executeUpdate();
			connection.commit();
			deleted=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	/** UPDATE  avec PreparedStatement
	 * @param ancienNom ancien nom de fournisseur
	 * @param nouveauNom nouveau nom de fournisseur
	 */
	public void updatePrep(String ancienNom, String nouveauNom) {
		try {
			PreparedStatement insertStatement = connection.prepareStatement("UPDATE fournisseur SET Nom =? WHERE Nom=?");
			insertStatement.setString (1, nouveauNom);
			insertStatement.setString (2, ancienNom);
			insertStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int findIdFournisseur(String nom){
		int idFournisseur=0;
		try {
			cursor = statement.executeQuery("SELECT id FROM fournisseur WHERE Nom = '"+nom+"'");
			while (cursor.next()) {
				idFournisseur = cursor.getInt("ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idFournisseur;
	}
}
