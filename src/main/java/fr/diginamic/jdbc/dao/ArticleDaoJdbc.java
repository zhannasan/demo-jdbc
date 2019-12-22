package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import fr.diginamic.entites.Article;
import fr.diginamic.entites.Fournisseur;
import fr.diginamic.utils.DbManager;

public class ArticleDaoJdbc implements ArticleDao{
	static String fileName;

	private static Statement statement;
	private static Connection connection;
	private static ResultSet cursor;
	
	/**
	 * Constructeur sans parametres qui etabli une connnexion
	 */
	public ArticleDaoJdbc() {
		super();
		try {
				connection = DbManager.getConnection();
				statement = connection.createStatement();
				System.out.println("\rConnected to the database " + connection.getCatalog());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Article> extraire() {
		ArrayList<Article> article = new ArrayList<Article>();
		try {
			cursor = statement.executeQuery("SELECT * FROM article");
			connection.commit();
			while (cursor.next()) {
				int id = cursor.getInt("ID");
				String ref = cursor.getString("Ref");
				String designation = cursor.getString("Designation");
				double prix = cursor.getDouble("Prix");
				int fouId = cursor.getInt("ID_Fou");
				Article articles = new Article(id, ref, designation, prix, new Fournisseur(fouId, ""));
				article.add(articles);
			}
			for (Article a : article) {
				System.out.println(a.toString());
			}
			System.out.println("\r");
			cursor.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public void insert(Article article) {
		try {
			PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO article (ID, Ref, Designation, Prix, ID_Fou) values (?,?,?,?,?)");
			insertStatement.setInt(1, article.getId());
			insertStatement.setString (2, article.getRef());
			insertStatement.setString (3, article.getDesignation());
			insertStatement.setDouble (4, article.getPrix());
			insertStatement.setInt (5, article.getFournisseur().getId());
			insertStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
@Override
	public void updatePrix(double newPrix, String designation) {
		try {
			PreparedStatement updateStatement = connection.prepareStatement("UPDATE article SET prix=prix*? WHERE Designation LIKE ?");
			updateStatement.setDouble (1,newPrix);
			updateStatement.setString (2, designation);
			updateStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(String designation) {
		try {
			PreparedStatement updateStatement = connection.prepareStatement("DELETE FROM article WHERE Designation LIKE ?");
			updateStatement.setString (1, designation);
			updateStatement.executeUpdate();
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/** Vérifier si le Driver a déjà été enregistré
	 * @param driver driver 
	 * @return
	 */
	static boolean loaded(String driver){
	    boolean isLoaded = false;
	    Enumeration<Driver> loadedDrivers = DriverManager.getDrivers(); 
	    while(loadedDrivers.hasMoreElements()){     
	        Driver loadDriver = loadedDrivers.nextElement();
	        if(loadDriver.getClass().getName().equals(driver)){
	        	isLoaded = true;
	            break;
	        }
	        else{
	        	try {
					Class.forName(driver);
					break;
				} catch (ClassNotFoundException e) {
					e.getStackTrace();
					System.out.println("Class Driver not found");
					isLoaded=false;
				}
	        }
	    }
	    return isLoaded;
	}

	public double extraireAvgPrix() {
		double avPrix = 0;
		try {
			cursor = statement.executeQuery("SELECT AVG(prix) FROM article");
			connection.commit();
			while (cursor.next()) {
				avPrix = cursor.getDouble("AVG(prix)");
			}
			System.out.println("Le prix moyen de tout les articles est "+String.format("%.2f",avPrix)+"\u20AC");
			cursor.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avPrix;
	}
	
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
