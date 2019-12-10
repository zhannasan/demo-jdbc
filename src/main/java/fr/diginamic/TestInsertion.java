package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.utils.ConnectDB;

public class TestInsertion {

	public static void main(String[] args) {
		try{
			Connection connection = ConnectDB.connectTo("demo-jdbc");
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO fournisseur (ID, Nom) values (4, 'Maison de la Peinture')");
			connection.commit();
			
			ResultSet cursor = statement.executeQuery("SELECT ID, Nom FROM fournisseur");
			ArrayList<Fournisseur> fourn = new ArrayList<Fournisseur>();
			
			while(cursor.next()){
				int id = cursor.getInt("ID");
				String nom = cursor.getString("Nom");
				Fournisseur fournisseur = new Fournisseur(id, nom);
				fourn.add(fournisseur);
			}
			for(Fournisseur f: fourn){
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
