package metier.model;

/*
 * Intervenant
 * Classe qui permet de creer un intervenant
 */

import metier.repo.AffectationDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intervenant
{

	private int id;
	private CategorieIntervenant categorieIntervenant;
	private String nom;
	private String prenom;
	private Integer hMax;
	private Map<Integer, Float> hParSemestre;

	public Intervenant(int id, CategorieIntervenant categorieIntervenant, String nom, String prenom, int hServ)
	{
		this.id = id;
		this.categorieIntervenant = categorieIntervenant;
		this.nom = nom;
		this.prenom = prenom;
		this.hMax = hServ;
		initHParSemestre();
	}

	public int getId() {return id;}
	public CategorieIntervenant getCategorie() { return categorieIntervenant; }
	public String getNom()    { return nom;    }
	public String getPrenom() { return prenom; }
	public int    gethMax()   { return hMax;   }

	public void setCategorie(CategorieIntervenant categorieIntervenant) { this.categorieIntervenant = categorieIntervenant; }
	public void sethMax(Integer hMax)             { this.hMax = hMax;           }

	private float initHParSemestre(){
		Map<Integer, Float> hParSemestre = new HashMap<>();
		List<Affectation> affectations = new AffectationDB().getAffectationsParIntervenantId(this.id);
		float totalHeure = 0;
		for ( Affectation affectation : affectations ){
			hParSemestre.put(affectation.getModule().getSemestre().getId(), affectation.getNbEqTd());
		}
		return totalHeure;
	}

	public float getHParSemestre(int i){
		return this.hParSemestre.get(i);
	}

	public float getTotalParPair(){
		float totalHPair = 0F;
		for ( Float i : this.hParSemestre.values() ){
			if ( i % 2 == 0 ){
				totalHPair += this.hParSemestre.get(i);
			}
		}
		return totalHPair;
	}

	public float getTotalParImpair(){
		float totalHImpair = 0F;
		for ( Float i : this.hParSemestre.values() ){
			if ( i % 2 != 0 ){
				totalHImpair += this.hParSemestre.get(i);
			}
		}
		return totalHImpair;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}