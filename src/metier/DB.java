package metier;

import java.sql.*;


public class DB 
{
	private String chemin;
	private String identifiant;
	private String motDePasse;


	public DB(String chemin, String id, String mdp)
	{
		this.chemin = chemin;
		this.identifiant = id;
		this.motDePasse = mdp;
	}

	public void requete(String req)
	{
			try {
				Class.forName("org.postgresql.JDBC");
				Connection con = DriverManager.getConnection("jdbc:postgresql:"+this.chemin, this.identifiant, this.motDePasse);
				Statement stmt = con.createStatement();
				ResultSet rs = null;
				String lbl = "";

				if(req.contains("SELECT") || req.contains("select"))
				{
					rs = stmt.executeQuery(req);
					ResultSetMetaData rsmd = rs.getMetaData();
					while (rs.next()) 
					{
						for(int i = 0; i < rsmd.getColumnCount(); i++)
						{
							lbl += rs.getString(i+1);
						}
						lbl += "\n";
					}
				}
				else
				{
					stmt.execute(req);
					lbl = "Requete effectuée";
				}
				System.out.println(lbl);
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {	}
	}
}