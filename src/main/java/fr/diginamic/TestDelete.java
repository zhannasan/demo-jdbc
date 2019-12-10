package fr.diginamic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.utils.ConnectDB;

public class TestDelete {

	public static void main(String[] args) {
		try {
			Connection connection = ConnectDB.connectTo("demo-jdbc");
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM fournisseur WHERE Nom = 'Maison des Peintures'");
			connection.commit();

			ResultSet cursor = statement.executeQuery("SELECT ID, Nom FROM fournisseur");
			ArrayList<Fournisseur> fourn = new ArrayList<Fournisseur>();

			while (cursor.next()) {
				int id = cursor.getInt("ID");
				String nom = cursor.getString("Nom");
				Fournisseur fournisseur = new Fournisseur(id, nom);
				fourn.add(fournisseur);
			}
			for (Fournisseur f : fourn) {
				System.out.println(f.toString());
			}

			cursor.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}


	}

}
