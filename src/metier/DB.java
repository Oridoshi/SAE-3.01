package metier;

import java.sql.*;

import org.json.JSONObject;

import controleur.Controleur;

/**
 * Classe permettant de faire des requetes sur la base de données

 */
public class DB 
{
	// private static String chemin = "woody/dt220522";
	// private static String identifiant = "dt220522";
	// private static String motDePasse = "22555225Tt.";
	private static Connection db;


	static {
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			JSONObject fichierParamConnexion = Controleur.getJson();

			db = DriverManager.getConnection("jdbc:postgresql://" + fichierParamConnexion.getString("chemin"), fichierParamConnexion.getString("identifiant"), fichierParamConnexion.getString("motDePasse"));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance(){
		return db;
	}

	public static DBResult query(PreparedStatement ps){
		try{
			return new DBResult(ps.executeQuery());
		} catch ( SQLException e ){
			return null;
		}
	}

	public static int update(PreparedStatement ps){
		try{
			return ps.executeUpdate();
		} catch ( SQLException e ){
			// System.out.println("DEBUG : " + e);
			return -1;
		}
	}

	public static void fermerConnexion()
	{
		try{
			if(!DB.db.isClosed())
				DB.db.close();
		} catch (Exception e) {
			System.out.println("DEBUG : IL Y A EUX UN PROBLEME LORS DE LA DECONNECTION");
		}
	}
}