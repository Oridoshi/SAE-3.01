package metier.model;

import metier.repo.IntervenantDB;

/*
 * Intervenant
 * Classe qui permet de creer un intervenant
 */

import metier.repo.AffectationDB;
import metier.repo.CategorieIntervenantDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public CategorieIntervenant getCategorie() {
		return this.categorieIntervenant;
	}
	public String getNom()    { return nom;    }
	public String getPrenom() { return prenom; }
	public Integer gethMax(){
		if ( this.hMax > 0 ){
			return this.hMax;
		} else {
			return getCategorie().getMaxH();
		}
	}

	public boolean setCategorie(CategorieIntervenant categorieIntervenant) {
		if ( !CategorieIntervenantDB.list().contains(categorieIntervenant) ) return false;
		this.categorieIntervenant = categorieIntervenant;
		return true;
	}
	public boolean sethMax(Integer hMax)             {
		if ( this.categorieIntervenant.getMinH() > hMax ) return false;
		this.hMax = hMax;
		return true;
	}


	public float getHParSemestre(int i){
		int h = 0;
		if ( AffectationDB.getAffectationsParIntervenant(this.id) == null ) return 0;
		for ( Affectation affectation : AffectationDB.getAffectationsParIntervenant(this.id)){
			if ( i == affectation.getModule().getSemestre().getId() ){
				h += affectation.getNbEqTd();
			}
		}
		return h;
	}

	public float getTotalParPair(){
		int h = 0;
		if ( AffectationDB.getAffectationsParIntervenant(this.id) == null ) return 0;
		for ( Affectation affectation : AffectationDB.getAffectationsParIntervenant(this.id)){
			if ( affectation.getModule().getSemestre().getId() % 2 == 0){
				h += affectation.getNbEqTd();
			}
		}
		return h;
	}

	public float getTotalParImpair(){
		int h = 0;
		if ( AffectationDB.getAffectationsParIntervenant(this.id) == null ) return 0;
		for ( Affectation affectation : AffectationDB.getAffectationsParIntervenant(this.id)){
			if ( affectation.getModule().getSemestre().getId() % 2 != 0){
				h += affectation.getNbEqTd();
			}
		}
		return h;
	}

	public Integer getHMin()
	{
		return this.getCategorie().getMinH();
	}

	public double getCoefTP()
	{
		return this.getCategorie().getCoefTp();
	}

	public double getTotal()
	{
		return this.getTotalParImpair() + this.getTotalParPair();
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Set<Module> getModulesOuIntervient(){
		return AffectationDB.getModulesParIntervenant(this);
	}

	public boolean sauvegarder()
	{
		return IntervenantDB.save(this);
	}

	public boolean supprimer()
	{
		return IntervenantDB.delete(this);
	}
}