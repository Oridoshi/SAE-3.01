package metier;

import java.sql.*;
/*
 * Classe permettant de faire des requetes sur la base de données

 */

public class DB 
{
	private static String chemin = "";
	private static String identifiant = "ba222202";
	private static String motDePasse = "Isabelle02121966!";
	private static Connection db;

	static{
		try{
			Class.forName("org.postgresql.JDBC");
			db = DriverManager.getConnection("jdbc:postgresql:"+ chemin, identifiant, motDePasse);
		} catch (Exception e){
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
			return -1;
		}
	}
}