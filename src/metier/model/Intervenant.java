package metier.model;

import metier.repo.IntervenantDB;
import metier.IModifiable;

/*
 * Intervenant
 * Classe qui permet de creer un intervenant
 */

import metier.repo.AffectationDB;
import metier.repo.CategorieIntervenantDB;

import java.util.List;
import java.util.ArrayList;

public class Intervenant implements IModifiable
{
	private int id;
	private CategorieIntervenant categorieIntervenant;
	private String nom;
	private String prenom;
	private Integer hMax;
	private Integer hMin;
	private double coefTP;

	public Intervenant( int id, CategorieIntervenant categorieIntervenant, String nom, String prenom, int hMin, int hMax, double coefTP)
	{
		this.id = id;
		this.categorieIntervenant = categorieIntervenant;
		this.nom = nom;
		this.prenom = prenom;
		this.hMax = hMax;
		this.hMin = hMin;
		this.coefTP = coefTP;
	}

	public int getId() {return id;}
	public String getNom()    { return nom;    }
	public String getPrenom() { return prenom; }

	public CategorieIntervenant getCategorie() {
		return this.categorieIntervenant;
	}
	public Integer gethMax(){
		if ( this.hMax > 0 ){
			return this.hMax;
		} else {
			return getCategorie().getMaxH();
		}
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
		if(this.hMin == 0)
			return this.categorieIntervenant.getMinH();

		return this.hMin;
	}

	public double getCoefTP()
	{
		if(this.coefTP == 0)
			return this.categorieIntervenant.getCoefTp();

		return this.coefTP;
	}

	public double getTotal()
	{
		return this.getTotalParImpair() + this.getTotalParPair();
	}

	public List<Affectation> getModulesOuIntervient(){
		ArrayList<Affectation> lstAffectation = new ArrayList<Affectation>();

		if(AffectationDB.getAffectationsParIntervenant(this.id) != null)
			for ( Affectation affectation : AffectationDB.getAffectationsParIntervenant(this.id))
			{
				lstAffectation.add(affectation);
			}

		return lstAffectation;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean setCoefTP(double coefTP) {
		if ( coefTP > 0 ){
			this.coefTP = coefTP;
			return true;
		}
		return false;
	}

	public boolean setHMin(Integer hMin) {
		if ( hMin < 0 ) return false;
		if ( hMin > this.hMax ) return false;
		this.hMin = hMin;
		return true;
	}

	public boolean setCategorie(CategorieIntervenant categorieIntervenant) {
	if ( !CategorieIntervenantDB.list().contains(categorieIntervenant) ) return false;
	this.categorieIntervenant = categorieIntervenant;
	return true;
	}
	public boolean sethMax(Integer hMax){
		if ( this.hMin > hMax ) return false;
		this.hMax = hMax;
		return true;
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