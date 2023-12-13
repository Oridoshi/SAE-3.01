package metier.model;

import metier.repo.IntervenantDB;

/*
 * Intervenant
 * Classe qui permet de creer un intervenant
 */

public class Intervenant
{

	private int id;
	private CategorieIntervenant categorieIntervenant;
	private String nom;
	private String prenom;
	private Integer hMax;

	public Intervenant(int id, CategorieIntervenant categorieIntervenant, String nom, String prenom, int hServ)
	{
		this.id = id;
		this.categorieIntervenant = categorieIntervenant;
		this.nom = nom;
		this.prenom = prenom;
		this.hMax = hServ;
 		}

	public int getId() {return id;}
	public CategorieIntervenant getCategorie() { return categorieIntervenant; }
	public String getNom()    { return nom;    }
	public String getPrenom() { return prenom; }
	public int    gethMax()   { return hMax;   }

	public void setCategorie(CategorieIntervenant categorieIntervenant) { this.categorieIntervenant = categorieIntervenant; }
	public void sethMax(Integer hMax)             { this.hMax = hMax;           }

	public void setNom(String nom) {
		this.nom = nom;
		this.updateChange();
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
		this.updateChange();
	}


	public void ajouterIntervenantToBd()
	{
		new IntervenantDB().ajouterIntervenant(this);
	}

	public void suppIntervenantFromBd()
	{
		new IntervenantDB().suppIntervenant(this);
	}


	public void updateChange()
	{
		new IntervenantDB().updateIntervenant(this);
	}
}