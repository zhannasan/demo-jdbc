package fr.diginamic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.utils.ConnectDB;

public class TestUpdate {

	public static void main(String[] args) {
		try {
			Connection connection = ConnectDB.connectTo("demo-jdbc");
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE fournisseur SET Nom = 'Maison des Peintures' WHERE ID=4 ");
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
