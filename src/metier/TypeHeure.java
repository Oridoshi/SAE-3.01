package metier;

/**
 * TypeHeure
 * Classe qui permet les différents types d'heures
 */

public class TypeHeure
{
	private String code;
	private String nom;
	private double coef;


	public TypeHeure(String code, String nom, double coef)
	{
		this.code = code;
		this.nom = nom;
		this.coef = coef;
	}

	public String getCode() { return this.code; }
	public String getNom()  { return this.nom;  }
	public double getCoef() { return this.coef; }

	public void setCode(String code) { this.code = code; }
	public void setNom(String nom)   { this.nom = nom;   }
	public void setCoef(double coef) { this.coef = coef; }

}