package metier;

import java.sql.*;
/*
 * Classe permettant de faire des requetes sur la base de données

 */

public class DB 
{
	private static String chemin;
	private static String identifiant;
	private static String motDePasse;
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

	public void query(PreparedStatement ps){

		try{
			Map<
			ResultSet rs = ps.executeQuery();
			for ( int i = 0 ; i < rs.getMetaData().getColumnCount() ; i++ ){

			}
			rs.getMetaData().

		} catch ( Exception e) {}

	}
 
	public void update(){
		try
	}

	public void requete(String req)
	{
			try {
				Statement stmt = db.createStatement();
				ResultSet rs = null;

				if(req.contains("SELECT") || req.contains("select"))
				{
					return stmt.executeQuery(req);
					ResultSetMetaData rsmd = rs.getMetaData();
					while (rs.next()) 
					{
					}
				}
				else stmt.execute(req);
				rs.close();
				stmt.close();
			} catch (Exception e) {	}
	}
}