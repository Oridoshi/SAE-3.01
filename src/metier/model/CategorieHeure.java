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
	
	private String nom;
	private double coef;

	public CategorieHeure(String nom, double coef)
	{
		this.nom = nom;
		this.coef = coef;
	}

	public String getNom()  { return this.nom;  }
	public double getCoef() { return this.coef; }

	public void setNom(String nom)   { this.nom = nom;   }
	public void setCoef(double coef) { this.coef = coef; }

	
	public boolean sauvegarder(){
        return CategorieHeureDB.save(this);
    }

    public boolean supprimer(){
        return CategorieHeureDB.delete(this);
    }

}