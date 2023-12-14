package metier.model;

import metier.repo.AffectationDB;
import metier.repo.CategorieHeureDB;
import metier.repo.IntervenantDB;
import metier.repo.ModuleDB;

/*
 * Affectation
 * Classe permettant de gérer les différentes affectations
 */

public class Affectation
{
	private Intervenant intervenant;
	private CategorieHeure categorieHeure;
	private int nbGroupe;
	private int nbSemaine;
	private int nbHeure;
	private String commentaire;
	private Module module;

	public Affectation(Intervenant intervenant, CategorieHeure categorieHeure, int nbGroupe, int nbSemaine, int nbHeure, String commentaire,
			Module module) {
		this.intervenant = intervenant;
		this.categorieHeure = categorieHeure;
		this.nbGroupe = nbGroupe;
		this.nbSemaine = nbSemaine;
		this.nbHeure = nbHeure;
		this.commentaire = commentaire;
		this.module = module;
	}
	
	public Intervenant getIntervenant(){
		return this.intervenant;
	}

	public Integer getNbGroupe(){
		if ( nbGroupe == 0 ) return null;
		return nbGroupe;
	}

	public Integer getNbSemaine(){
		if ( nbSemaine == 0 ) return null;
		return nbSemaine;
	}

	public String getCommentaire(){
		return commentaire;
	}

	public Integer getNbHeure(){
		if ( nbHeure == 0 ) return null;
		return nbHeure;
	}

	public float getNbEqTd (){
		if ( this.nbHeure > 0 ) return (int) (this.nbHeure * getCategorieHeure().getCoef());
		return (int) (getModule().getProgramme().getItem(getCategorieHeure().getNom()).getNbHeure() * this.nbGroupe * this.nbSemaine * getCategorieHeure().getCoef());
	}

	public CategorieHeure getCategorieHeure() {
		return this.categorieHeure;
	}

	public Module getModule() {
		return this.module;
	}

	public void sauvegarder()
	{
		AffectationDB.save(this);
	}

	public void supprimer()
	{
		AffectationDB.delete(this);
	}
}