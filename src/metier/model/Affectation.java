package metier.model;

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
	public int getNbGroupe(){return nbGroupe;}
	public int getNbSemaine(){return nbSemaine;}
	public String getCommentaire(){return commentaire;}
	public int getNbHeure(){return nbHeure;}

	public CategorieHeure getCategorieHeure() {
		return categorieHeure;
	}

	public Module getModule() {
		return module;
	}
}