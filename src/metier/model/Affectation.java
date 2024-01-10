package metier.model;

import metier.IModifiable;
import metier.repo.AffectationDB;

/**
 * Affectation
 * Classe permettant de gérer les différentes affectations
 */
public class Affectation implements IModifiable
{
	private int id;
	private Intervenant intervenant;
	private CategorieHeure categorieHeure;
	private int nbGroupe;
	private int nbSemaine;
	private int nbHeure;
	private String commentaire;
	private Module module;

	public Affectation(int id, Intervenant intervenant, CategorieHeure categorieHeure, int nbGroupe, int nbSemaine, int nbHeure, String commentaire,
			Module module) {
		this.id = id;
		this.intervenant = intervenant;
		this.categorieHeure = categorieHeure;
		this.nbGroupe = nbGroupe;
		this.nbSemaine = nbSemaine;
		this.nbHeure = nbHeure;
		this.commentaire = commentaire;
		this.module = module;
	}

	public int getId() {
		return this.id;
	}

	public Affectation setId(int id){
		this.id = id;
		return this;
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
		return (int) (getModule().getNbHeureSemaine(getCategorieHeure().getNom()) * this.nbGroupe * this.nbSemaine * getCategorieHeure().getCoef());
	}

	public CategorieHeure getCategorieHeure() {
		return this.categorieHeure;
	}

	public void setCategorieHeure(CategorieHeure categorieHeure) {
		this.categorieHeure = categorieHeure;
	}

	public void setNbGroupe(int nbGroupe) {
		this.nbGroupe = nbGroupe;
	}

	public void setNbSemaine(int nbSemaine) {
		this.nbSemaine = nbSemaine;
	}

	public void setNbHeure(int nbHeure) {
		this.nbHeure = nbHeure;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Module getModule() {
		return this.module;
	}

	public boolean sauvegarder()
	{
		return AffectationDB.save(this);
	}

	public boolean supprimer()
	{
		return AffectationDB.delete(this);
	}
}