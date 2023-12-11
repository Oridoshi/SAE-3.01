package metier;

/*
 * Intervenant
 * Classe qui permet de creer un intervenant
 */

public class Intervenant
{

	private Categorie categorie;
	private String nom;
	private String prenom;
	private Integer hMax;

	public Intervenant(Categorie categorie, String nom, String prenom, int hServ, double coefTP)
	{
		this.categorie = categorie;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Categorie getCategorie() { return categorie; }

	public String getNom()    { return nom;    }
	public String getPrenom() { return prenom; }
	public int    gethMax()   { return hMax;   }

	public void setCategorie(Categorie categorie) { this.categorie = categorie; }
	public void sethMax(Integer hMax)             { this.hMax = hMax;           }

}