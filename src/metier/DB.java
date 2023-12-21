package metier;

import java.sql.*;


import org.json.JSONObject;

import controleur.Controleur;

/**
 * Classe permettant de faire des requetes sur la base de données ANAS
 */
public class DB 
{
	private static Connection db;

	private static PreparedStatement psTableExiste;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		DB.connexion();
	}

	public static Connection getInstance(){
		if(db == null)
			DB.connexion();

		return db;
	}

	private static void connexion()
	{
		try {
			JSONObject fichierParamConnexion = Controleur.getJson();

			db = DriverManager.getConnection("jdbc:postgresql://" + fichierParamConnexion.getString("chemin"), fichierParamConnexion.getString("identifiant"), fichierParamConnexion.getString("motDePasse"));
			DB.psTableExiste = db.prepareStatement("SELECT table_name FROM information_schema.tables WHERE table_schema NOT IN ('pg_catalog', 'information_schema') AND table_name = ?;");
		}
		catch (Exception e) {
			System.out.println("DEBUG : " + e);
		}
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
		try
		{
			if(DB.db != null)
			{
				if(!DB.db.isClosed())
					DB.db.close();

				DB.db = null;
			}
		}
		catch (Exception e)
		{
			System.out.println("DEBUG : IL Y A EUX UN PROBLEME LORS DE LA DECONNECTION");
		}
	}

	public static boolean tableExiste(String nomTable){
		try
		{
			DB.psTableExiste.setString(1, nomTable);
			return DB.query(DB.psTableExiste).getLignes().size() > 0;
		}
		catch ( SQLException e )
		{
			System.out.println("DEBUG : " + e);
			return false;
		}
	}
}