package metier.model;

import metier.IModifiable;
import metier.repo.CategorieHeureDB;
import metier.repo.CategorieIntervenantDB;

/**
 * TypeHeure
 * Classe qui permet les différents types d'heures
 */

public class CategorieHeure implements IModifiable
{
	
	private String nomOrigine;
	private String nom;
	private double coef;

	public CategorieHeure(String nom, double coef)
	{
		this.nomOrigine = nom;
		this.nom = nom;
		this.coef = coef;
	}

	public String getNom()  { return this.nom;  }
	public String getNomOrigine() { return this.nomOrigine;  }
	public double getCoef() { return this.coef; }

	public void setNom(String nom)   { this.nom = nom;   }
	public void setCoef(double coef) { this.coef = coef; }
	public void setNomOrigine(String nom) { this.nomOrigine = nom; }

	
	public boolean sauvegarder(){
        return CategorieHeureDB.save(this);
    }

    public boolean supprimer(){
        return CategorieHeureDB.delete(this);
    }

}