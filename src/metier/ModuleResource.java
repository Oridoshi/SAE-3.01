package metier;

import java.util.ArrayList;


/**
 * ModuleResource
 * Classe permettant de créer et gérer les différentes ressources 
 */
public class ModuleResource extends Module
{
	private int hPnTd;   //heures programme pour TD
	private int hPnTp;   //heures programme pour TP
	private int hPnCm;   //heures programme pour CM
	private int hPnTut;  //heures programme pour TUT

	private int hSemaineTd;  //heures TD par semaine
	private int hSemaineTp;  //heures TP par semaine
	private int hSemaineCm;  //heures CM par semaine
	private int hSemaineTut; //heures TUT par semaine

	private int totalPromoEqTd;   //total des heures en équivalent TD pour la promo
	private int totalAffecteEqTd;//total des heures Affecte en équivalent TD pour la promo

	private int hP; //heures ponctuelle

	private ArrayList<Affectation> lstAffectation;

	public ModuleResource(int hPnTd, int hPnTp, int hPnCm, int hPnTut, int hSemaineTd, int hSemaineTp, int hSemaineCm, int hSemaineTut, int hP, String nom, String code, boolean valider)
	{
		super("Ressource", code, nom, valider);
		this.hPnTd = hPnTd;
		this.hPnTp = hPnTp;
		this.hPnCm = hPnCm;
		this.hPnTut = hPnTut;
		this.hSemaineTd = hSemaineTd;
		this.hSemaineTp = hSemaineTp;
		this.hSemaineCm = hSemaineCm;
		this.hSemaineTut = hSemaineTut;
		this.hP = hP;
		this.lstAffectation = new ArrayList<Affectation>();
		this.totalPromoEqTd = 0;
		this.totalAffecteEqTd = 0;
	}

	public int gethPnTd() {return hPnTd;}
	public int gethPnTp() {return hPnTp;}
	public int gethPnCm() {return hPnCm;}
	public int gethPnTut() {return hPnTut;}
	public int gethSemaineTd() {return hSemaineTd;}
	public int gethSemaineTp(){return hSemaineTp;}
	public int gethSemaineCm(){return hSemaineCm;}
	public int gethSemaineTut(){return hSemaineTut;}
	public int gethP(){return hP;}
	public ArrayList<Affectation> getLstAffectation(){return lstAffectation;}
	public int getTotalPromoEqTd(){return totalPromoEqTd;}
	public int getTotalAffecteEqTd(){return totalAffecteEqTd;}

	public void sethPnTd(int hPnTd) {this.hPnTd = hPnTd;}
	public void sethPnTp(int hPnTp) {this.hPnTp = hPnTp;}
	public void sethPnCm(int hPnCm) {this.hPnCm = hPnCm;}
	public void sethPnTut(int hPnTut) {this.hPnTut = hPnTut;}
	public void sethSemaineTd(int hSemaineTd){this.hSemaineTd = hSemaineTd;}
	public void sethSemaineTp(int hSemaineTp){this.hSemaineTp = hSemaineTp;}
	public void sethSemaineCm(int hSemaineCm){this.hSemaineCm = hSemaineCm;}
	public void sethSemaineTut(int hSemaineTut){this.hSemaineTut = hSemaineTut;}
	public void sethP(int hP){this.hP = hP;}
	public void setLstAffectation(ArrayList<Affectation> lstAffectation){this.lstAffectation = lstAffectation;}

	public void addAffectation(Intervenant intervenant, TypeHeure typeHeure, int nbGroupe, int nbSemaine, String commentaire)
	{
		Affectation affectation = new Affectation(intervenant, typeHeure, nbGroupe, nbSemaine, commentaire);
		lstAffectation.add(affectation);
	}

	public void addAffectation(Intervenant intervenant, TypeHeure typeHeure, int nbHeure, String commentaire)
	{
		Affectation affectation = new Affectation(intervenant, typeHeure, nbHeure, commentaire);
		lstAffectation.add(affectation);
	}
}
