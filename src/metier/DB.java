package metier;

import java.sql.*;
/*
 * Classe permettant de faire des requetes sur la base de données

 */

public class DB 
{
	private static String chemin = "bernouy.com/sae301";
	private static String identifiant = "postgres";
	private static String motDePasse = "mon_mot_de_passe";
	private static Connection db;


	static {
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			db = DriverManager.getConnection("jdbc:postgresql://" + chemin,identifiant,motDePasse);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	static{
		try{
			db = DriverManager.getConnection("jdbc:postgresql://"+ chemin + "/" + identifiant, identifiant, motDePasse);
		} catch (Exception e){
			e.printStackTrace();
		}
	}*/

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
			return -1;
		}
	}
}