package metier.model;

import metier.repo.AffectationDB;

/*
 * Affectation
 * Classe permettant de gérer les différentes affectations
 */

public class Affectation
{
	private Intervenant intervenant;
	private CategorieHeure categorieHeure;
	private Integer nbGroupe;
	private Integer nbSemaine;
	private Integer nbHeure;
	private String commentaire;
	private Module module;

	public Affectation(Intervenant intervenant, CategorieHeure categorieHeure, Integer nbGroupe, Integer nbSemaine, Integer nbHeure, String commentaire, Module module) {
		this.intervenant = intervenant;
		this.categorieHeure = categorieHeure;
		this.nbGroupe = nbGroupe;
		this.nbSemaine = nbSemaine;
		this.nbHeure = nbHeure;
		this.commentaire = commentaire;
		this.module = module;
	}

	public Intervenant getIntervenant(){return intervenant;}
	public Integer getNbGroupe(){return nbGroupe;}
	public Integer getNbSemaine(){return nbSemaine;}
	public String getCommentaire(){return commentaire;}
	public Integer getNbHeure(){return nbHeure;}
	public Float getNbEqTd (){
		int nbHeure = this.module.getProgrammeItem(this.categorieHeure.getNom()).getNbHeure();
		if ( this.getNbSemaine() == null ){
			nbHeure = nbHeure * this.getNbSemaine() * this.getNbGroupe();
		}
		nbHeure = (int) ( nbHeure * this.getCategorieHeure().getCoef() );
		return nbHeure * 0F;
	}

	public CategorieHeure getCategorieHeure() {
		return categorieHeure;
	}
	public Module getModule() {
		return module;
	}

	public void ajouterAffectationToBd()
	{
		new AffectationDB().ajouterAffectation(this);
	}

	public void suppAffectationFromBd()
	{
		new AffectationDB().suppAffectation(this);
	}
}