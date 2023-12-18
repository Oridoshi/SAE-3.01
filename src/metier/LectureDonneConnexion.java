package metier;
import java.util.Scanner;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class LectureDonneConnexion
{
	private String chemin;
	private String identifiant;
	private String motDePasse;

	public LectureDonneConnexion()
	{
		try
		{
			JSONObject fichierParamConnexion = new JSONObject(lireFichier("/ParametreConnexion.json"));

			this.chemin = fichierParamConnexion.getString("chemin");
			this.identifiant = fichierParamConnexion.getString("identifiant");
			this.motDePasse = fichierParamConnexion.getString("motDePasse");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			this.chemin = "ERREUR";
			this.identifiant = "ERREUR";
			this.motDePasse = "ERREUR";
		}
	}

	private String lireFichier(String nomFichier) throws IOException
	{
		InputStream ips = this.getClass().getResourceAsStream(nomFichier);
		try (Scanner scanner = new Scanner(ips, "UTF-8"))
		{
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}
	}

	public String getChemin()     {return chemin;}
	public String getIdentifiant(){return identifiant;}
	public String getMotDePasse() {return motDePasse;}
}
